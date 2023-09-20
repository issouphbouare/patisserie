package com.fst.patisserie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fst.patisserie.model.LigneVente;
import com.fst.patisserie.model.Vente;
import com.fst.patisserie.repository.LigneVenteRepository;

import lombok.Data;

@Data
@Service
public class LigneVenteService implements LigneVenteRepository{
	@Autowired
	private LigneVenteRepository ligneVenteRepository;
	@Override
	public List<LigneVente> findAll() {
		// TODO Auto-generated method stub
		return ligneVenteRepository.findAll();
	}

	@Override
	public List<LigneVente> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.findAll(sort);
	}

	@Override
	public List<LigneVente> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.findAllById(ids);
	}

	@Override
	public <S extends LigneVente> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		ligneVenteRepository.flush();
		
	}

	@Override
	public <S extends LigneVente> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<LigneVente> entities) {
		// TODO Auto-generated method stub
		ligneVenteRepository.deleteInBatch(entities);
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		ligneVenteRepository.deleteAllInBatch();
		
	}

	@Override
	public LigneVente getOne(Integer id) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.getOne(id);
	}

	@Override
	public <S extends LigneVente> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.findAll(example);
	}

	@Override
	public <S extends LigneVente> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.findAll(example, sort);
	}

	@Override
	public Page<LigneVente> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.findAll(pageable);
	}

	@Override
	public <S extends LigneVente> S save(S entity) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.save(entity);
	}

	@Override
	public Optional<LigneVente> findById(Integer id) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return ligneVenteRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		ligneVenteRepository.deleteById(id);
		
	}

	@Override
	public void delete(LigneVente entity) {
		// TODO Auto-generated method stub
		ligneVenteRepository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends LigneVente> entities) {
		// TODO Auto-generated method stub
		ligneVenteRepository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		ligneVenteRepository.deleteAll();
		
	}

	@Override
	public <S extends LigneVente> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.findOne(example);
	}

	@Override
	public <S extends LigneVente> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.findAll(example, pageable);
	}

	@Override
	public <S extends LigneVente> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.count(example);
	}

	@Override
	public <S extends LigneVente> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.exists(example);
	}

	@Override
	public List<LigneVente> getLigneVenteByVente(Vente vente) {
		// TODO Auto-generated method stub
		return ligneVenteRepository.getLigneVenteByVente(vente);
	}
	
	
	
	/*
	 * @Autowired private DomaineRepository domaineRepository;
	 * 
	 * public Domaine getDomaine(final int id) { return
	 * domaineRepository.getOne(id); }
	 * 
	 * public Iterable<Domaine> getDomaines() { return domaineRepository.findAll();
	 * }
	 * 
	 * public void deleteDepartement(final int id) {
	 * domaineRepository.deleteById(id); }
	 * 
	 * public Domaine saveDomainet(Domaine domaine) {
	 * 
	 * return domaineRepository.save(domaine); }
	 */
}
