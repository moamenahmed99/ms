package com.moamenprojects.cards.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.moamenprojects.cards.constants.CardsConstants;
import com.moamenprojects.cards.dto.CardsDto;
import com.moamenprojects.cards.entity.Cards;
import com.moamenprojects.cards.exception.ResourceNotFoundException;
import com.moamenprojects.cards.mapper.CardsMapper;
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
    public CardsDto fetchCards(String mobileNumber) {
        Cards cards =  cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.maptoCardsDto(cards, new CardsDto());
    }

    @Override
    public void updateCard(CardsDto cardsDto) {
        String cardNumber = cardsDto.getCardNumber();
        Cards card = cardsRepository.findByCardNumber(cardNumber).orElseThrow(
            ()-> new ResourceNotFoundException("Card", "CardNumber", cardNumber)
        );
        CardsMapper.maptoCards(card, cardsDto);
        cardsRepository.save(card);
    }

    @Override
    public void deleteCard(String mobileNumber) {
        
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
            ()-> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(card.getCardId());
    }


}
