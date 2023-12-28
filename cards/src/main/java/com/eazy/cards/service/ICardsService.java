package com.eazy.cards.service;

import com.eazy.core.dto.CardsDto;

public interface ICardsService {
	/**
	 * 
	 * @param CardsDto
	 */
	public void createCard(CardsDto cardsDto);

	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public CardsDto fetchCard(String mobileNumber);
	
	
	/**
	 * 
	 * @param CardsDto
	 * @return
	 */
	public boolean updateCard(CardsDto cardsDto);
	
	/**
	 * 
	 * @param mobileNumber
	 * @return
	 */
	public boolean deleteCard(String mobileNumber);

}
