package com.hackerrank.stocktrade.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.UserRepository;

@Service
public class ActorServiceImpl implements ActorService {

	@Autowired
	private UserRepository actorRepository;

	@Override
	public List<User> getActorsOrderedByMaximumStreak() {
		return actorRepository.getActorsOrderedByMaximumStreak();
	}

	
	@Override
	public List<User> getActorsOrderedByTotalNumberOfEvents() {
		return actorRepository.getActorsOrderedByTotalNumberOfEvents();
	}

	@Override
	public Optional<Object> update(User user) {
		// TODO Auto-generated method stub
		Optional<Trade> persistedActor = actorRepository.findById(user.getId());
		if (persistedActor.isPresent()) {
			if (Objects.equals(user.getId(), persistedActor.get().getLogin())) {
				return Optional.ofNullable(actorRepository.save(user));
			} else {
				throw new BadRequestException();
			}
		} else {
			throw new NotFoundException();
		}
	}
}
