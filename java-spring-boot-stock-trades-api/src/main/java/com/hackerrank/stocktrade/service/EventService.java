package com.hackerrank.stocktrade.service;

import java.util.List;
import java.util.Optional;


import com.hackerrank.stocktrade.model.Trade;

public interface EventService {

	/**
	 * Get Events By Actor Id
	 * 
	 * @param actorId
	 * @return {@link List} of Events
	 */
	List<Trade> getEventsByActorId(Long actorId);

	/**
	 * Save Event
	 * 
	 * @param event
	 * @return {@link Optional}
	 */
	Optional<Object> save(Trade event);

	/**
	 * Find All Events
	 * 
	 * @return {@link Iterable} of Events
	 */
	Iterable<Trade> findAll();

	/**
	 * Delete All Events
	 */
	void deleteAll();
}
