package kg.natv.TextAd.services;

import kg.natv.TextAd.models.DTOs.ChannelDTO;

import java.io.IOException;
import java.util.List;

public interface ChannelService {

    ChannelDTO findById(Long id);
    List<ChannelDTO> findByActiveTrue();
    List<ChannelDTO> findAll();
    ChannelDTO save(ChannelDTO channelDTO);
    ChannelDTO update(Long id, ChannelDTO channelDTO) throws IOException;
    void delete(Long id);

}
