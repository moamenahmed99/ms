package com.moamenprojects.cards.service;

import com.moamenprojects.cards.entity.Cards;

public interface ICardsService {

    void createCard(String mobileNumber);
    Cards fetchCards(String mobileNumber);

}
