package kg.natv.TextAd.services.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import kg.natv.TextAd.mappers.ChannelMapper;
import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.Channel;

import kg.natv.TextAd.models.DTOs.ChannelDTO;
import kg.natv.TextAd.repositories.ChannelRepo;
import kg.natv.TextAd.services.ChannelService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepo channelRepo;
    private final ChannelMapper channelMapper;
    private final ObjectMapper objectMapper;

    public ChannelServiceImpl(ChannelRepo channelRepo, ChannelMapper channelMapper, ObjectMapper objectMapper){
        this.channelRepo = channelRepo;
        this.channelMapper = channelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public ChannelDTO findById(Long id) {
        Optional<Channel> channel = channelRepo.findById(id);
        if (channel.isPresent()){
            return channelMapper.ToDTO(channel.get());
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
    public ChannelDTO save(ChannelDTO channelDTO) {
        Channel channel = channelMapper.toEntity(channelDTO);
        channel.setActive(true);
        channel = channelRepo.save(channel);
        return channelMapper.ToDTO(channel);
    }

    @Transactional
    @Override
    public ChannelDTO update(Long id, String json) throws IOException {
        Optional<Channel> optionalChannel = channelRepo.findById(id);
        if (optionalChannel.isEmpty()) {
            throw new NoSuchElementException("No channel found with id " + id);
        }
        Channel channel = optionalChannel.get();
        ObjectReader objectReader = objectMapper.readerForUpdating(channel);
        channel = objectReader.readValue(json);
        return channelMapper.ToDTO(channel);
    }

    @Override
    public void delete(Long id) {
        channelRepo.findById(id);
        channelRepo.deleteById(id);
        channelRepo.resetAutoIncrement();
    }

}
