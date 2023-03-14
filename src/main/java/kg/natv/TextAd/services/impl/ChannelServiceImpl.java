package kg.natv.TextAd.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import kg.natv.TextAd.mappers.ChannelMapper;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.*;
import kg.natv.TextAd.repositories.ChannelRepo;
import kg.natv.TextAd.services.PriceService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class ChannelServiceImpl implements kg.natv.TextAd.services.ChannelService {
    private final ChannelRepo channelRepo;
    private final ChannelMapper channelMapper;
    private final ObjectMapper objectMapper;
    private final PriceService priceService;

    public ChannelServiceImpl(ChannelRepo channelRepo, ChannelMapper channelMapper, ObjectMapper objectMapper, @Lazy PriceService priceService){
        this.channelRepo = channelRepo;
        this.channelMapper = channelMapper;
        this.objectMapper = objectMapper;
        this.priceService = priceService;
    }

    @Override
    public ChannelDTO findById(Long id) {
        Optional<Channel> channel = channelRepo.findById(id);
        if (channel.isPresent()){
            return channelMapper.toDTO(channel.get());
        } else {
            throw new NoSuchElementException("Канал не найден");
        }
    }

    @Override
    public List<ChannelDTO> findByActiveTrue() {
        List<Channel> channels = channelRepo.findAll();
        channels.removeIf(channel -> !channel.isActive());
        return channelMapper.toDTOList(channels);
    }

    @Override
    public List<ChannelDTO> findAll() {
        List<Channel> channels = channelRepo.findAll();
        channels.sort(Comparator.comparing(Channel::isActive).reversed());
//        Stream<Channel> sortedChannels = channels.stream().sorted((Comparator.comparing(Channel::isActive).reversed()));
//        channels = sortedChannels.collect(Collectors.toList());
        return channelMapper.toDTOList(channels);
    }


    @Override
    public PriceDTO calculate(PriceDTO priceDTO) {
        ChannelDTO channel = findById(priceDTO.getChannelId());
        if (!channel.isActive()){
            throw new IllegalArgumentException("Выбранный канал неактивный: "+channel.getName()+" id "+channel.getId());
        }
        for (LocalDate date : priceDTO.getOrderDateDTO().getDays()) {
            if (date.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Ошибка! Выбран прошедший день "
                        +date.format(DateTimeFormatter.ofPattern("dd-MM-yyy")));
            }
        }

        priceDTO.getOrderDateDTO().setDays(priceDTO.getOrderDateDTO().getDays());
        priceDTO.setDaysCount((long) priceDTO.getOrderDateDTO().getDays().size());
        priceDTO.setSymbolCount(priceDTO.getText().replaceAll(" ", "").length());
        priceDTO.setChannelId(priceDTO.getChannelId());
        priceDTO.setStartDate(Objects.requireNonNull(priceDTO.getOrderDateDTO().getDays().stream().min(LocalDate::compareTo).orElse(null)).atStartOfDay());
        priceDTO.setEndDate(Objects.requireNonNull(priceDTO.getOrderDateDTO().getDays().stream().max(LocalDate::compareTo).orElse(null)).atStartOfDay());

        priceService.calculatePrice(priceDTO);
        priceService.calculatePriceWithDiscount(priceDTO);
        return priceDTO;
    }

    @Override
    public ChannelDTO save(ChannelDTO channelDTO) {
        Channel channel = channelMapper.toEntity(channelDTO);
        channel.setActive(true);
        channel = channelRepo.save(channel);
        return channelMapper.toDTO(channel);
    }

    @Transactional
    @Override
    public ChannelDTO update(Long id, ChannelDTO channelDTO) throws IOException {
        Optional<Channel> optionalChannel = channelRepo.findById(id);
        if (optionalChannel.isEmpty()) {
            throw new NoSuchElementException("Нет канала с таким ID");
        }
        Channel channel = optionalChannel.get();
        ObjectReader objectReader = objectMapper.readerForUpdating(channel);
        channel = objectReader.readValue(objectMapper.writeValueAsString(channelDTO));
        return channelMapper.toDTO(channel);
    }

    @Override
    public void delete(Long id) {
        channelRepo.findById(id);
        channelRepo.deleteById(id);
        channelRepo.resetAutoIncrement();
    }

}
