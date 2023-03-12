package kg.natv.TextAd.services;

import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.DTOs.AdDTO;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface AdService {
    AdDTO findById(Long id);
    List<AdDTO> findAll();
    AdDTO save(AdDTO adDTO);
    AdDTO update(Long id, AdDTO adDTO) throws IOException;
    void delete(Long id);
}
