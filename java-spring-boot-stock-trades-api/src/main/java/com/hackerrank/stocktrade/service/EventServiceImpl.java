package com.hackerrank.stocktrade.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private TradeRepository eventRepository;

	@Autowired
	private UserRepository actorRepository;

	@Override
	public List<Trade> getEventsByActorId(Long actorId) {
		if (!actorRepository.existsById(actorId)) {
			throw new NotFoundException();
		} else {
			User actor = new User();
			actor.setId(actorId);

			Trade event = new Trade();
			event.setUser(actor);

			return eventRepository.findAll(Example.of(event), sortAscendingOrderById());
		}
	}

	@Override
	public Optional<Object> save(Trade event) {
		if (!eventRepository.existsById(event.getId())) {
			return Optional.ofNullable(eventRepository.save(event));
		} else {
			throw new BadRequestException();
		}
	}

	@Override
	public List<Trade> findAll() {
		return eventRepository.findAll(sortAscendingOrderById());
	}

	private Sort sortAscendingOrderById() {
		return new Sort(Direction.ASC);
	}

	@Override
	public void deleteAll() {
		eventRepository.deleteAll();
	}
}
