package com.fst.patisserie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fst.patisserie.model.Users;
import com.fst.patisserie.repository.UsersRepository;

import lombok.Data;

@Data
@Service
public class UsersService implements UsersRepository{
	@Autowired
	private UsersRepository usersRepository;
	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return usersRepository.findAll();
	}

	@Override
	public List<Users> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return usersRepository.findAll(sort);
	}

	@Override
	public List<Users> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return usersRepository.findAllById(ids);
	}

	@Override
	public <S extends Users> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return usersRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		usersRepository.flush();
		
	}

	@Override
	public <S extends Users> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return usersRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Users> entities) {
		// TODO Auto-generated method stub
		usersRepository.deleteInBatch(entities);
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		usersRepository.deleteAllInBatch();
		
	}

	@Override
	public Users getOne(Integer id) {
		// TODO Auto-generated method stub
		return usersRepository.getOne(id);
	}

	@Override
	public <S extends Users> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return usersRepository.findAll(example);
	}

	@Override
	public <S extends Users> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return usersRepository.findAll(example, sort);
	}

	@Override
	public Page<Users> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return usersRepository.findAll(pageable);
	}

	@Override
	public <S extends Users> S save(S entity) {
		// TODO Auto-generated method stub
		return usersRepository.save(entity);
	}

	@Override
	public Optional<Users> findById(Integer id) {
		// TODO Auto-generated method stub
		return usersRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return usersRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return usersRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		usersRepository.deleteById(id);
		
	}

	@Override
	public void delete(Users entity) {
		// TODO Auto-generated method stub
		usersRepository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends Users> entities) {
		// TODO Auto-generated method stub
		usersRepository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		usersRepository.deleteAll();
		
	}

	@Override
	public <S extends Users> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return usersRepository.findOne(example);
	}

	@Override
	public <S extends Users> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return usersRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Users> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return usersRepository.count(example);
	}

	@Override
	public <S extends Users> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return usersRepository.exists(example);
	}

	
	  @Override
	  public boolean existsByEmail(String email) {
		  // TODO Auto-generatedmethod stub 
		  return usersRepository.existsByEmail(email);
		  }
	  
	  @Override
	  public boolean existsByUsername(String username) { 
		  // TODO Auto-generated method stub 
		  return usersRepository.existsByUsername(username); 
		  }
	  
	  @Override
	  public boolean existsByTelephone(String telephone) { 
		  // TODO Auto-generated method stub 
		  return usersRepository.existsByTelephone(telephone);
		  }
	  
	  @Override 
	  public Users findByEmail(String email)
	  { // TODO Auto-generated method stub
	  return usersRepository.findByEmail(email);
	  }
	  
	  @Override
	  public Users findByUsername(String username)  {
		  // TODO Auto-generated method stub 
		  return usersRepository.findByUsername(username); 
		  }
	  
	  
	  @Override 
	  public Users findByTelephone(String telephone) { 
		  // TODO Auto-generated method stub 
		  return usersRepository.findByTelephone(telephone); 
		  }
	 
	
	
}
