package kg.natv.TextAd.repositories;

import kg.natv.TextAd.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    @Transactional
    @Query(value = "SELECT setval('tb_order_id_seq', coalesce((SELECT MAX(id) FROM tb_order), 1));", nativeQuery = true)
    void resetAutoIncrement();
}
