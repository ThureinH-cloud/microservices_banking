package spring_boot_microservices.cards.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring_boot_microservices.cards.constants.CardsConstants;
import spring_boot_microservices.cards.dto.CardsDto;
import spring_boot_microservices.cards.entity.Cards;
import spring_boot_microservices.cards.exception.CardAlreadyExistsException;
import spring_boot_microservices.cards.exception.ResourceNotFoundException;
import spring_boot_microservices.cards.mapper.CardsMapper;
import spring_boot_microservices.cards.repository.CardsRepository;
import spring_boot_microservices.cards.service.ICardService;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {
    private CardsRepository cardsRepository;
    @Override
    @Transactional
    public void createCard(String mobileNumber) {
        Optional<Cards> cards=cardsRepository.findByMobileNumber(mobileNumber);
        if (cards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        return CardsMapper.mapToCardsDto(cards,new CardsDto());
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards=cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(()->new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardsRepository.delete(cards);
        return true;
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
