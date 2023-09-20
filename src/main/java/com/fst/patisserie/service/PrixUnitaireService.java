package com.fst.patisserie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fst.patisserie.model.PrixUnitaire;
import com.fst.patisserie.model.Produit;
import com.fst.patisserie.repository.PrixUnitaireRepository;

import lombok.Data;

@Data
@Service
public class PrixUnitaireService implements PrixUnitaireRepository{
	@Autowired
	private PrixUnitaireRepository prixUnitaireRepository;
	@Override
	public List<PrixUnitaire> findAll() {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.findAll();
	}

	@Override
	public List<PrixUnitaire> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.findAll(sort);
	}

	@Override
	public List<PrixUnitaire> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.findAllById(ids);
	}

	@Override
	public <S extends PrixUnitaire> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		prixUnitaireRepository.flush();
		
	}

	@Override
	public <S extends PrixUnitaire> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<PrixUnitaire> entities) {
		// TODO Auto-generated method stub
		prixUnitaireRepository.deleteInBatch(entities);
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		prixUnitaireRepository.deleteAllInBatch();
		
	}

	@Override
	public PrixUnitaire getOne(Integer id) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.getOne(id);
	}

	@Override
	public <S extends PrixUnitaire> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.findAll(example);
	}

	@Override
	public <S extends PrixUnitaire> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.findAll(example, sort);
	}

	@Override
	public Page<PrixUnitaire> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.findAll(pageable);
	}

	@Override
	public <S extends PrixUnitaire> S save(S entity) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.save(entity);
	}

	@Override
	public Optional<PrixUnitaire> findById(Integer id) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		prixUnitaireRepository.deleteById(id);
		
	}

	@Override
	public void delete(PrixUnitaire entity) {
		// TODO Auto-generated method stub
		prixUnitaireRepository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends PrixUnitaire> entities) {
		// TODO Auto-generated method stub
		prixUnitaireRepository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		prixUnitaireRepository.deleteAll();
		
	}

	@Override
	public <S extends PrixUnitaire> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.findOne(example);
	}

	@Override
	public <S extends PrixUnitaire> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.findAll(example, pageable);
	}

	@Override
	public <S extends PrixUnitaire> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.count(example);
	}

	@Override
	public <S extends PrixUnitaire> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return prixUnitaireRepository.exists(example);
	}

	@Override
	public List<PrixUnitaire> getPrixUnitaireByProduit(Produit produit) {
		
		return prixUnitaireRepository.getPrixUnitaireByProduit(produit);
	}
	
	
}
