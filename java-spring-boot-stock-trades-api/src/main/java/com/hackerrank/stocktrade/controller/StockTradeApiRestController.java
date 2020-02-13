package com.hackerrank.stocktrade.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.events.Event;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.service.ActorService;
import com.hackerrank.stocktrade.service.EventService;





@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class StockTradeApiRestController {

	@Autowired
	private ActorService actorService;

	@Autowired
	private EventService eventService;

	@Autowired
	private ModelMapper mapper;

	/**
	 * Delete All Events
	 * 
	 * The service should be able to erase all the events by the DELETE request at
	 * /erase. The HTTP response code should be 200.
	 * 
	 * @return {@link ResponseEntity}
	 */
	@DeleteMapping("/erase")
	public ResponseEntity<Void> deleteAllEvents() {
		eventService.deleteAll();
		return ResponseEntity.ok().build();
	}

	/**
	 * Save Event
	 * 
	 * The service should be able to add a new event by the POST request at /events.
	 * The event JSON is sent in the request body. If an event with the same id
	 * already exists then the HTTP response code should be 400, otherwise, the
	 * response code should be 201.
	 * 
	 * @param eventDTO
	 * @return {@link ResponseEntity}
	 */
	@PostMapping(path = "/trades", consumes = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> saveEvent(@RequestBody Trade eventDTO) {
		eventService.save(mapper.map(eventDTO, Trade.class));
		return ResponseEntity.status(CREATED).build();
	}

	/**
	 * Find All Events
	 * 
	 * The service should be able to return the JSON array of all the events by the
	 * GET request at /events. The HTTP response code should be 200. The JSON array
	 * should be sorted in ascending order by event ID.
	 * 
	 * @return {@link ResponseEntity} List of Events
	 */
	@GetMapping("/events")
	public ResponseEntity<List<Trade>> getEvents() {
		Iterable<Trade> iterable = eventService.findAll();

		List<Trade> events = new ArrayList<>();
		iterable.forEach(event -> events.add(mapper.map(event, Trade.class)));

		return ResponseEntity.ok().body(events);
	}

	/**
	 * Get Events By Actor Id
	 * 
	 * The service should be able to return the JSON array of all the events which
	 * are performed by the actor ID by the GET request at /events/actors/{actorID}.
	 * If the requested actor does not exist then HTTP response code should be 404,
	 * otherwise, the response code should be 200. The JSON array should be sorted
	 * in ascending order by event ID.
	 * 
	 * @param actorId
	 * @return {@link ResponseEntity} List of Events
	 */
	@GetMapping("/trades/users/{id}")
	public ResponseEntity<List<Trade>> getEventsByActorId(@PathVariable("id") Long actorId) {
		List<Trade> events = eventService.getEventsByActorId(actorId);

		List<Trade> eventsDTO = events.stream().map(e -> mapper.map(e, Trade.class)).collect(Collectors.toList());

		return ResponseEntity.ok().body(eventsDTO);
	}

	/**
	 * Update the actor
	 * 
	 * The service should be able to update the avatar URL of the actor by the PUT
	 * request at /actors. The actor JSON is sent in the request body. If the actor
	 * with the id does not exist then the response code should be 404, or if there
	 * are other fields being updated for the actor then the HTTP response code
	 * should be 400, otherwise, the response code should be 200.
	 * 
	 * @param actor
	 * @return {@link ResponseEntity}
	 */
	@PutMapping(path = "/actors", consumes = APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> updateActor(@RequestBody User actorDTO) {
		actorService.update(mapper.map(actorDTO, User.class));
		return ResponseEntity.ok().build();
	}

	/**
	 * Get Actors Ordered By Total Number Of Events
	 * 
	 * The service should be able to return the JSON array of all the actors sorted
	 * by the total number of associated events with each actor in descending order
	 * by the GET request at /actors. If there are more than one actors with the
	 * same number of events, then order them by the timestamp of the latest event
	 * in the descending order. If more than one actors have the same timestamp for
	 * the latest event, then order them by the alphabetical order of login. The
	 * HTTP response code should be 200.
	 * 
	 * @return {@link ResponseEntity} List of Actors
	 */
	@GetMapping("/stocks/ACC/price")
	public ResponseEntity<List<User>> getActorsOrderedByTotalNumberOfEvents() {
		Iterable<User> iterable = actorService.getActorsOrderedByTotalNumberOfEvents();

		List<User> actors = new ArrayList<>();
		iterable.forEach(actor -> actors.add(mapper.map(actor, User.class)));

		return ResponseEntity.ok().body(actors);
	}

	/**
	 * Get Actor Ordered By Maximum Streak
	 * 
	 * The service should be able to return the JSON array of all the actors sorted
	 * by the maximum streak (i.e., the total number of consecutive days actor has
	 * pushed an event to the system) in descending order by the GET request at
	 * /actors/streak. If there are more than one actors with the same maximum
	 * streak, then order them by the timestamp of the latest event in the
	 * descending order. If more than one actors have the same timestamp for the
	 * latest event, then order them by the alphabetical order of login. The HTTP
	 * response code should be 200.
	 * 
	 * @return {@link ResponseEntity} List of Actors
	 */
	@GetMapping("/stocks/stats")
	public ResponseEntity<List<User>> getActorsOrderedByMaximumStreak() {
		List<User> actors = actorService.getActorsOrderedByMaximumStreak();

		List<User> actorsDTO = actors.stream().map(a -> mapper.map(a, User.class)).collect(Collectors.toList());

		return ResponseEntity.ok().body(actorsDTO);
	}
}