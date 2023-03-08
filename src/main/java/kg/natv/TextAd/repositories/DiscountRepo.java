package kg.natv.TextAd.repositories;

import kg.natv.TextAd.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {
    @Transactional
    @Query(value = "SELECT setval('tb_discount_id_seq', coalesce((SELECT MAX(id) FROM tb_discount), 1));", nativeQuery = true)
    void resetAutoIncrement();
}
