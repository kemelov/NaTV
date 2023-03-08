package kg.natv.TextAd.repositories;

import kg.natv.TextAd.models.Channel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ChannelRepo extends JpaRepository<Channel, Long> {
//    List<Channel> findByActiveTrue();

    @Override
    @Query("SELECT c FROM Channel c ORDER BY lower(unaccent(c.name))")
    List<Channel> findAll();

    @Transactional
    @Query(value = "SELECT setval('tb_channel_id_seq', coalesce((SELECT MAX(id) FROM tb_channel), 1));", nativeQuery = true)
    void resetAutoIncrement();
}
