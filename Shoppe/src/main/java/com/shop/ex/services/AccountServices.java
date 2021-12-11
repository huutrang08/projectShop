package com.shop.ex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.shop.ex.domain.Acc;

public interface AccountServices {

	<S extends Acc> List<S> findAll(Example<S> example, Sort sort);

	<S extends Acc> List<S> findAll(Example<S> example);

	Acc getById(String id);

	void deleteAll();

	void deleteAll(Iterable<? extends Acc> entities);

	Acc getOne(String id);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends String> ids);

	void delete(Acc entity);

	void deleteAllByIdInBatch(Iterable<String> ids);

	void deleteById(String id);

	long count();

	void deleteAllInBatch(Iterable<Acc> entities);

	<S extends Acc> boolean exists(Example<S> example);

	<S extends Acc> long count(Example<S> example);

	void deleteInBatch(Iterable<Acc> entities);

	<S extends Acc> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Acc> List<S> saveAllAndFlush(Iterable<S> entities);

	boolean existsById(String id);

	<S extends Acc> S saveAndFlush(S entity);

	void flush();

	<S extends Acc> List<S> saveAll(Iterable<S> entities);

	Optional<Acc> findById(String id);

	List<Acc> findAllById(Iterable<String> ids);

	List<Acc> findAll(Sort sort);

	List<Acc> findAll();

	Page<Acc> findAll(Pageable pageable);

	<S extends Acc> Optional<S> findOne(Example<S> example);

	<S extends Acc> S save(S entity);

	Acc login(String username, String password);

}
