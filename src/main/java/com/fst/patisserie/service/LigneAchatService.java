package com.fst.patisserie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fst.patisserie.model.Achat;
import com.fst.patisserie.model.LigneAchat;
import com.fst.patisserie.repository.LigneAchatRepository;

import lombok.Data;

@Data
@Service
public class LigneAchatService implements LigneAchatRepository{
	@Autowired
	private LigneAchatRepository ligneAchatRepository;
	@Override
	public List<LigneAchat> findAll() {
		// TODO Auto-generated method stub
		return ligneAchatRepository.findAll();
	}

	@Override
	public List<LigneAchat> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.findAll(sort);
	}

	@Override
	public List<LigneAchat> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.findAllById(ids);
	}

	@Override
	public <S extends LigneAchat> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		ligneAchatRepository.flush();
		
	}

	@Override
	public <S extends LigneAchat> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<LigneAchat> entities) {
		// TODO Auto-generated method stub
		ligneAchatRepository.deleteInBatch(entities);
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		ligneAchatRepository.deleteAllInBatch();
		
	}

	@Override
	public LigneAchat getOne(Integer id) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.getOne(id);
	}

	@Override
	public <S extends LigneAchat> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.findAll(example);
	}

	@Override
	public <S extends LigneAchat> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.findAll(example, sort);
	}

	@Override
	public Page<LigneAchat> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.findAll(pageable);
	}

	@Override
	public <S extends LigneAchat> S save(S entity) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.save(entity);
	}

	@Override
	public Optional<LigneAchat> findById(Integer id) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return ligneAchatRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		ligneAchatRepository.deleteById(id);
		
	}

	@Override
	public void delete(LigneAchat entity) {
		// TODO Auto-generated method stub
		ligneAchatRepository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends LigneAchat> entities) {
		// TODO Auto-generated method stub
		ligneAchatRepository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		ligneAchatRepository.deleteAll();
		
	}

	@Override
	public <S extends LigneAchat> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.findOne(example);
	}

	@Override
	public <S extends LigneAchat> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.findAll(example, pageable);
	}

	@Override
	public <S extends LigneAchat> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.count(example);
	}

	@Override
	public <S extends LigneAchat> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.exists(example);
	}

	@Override
	public List<LigneAchat> getLigneAchatByAchat(Achat achat) {
		// TODO Auto-generated method stub
		return ligneAchatRepository.getLigneAchatByAchat(achat);
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
