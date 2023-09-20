package com.fst.patisserie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fst.patisserie.model.Produit;
import com.fst.patisserie.repository.ProduitRepository;

import lombok.Data;

@Data
@Service
public class ProduitService implements ProduitRepository{
	@Autowired
	private ProduitRepository produitRepository;
	@Override
	public List<Produit> findAll() {
		// TODO Auto-generated method stub
		return produitRepository.findAll();
	}

	@Override
	public List<Produit> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return produitRepository.findAll(sort);
	}

	@Override
	public List<Produit> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return produitRepository.findAllById(ids);
	}

	@Override
	public <S extends Produit> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return produitRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		produitRepository.flush();
		
	}

	@Override
	public <S extends Produit> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return produitRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<Produit> entities) {
		// TODO Auto-generated method stub
		produitRepository.deleteInBatch(entities);
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		produitRepository.deleteAllInBatch();
		
	}

	@Override
	public Produit getOne(Integer id) {
		// TODO Auto-generated method stub
		return produitRepository.getOne(id);
	}

	@Override
	public <S extends Produit> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return produitRepository.findAll(example);
	}

	@Override
	public <S extends Produit> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return produitRepository.findAll(example, sort);
	}

	@Override
	public Page<Produit> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return produitRepository.findAll(pageable);
	}

	@Override
	public <S extends Produit> S save(S entity) {
		// TODO Auto-generated method stub
		return produitRepository.save(entity);
	}

	@Override
	public Optional<Produit> findById(Integer id) {
		// TODO Auto-generated method stub
		return produitRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return produitRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return produitRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		produitRepository.deleteById(id);
		
	}

	@Override
	public void delete(Produit entity) {
		// TODO Auto-generated method stub
		produitRepository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends Produit> entities) {
		// TODO Auto-generated method stub
		produitRepository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		produitRepository.deleteAll();
		
	}

	@Override
	public <S extends Produit> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return produitRepository.findOne(example);
	}

	@Override
	public <S extends Produit> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return produitRepository.findAll(example, pageable);
	}

	@Override
	public <S extends Produit> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return produitRepository.count(example);
	}

	@Override
	public <S extends Produit> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return produitRepository.exists(example);
	}
	
	
}
