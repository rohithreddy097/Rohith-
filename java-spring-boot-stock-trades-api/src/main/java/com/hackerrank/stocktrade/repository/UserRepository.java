package com.hackerrank.stocktrade.repository;

import java.util.List;
import java.util.Optional;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;

public interface UserRepository {

	boolean existsById(Long actorId);

	List<User> getActorsOrderedByMaximumStreak();

	Optional<Trade> findById(Long id);

	List<User> getActorsOrderedByTotalNumberOfEvents();

	Object save(User user);
}
