package spring_boot_microservices.cards.mapper;

import spring_boot_microservices.cards.dto.CardsDto;
import spring_boot_microservices.cards.entity.Cards;



public final class CardsMapper {
    public static CardsDto mapToCardsDto(Cards cards, CardsDto cardsDto) {
        cardsDto.setMobileNumber(cards.getMobileNumber());
        cardsDto.setCardId(cards.getCardId());
        cardsDto.setCardType(cards.getCardType());
        cardsDto.setCardNumber(cards.getCardNumber());
        cardsDto.setTotalLimit(cards.getTotalLimit());
        cardsDto.setAvailableAmount(cards.getAvailableAmount());
        cardsDto.setAmountUsed(cards.getAmountUsed());
        return cardsDto;
    }
    public static Cards mapToCards(CardsDto cardsDto,Cards cards) {
        cards.setCardId(cardsDto.getCardId());
        cards.setCardType(cardsDto.getCardType());
        cards.setCardNumber(cardsDto.getCardNumber());
        cards.setTotalLimit(cardsDto.getTotalLimit());
        cards.setAvailableAmount(cardsDto.getAvailableAmount());
        cards.setMobileNumber(cardsDto.getMobileNumber());
        cards.setAmountUsed(cardsDto.getAmountUsed());
        return cards;
    }
}
