package kg.natv.TextAd.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import kg.natv.TextAd.mappers.AdMapper;
import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.AdDTO;
import kg.natv.TextAd.repositories.AdRepo;
import kg.natv.TextAd.repositories.ChannelRepo;
import kg.natv.TextAd.services.AdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {
    private final AdRepo adRepo;
    private final AdMapper adMapper;
    private final ChannelRepo channelRepo;
    private final ObjectMapper objectMapper;

    public AdServiceImpl(AdRepo adRepo, AdMapper adMapper, ChannelRepo channelRepo, ObjectMapper objectMapper){
        this.adRepo = adRepo;
        this.adMapper = adMapper;
        this.channelRepo = channelRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public AdDTO findById(Long id) {
        Optional<Ad> ad = adRepo.findById(id);
        if (ad.isPresent()){
            Ad exAd = ad.get();
            if (exAd.getSymbolCount()==0){
                exAd.setSymbolCount(exAd.getText().length());
                adRepo.save(exAd);
            }
            return adMapper.toDTO(ad.get());
        } else {
            throw new NoSuchElementException("Не найден текст с такиим ID");
        }
    }

    @Override
    public List<AdDTO> findAll() {
        List<Ad> ads = adRepo.findAll();
        return adMapper.toDTOList(ads);
    }

    @Override
    public AdDTO save(AdDTO adDTO) {
        Optional<Channel> channel = channelRepo.findById(adDTO.getChannelId());
        if (channel.isEmpty()){
            throw new NoSuchElementException("Нет канала с такиим ID");
        }

        if (adDTO.getOrderDateDTO().getDays().isEmpty()){
            throw new IllegalArgumentException("Ошибка! не выбран день.");
        }

        for (LocalDate date : adDTO.getOrderDateDTO().getDays()) {
            if (date.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("Ошибка! Выбран прошедший день "+date);
            }
        }


        Channel exChannel = channel.get();
        Ad ad = adMapper.toEntity(adDTO);
        ad.setChannel(channel.get());
        ad.setSymbolCount(ad.getText().replaceAll(" ", "").length());
        ad.getOrderDate().setDays(adDTO.getOrderDateDTO().getDays());
        ad.getOrderDate().setAd(ad);
        ad.setDaysCount((long) ad.getOrderDate().getDays().size());
        ad.setStartDate(LocalDateTime.now());
        ad.setEndDate(LocalDateTime.now().plusDays(ad.getDaysCount()));
        ad = adRepo.save(ad);

        AdDTO savedAdDTO = adMapper.toDTO(ad);
        savedAdDTO.setChannelId(exChannel.getId());
        savedAdDTO.setChannelName(exChannel.getName());
        savedAdDTO.getOrderDateDTO().setAdId(adDTO.getId());
        return savedAdDTO;
    }

    @Transactional
    @Override
    public AdDTO update(Long id, AdDTO adDTO) throws IOException {
        Optional<Ad> optionalAd = adRepo.findById(id);
        if (optionalAd.isEmpty()){
            throw new NoSuchElementException("Текст не найден!");
        }
        Ad existingAd = optionalAd.get();
        ObjectReader objectReader = objectMapper.readerForUpdating(existingAd);
        existingAd = objectReader.readValue(objectMapper.writeValueAsString(adDTO));
        return adMapper.toDTO(existingAd);
    }

    public void delete(Long id){
        findById(id);
        adRepo.deleteById(id);
        adRepo.resetAutoIncrement();
    }



}
