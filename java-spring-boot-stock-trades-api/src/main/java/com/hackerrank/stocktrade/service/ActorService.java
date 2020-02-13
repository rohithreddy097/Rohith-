package com.hackerrank.stocktrade.service;

import java.util.List;
import java.util.Optional;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;

public interface ActorService {

	/**
	 * Get Actor Ordered By Maximum Streak
	 * 
	 * @return {@link List} of Actors
	 */
	List<User> getActorsOrderedByMaximumStreak();

	/**
	 * Update the actor
	 * 
	 * @param user
	 * @return {@link Optional} with the Actor
	 */
	Optional<Object> update(User user);

	/**
	 * Get Actors Ordered By Total Number Of Events
	 * 
	 * @return {@link List} of Actors
	 */
	List<User> getActorsOrderedByTotalNumberOfEvents();
}
