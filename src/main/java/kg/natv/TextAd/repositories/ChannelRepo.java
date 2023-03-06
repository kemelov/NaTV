package kg.natv.TextAd.repositories;

import kg.natv.TextAd.models.Ad;
import kg.natv.TextAd.models.Channel;
import kg.natv.TextAd.models.DTOs.ChannelDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {
//    List<Channel> findByActiveTrue();

    @Transactional
    @Query(value = "SELECT setval('tb_channel_id_seq', coalesce((SELECT MAX(id) FROM tb_channel), 1));", nativeQuery = true)
    void resetAutoIncrement();
}
