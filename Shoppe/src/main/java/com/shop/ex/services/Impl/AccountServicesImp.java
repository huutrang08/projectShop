package com.shop.ex.services.Impl;

import java.util.List;
import java.util.Optional;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shop.ex.domain.Acc;
import com.shop.ex.reponsitory.AccountRepository;
import com.shop.ex.services.AccountServices;

@Service
public class AccountServicesImp implements AccountServices {
    @Autowired
	private AccountRepository accountRepository;
    
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
	public Acc login(String username,String password) {
    	Optional<Acc> optional = findById(username);
    	if (optional.isPresent() && bCryptPasswordEncoder.matches(password, optional.get().getPassword())) {
			optional.get().setPassword("");
    		return optional.get();
		}
    	return null;
    }
    
    
	@Override
	public <S extends Acc> S save(S entity) {
		Optional<Acc> acc = findById(entity.getUsername());
		if (acc.isPresent()) {
			if (StringUtils.isEmpty(entity.getPassword())) {
				entity.setPassword(acc.get().getPassword());
			}
			else {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			}
		}
		else {
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		}
		return accountRepository.save(entity);
	}

	@Override
	public <S extends Acc> Optional<S> findOne(Example<S> example) {
		return accountRepository.findOne(example);
	}

	@Override
	public Page<Acc> findAll(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	@Override
	public List<Acc> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public List<Acc> findAll(Sort sort) {
		return accountRepository.findAll(sort);
	}

	@Override
	public List<Acc> findAllById(Iterable<String> ids) {
		return accountRepository.findAllById(ids);
	}

	@Override
	public Optional<Acc> findById(String id) {
		return accountRepository.findById(id);
	}

	@Override
	public <S extends Acc> List<S> saveAll(Iterable<S> entities) {
		return accountRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		accountRepository.flush();
	}

	@Override
	public <S extends Acc> S saveAndFlush(S entity) {
		return accountRepository.saveAndFlush(entity);
	}

	@Override
	public boolean existsById(String id) {
		return accountRepository.existsById(id);
	}

	@Override
	public <S extends Acc> List<S> saveAllAndFlush(Iterable<S> entities) {
		return accountRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Acc> Page<S> findAll(Example<S> example, Pageable pageable) {
		return accountRepository.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Acc> entities) {
		accountRepository.deleteInBatch(entities);
	}

	@Override
	public <S extends Acc> long count(Example<S> example) {
		return accountRepository.count(example);
	}

	@Override
	public <S extends Acc> boolean exists(Example<S> example) {
		return accountRepository.exists(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Acc> entities) {
		accountRepository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return accountRepository.count();
	}

	@Override
	public void deleteById(String id) {
		accountRepository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		accountRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Acc entity) {
		accountRepository.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		accountRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		accountRepository.deleteAllInBatch();
	}

	@Override
	public Acc getOne(String id) {
		return accountRepository.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Acc> entities) {
		accountRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		accountRepository.deleteAll();
	}

	@Override
	public Acc getById(String id) {
		return accountRepository.getById(id);
	}

	@Override
	public <S extends Acc> List<S> findAll(Example<S> example) {
		return accountRepository.findAll(example);
	}

	@Override
	public <S extends Acc> List<S> findAll(Example<S> example, Sort sort) {
		return accountRepository.findAll(example, sort);
	}
}
