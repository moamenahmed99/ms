package com.moamenprojects.cards.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.moamenprojects.cards.constants.CardsConstants;
import com.moamenprojects.cards.entity.Cards;
import com.moamenprojects.cards.repository.CardsRepository;
import com.moamenprojects.cards.service.ICardsService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardsImpl implements ICardsService{

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public Cards fetchCards(String mobileNumber) {
        // cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
            // () ->
        // )
        throw new UnsupportedOperationException("Unimplemented method 'fetchCards'");
    }

}
