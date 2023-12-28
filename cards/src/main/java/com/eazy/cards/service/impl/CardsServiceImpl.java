package com.eazy.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazy.cards.service.ICardsService;
import com.eazy.core.constants.ApplicationConstants;
import com.eazy.core.dto.CardsDto;
import com.eazy.core.entities.cards.Cards;
import com.eazy.core.exception.CardAlreadyExistsException;
import com.eazy.core.exception.ResourceNotFoundException;
import com.eazy.core.mapper.CardsMapper;
import com.eazy.core.repositories.AccountsRepository;
import com.eazy.core.repositories.CardsRepository;
import com.eazy.core.repositories.CustomerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

	@Autowired
	private AccountsRepository accountsRepository;
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CardsRepository cardsRepository;

	@Override
	public void createCard(CardsDto cardsDto) {
		Cards card = CardsMapper.mapToCards(cardsDto, new Cards());
		Optional<Cards> optCard = cardsRepository.findByMobileNumber(card.getMobileNumber());
		if (optCard.isPresent()) {
			throw new CardAlreadyExistsException(
					"Loan already registered with given mobile number " + optCard.get().getMobileNumber());
		}
		cardsRepository.save(createNewCards(card.getMobileNumber()));
	}

	private Cards createNewCards(String mobileNumber) {
		Cards card = new Cards();
		long accNumber = 100000000000L + new Random().nextInt(900000000);
		card.setCardNumber(Long.toString(accNumber));
		card.setMobileNumber(mobileNumber);
		card.setCardType(ApplicationConstants.CARD_VISA);
		card.setAmountUsed(0);
		card.setAvailableAmount(ApplicationConstants.NEW_CARD_LIMIT);
		card.setTotalLimit(ApplicationConstants.NEW_CARD_LIMIT);
		return card;
	}

	@Override
	public CardsDto fetchCard(String mobileNumber) {
		Cards card = cardsRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber));

		return CardsMapper.mapToCardsDto(card, new CardsDto());
	}

	@Override
	public boolean updateCard(CardsDto cardsDto) {
		boolean isUpdated = false;
		if (null != cardsDto) {
			Cards card = cardsRepository.findByCardNumber(cardsDto.getCardNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Card", "Card Number", cardsDto.getCardNumber()));
			CardsMapper.mapToCards(cardsDto, card);
			cardsRepository.save(card);
			isUpdated = true;
		}

		return isUpdated;
	}

	@Override
	public boolean deleteCard(String mobileNumber) {
		Cards card = cardsRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Card", "mobile Number", mobileNumber));
		cardsRepository.deleteById(card.getCardId());
		return true;
	}

}
