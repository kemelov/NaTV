package kg.natv.TextAd.repositories;

import kg.natv.TextAd.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepo extends JpaRepository<Ad, Long> {
}
