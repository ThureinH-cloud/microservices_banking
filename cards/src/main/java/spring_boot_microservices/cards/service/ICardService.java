package spring_boot_microservices.cards.service;

import spring_boot_microservices.cards.dto.CardsDto;

public interface ICardService {
    void createCard(String mobileNumber);
    CardsDto fetchCard(String mobileNumber);
    boolean deleteCard(String mobileNumber);
}
