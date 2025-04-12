package com.moamenprojects.cards.mapper;

import com.moamenprojects.cards.dto.CardsDto;
import com.moamenprojects.cards.entity.Cards;

public class CardsMapper {

    public static CardsDto maptoCardsDto(Cards card, CardsDto cardDto){
        cardDto.setAmountUsed(card.getAmountUsed());
        cardDto.setAvailableAmount(card.getAvailableAmount());
        cardDto.setCardNumber(card.getCardNumber());
        cardDto.setMobileNumber(card.getMobileNumber());
        cardDto.setTotalLimit(card.getTotalLimit());
        cardDto.setCardType(card.getCardType());
        return cardDto;
    }

    public static Cards maptoCards(Cards card, CardsDto cardDto){
        card.setAmountUsed(cardDto.getAmountUsed());
        card.setAvailableAmount(cardDto.getAvailableAmount());
        card.setCardNumber(cardDto.getCardNumber());
        card.setCardType(cardDto.getCardType());
        card.setMobileNumber(cardDto.getMobileNumber());
        card.setTotalLimit(cardDto.getTotalLimit());
        return card;
    }
}
