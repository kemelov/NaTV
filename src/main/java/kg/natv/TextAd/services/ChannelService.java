package kg.natv.TextAd.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.ChannelDTO;
import kg.natv.TextAd.models.DTOs.ChannelDTO;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface ChannelService {

    ChannelDTO findById(Long id);
    List<ChannelDTO> findByActiveTrue();
    List<ChannelDTO> findAll();
    ChannelDTO save(ChannelDTO channelDTO);
    ChannelDTO update(Long id, String json) throws IOException;
    void delete(Long id);
}
