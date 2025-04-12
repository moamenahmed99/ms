package com.moamenprojects.cards.service;

import com.moamenprojects.cards.dto.CardsDto;
import com.moamenprojects.cards.entity.Cards;

import jakarta.validation.constraints.Pattern;

public interface ICardsService {

    void createCard(String mobileNumber);
    CardsDto fetchCards(String mobileNumber);
    void updateCard(CardsDto cardsDto);
    void deleteCard(String mobileNumber);
}
