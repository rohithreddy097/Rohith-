package com.hackerrank.stocktrade.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.hackerrank.stocktrade.model.Trade;

public interface TradeRepository {

	List<Trade> findAll(Example<Trade> of, Sort sortAscendingOrderById);

	boolean existsById(Long id);

	List<Trade> findAll(Sort sortAscendingOrderById);

	void deleteAll();

	Object save(Trade event);
}
