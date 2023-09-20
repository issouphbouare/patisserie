package com.fst.patisserie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fst.patisserie.model.Fournisseur;
import com.fst.patisserie.repository.FournisseurRepository;

import lombok.Data;

@Data
@Service
public class FournisseurService implements FournisseurRepository{
	@Autowired
	private FournisseurRepository fournisseurRepository;
	@Override
	public List<Fournisseur> findAll() {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll();
	}

	@Override
	public List<Fournisseur> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll(sort);
	}

	@Override
	public List<Fournisseur> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAllById(ids);
	}

	@Override
	public <S extends Fournisseur> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return fournisseurRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		fournisseurRepository.flush();
		
	}

	@Override
	public <S extends Fournisseur> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return fournisseurRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Fournisseur> entities) {
		// TODO Auto-generated method stub
		fournisseurRepository.deleteInBatch(entities);
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		fournisseurRepository.deleteAllInBatch();
		
	}

	@Override
	public Fournisseur getOne(Integer id) {
		// TODO Auto-generated method stub
		return fournisseurRepository.getOne(id);
	}

	@Override
	public <S extends Fournisseur> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll(example);
	}

	@Override
	public <S extends Fournisseur> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll(example, sort);
	}

	@Override
	public Page<Fournisseur> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll(pageable);
	}

	@Override
	public <S extends Fournisseur> S save(S entity) {
		// TODO Auto-generated method stub
		return fournisseurRepository.save(entity);
	}

	@Override
	public Optional<Fournisseur> findById(Integer id) {
		// TODO Auto-generated method stub
		return fournisseurRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return fournisseurRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return fournisseurRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		fournisseurRepository.deleteById(id);
		
	}

	@Override
	public void delete(Fournisseur entity) {
		// TODO Auto-generated method stub
		fournisseurRepository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends Fournisseur> entities) {
		// TODO Auto-generated method stub
		fournisseurRepository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		fournisseurRepository.deleteAll();
		
	}

	@Override
	public <S extends Fournisseur> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return fournisseurRepository.findOne(example);
	}

	@Override
	public <S extends Fournisseur> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return fournisseurRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Fournisseur> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return fournisseurRepository.count(example);
	}

	@Override
	public <S extends Fournisseur> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return fournisseurRepository.exists(example);
	}
	
	
}
