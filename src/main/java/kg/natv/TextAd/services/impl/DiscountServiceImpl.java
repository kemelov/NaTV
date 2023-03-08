package kg.natv.TextAd.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import kg.natv.TextAd.mappers.ChannelMapper;
import kg.natv.TextAd.mappers.DiscountMapper;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.ChannelDTO;
import kg.natv.TextAd.models.DTOs.DiscountDTO;
import kg.natv.TextAd.models.Discount;
import kg.natv.TextAd.repositories.ChannelRepo;
import kg.natv.TextAd.repositories.DiscountRepo;
import kg.natv.TextAd.services.DiscountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepo discountRepo;
    private final DiscountMapper discountMapper;
    private final ChannelRepo channelRepo;
    private final ChannelMapper channelMapper;
    private final ObjectMapper objectMapper;

    public DiscountServiceImpl(DiscountRepo discountRepo, DiscountMapper discountMapper, ChannelRepo channelRepo, ChannelMapper channelMapper, ObjectMapper objectMapper){
        this.discountRepo = discountRepo;
        this.discountMapper = discountMapper;
        this.channelRepo = channelRepo;
        this.channelMapper = channelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public DiscountDTO findById(Long id) {
        Optional<Discount> discount = discountRepo.findById(id);
        if (discount.isPresent()){
            return discountMapper.toDTO(discount.get());
        } else {
            throw new NoSuchElementException("Не найдено");
        }
    }

    @Transactional
    @Override
    public DiscountDTO update(Long id, DiscountDTO discountDTO) throws IOException {
        Optional<Discount> optionalDiscount = discountRepo.findById(id);
        if (optionalDiscount.isEmpty()) {
            throw new NoSuchElementException("Не найдено");
        }
        Discount discount = optionalDiscount.get();
        if (!discount.isActive()&&discountDTO.isActive()){
            discount.setEndDate(null);
            discount.setStartDate(LocalDateTime.now());
        }
        ObjectReader objectReader = objectMapper.readerForUpdating(discount);
        discount = objectReader.readValue(objectMapper.writeValueAsString(discountDTO));

        return discountMapper.toDTO(discount);
    }


    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {

        Optional<Channel> channel = channelRepo.findById(discountDTO.getChannelId());
        if (channel.isEmpty()){
            throw new NoSuchElementException("Нет канала с таким ID");
        }
        Channel exChannel = channel.get();
        Discount discount = discountMapper.toEntity(discountDTO);
        discount.setChannel(exChannel);
        discount.setStartDate(LocalDateTime.now());
        discount.setActive(true);
        discount = discountRepo.save(discount);
        exChannel.getDiscounts().add(discount);
        DiscountDTO saved = discountMapper.toDTO(discount);
        saved.setChannelId(exChannel.getId());
        ChannelDTO channelDTO = channelMapper.toDTO(exChannel);
        channelDTO.getDiscounts().add(saved);
        return saved;
    }

    @Override
    public void delete(Long id) {
        discountRepo.findById(id);
        discountRepo.deleteById(id);
        discountRepo.resetAutoIncrement();
    }

    @Transactional
    @Override
    public DiscountDTO deactivate(Long id) {
        Optional<Discount> discount = discountRepo.findById(id);
        if (discount.isEmpty()){
            throw new NoSuchElementException("Не найдено");
        }
        Discount exDiscount = discount.get();
        exDiscount.setActive(false);
        exDiscount.setEndDate(LocalDateTime.now());
        return discountMapper.toDTO(exDiscount);
    }
}
