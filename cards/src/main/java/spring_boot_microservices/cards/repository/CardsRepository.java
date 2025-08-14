package spring_boot_microservices.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot_microservices.cards.entity.Cards;


import java.util.Optional;


public interface CardsRepository extends JpaRepository<Cards, Long> {
    Optional<Cards> findByMobileNumber(String mobileNumber);
}
