package kg.natv.TextAd.repositories;

import kg.natv.TextAd.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdRepo extends JpaRepository<Ad, Long> {
    @Transactional
    @Query(value = "SELECT setval('tb_text_ad_id_seq', coalesce((SELECT MAX(id) FROM tb_text_ad), 1));", nativeQuery = true)
    void resetAutoIncrement();
}
