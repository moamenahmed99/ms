package com.moamenprojects.cards.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moamenprojects.cards.entity.Cards;


public interface CardsRepository extends JpaRepository<Cards,Long>{
    // Optional<Cards> findByCardId(Long cardId);
    Optional<Cards> findByMobileNumber(String mobileNumber);
    Optional<Cards> findByCardNumber(String cardNumber);
}
