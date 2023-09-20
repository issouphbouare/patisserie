package com.fst.patisserie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fst.patisserie.model.Client;
import com.fst.patisserie.repository.ClientRepository;

import lombok.Data;

@Data
@Service
public class ClientService implements ClientRepository{
	@Autowired
	private ClientRepository clientRepository;
	@Override
	public List<Client> findAll() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	@Override
	public List<Client> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return clientRepository.findAll(sort);
	}

	@Override
	public List<Client> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return clientRepository.findAllById(ids);
	}

	@Override
	public <S extends Client> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return clientRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		clientRepository.flush();
		
	}

	@Override
	public <S extends Client> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return clientRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Client> entities) {
		// TODO Auto-generated method stub
		clientRepository.deleteInBatch(entities);
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		clientRepository.deleteAllInBatch();
		
	}

	@Override
	public Client getOne(Integer id) {
		// TODO Auto-generated method stub
		return clientRepository.getOne(id);
	}

	@Override
	public <S extends Client> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return clientRepository.findAll(example);
	}

	@Override
	public <S extends Client> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return clientRepository.findAll(example, sort);
	}

	@Override
	public Page<Client> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clientRepository.findAll(pageable);
	}

	@Override
	public <S extends Client> S save(S entity) {
		// TODO Auto-generated method stub
		return clientRepository.save(entity);
	}

	@Override
	public Optional<Client> findById(Integer id) {
		// TODO Auto-generated method stub
		return clientRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return clientRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return clientRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		clientRepository.deleteById(id);
		
	}

	@Override
	public void delete(Client entity) {
		// TODO Auto-generated method stub
		clientRepository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends Client> entities) {
		// TODO Auto-generated method stub
		clientRepository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		clientRepository.deleteAll();
		
	}

	@Override
	public <S extends Client> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return clientRepository.findOne(example);
	}

	@Override
	public <S extends Client> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return clientRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Client> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return clientRepository.count(example);
	}

	@Override
	public <S extends Client> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return clientRepository.exists(example);
	}
	
	
}
