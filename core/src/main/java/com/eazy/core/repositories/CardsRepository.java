package com.eazy.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazy.core.entities.cards.Cards;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {

	public Optional<Cards> findByMobileNumber(String mobileNumber);

	public Optional<Cards> findByCardNumber(String cardNumber);

}
