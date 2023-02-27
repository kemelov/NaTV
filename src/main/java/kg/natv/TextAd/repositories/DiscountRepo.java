package kg.natv.TextAd.repositories;

import kg.natv.TextAd.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepo extends JpaRepository<Discount, Long> {
}