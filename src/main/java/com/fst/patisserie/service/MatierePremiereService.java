package com.fst.patisserie.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fst.patisserie.model.MatierePremiere;
import com.fst.patisserie.repository.MatierePremiereRepository;

import lombok.Data;

@Data
@Service
public class MatierePremiereService implements MatierePremiereRepository{
	@Autowired
	private MatierePremiereRepository matierePremiereRepository;
	@Override
	public List<MatierePremiere> findAll() {
		// TODO Auto-generated method stub
		return matierePremiereRepository.findAll();
	}

	@Override
	public List<MatierePremiere> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.findAll(sort);
	}

	@Override
	public List<MatierePremiere> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.findAllById(ids);
	}

	@Override
	public <S extends MatierePremiere> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		matierePremiereRepository.flush();
		
	}

	@Override
	public <S extends MatierePremiere> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.saveAndFlush(entity);
	}

	@Override
	public void deleteInBatch(Iterable<MatierePremiere> entities) {
		// TODO Auto-generated method stub
		matierePremiereRepository.deleteInBatch(entities);
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		matierePremiereRepository.deleteAllInBatch();
		
	}

	@Override
	public MatierePremiere getOne(Integer id) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.getOne(id);
	}

	@Override
	public <S extends MatierePremiere> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.findAll(example);
	}

	@Override
	public <S extends MatierePremiere> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.findAll(example, sort);
	}

	@Override
	public Page<MatierePremiere> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.findAll(pageable);
	}

	@Override
	public <S extends MatierePremiere> S save(S entity) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.save(entity);
	}

	@Override
	public Optional<MatierePremiere> findById(Integer id) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.existsById(id);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return matierePremiereRepository.count();
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		matierePremiereRepository.deleteById(id);
		
	}

	@Override
	public void delete(MatierePremiere entity) {
		// TODO Auto-generated method stub
		matierePremiereRepository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends MatierePremiere> entities) {
		// TODO Auto-generated method stub
		matierePremiereRepository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		matierePremiereRepository.deleteAll();
		
	}

	@Override
	public <S extends MatierePremiere> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.findOne(example);
	}

	@Override
	public <S extends MatierePremiere> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.findAll(example, pageable);
	}

	@Override
	public <S extends MatierePremiere> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.count(example);
	}

	@Override
	public <S extends MatierePremiere> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return matierePremiereRepository.exists(example);
	}
	
	
}
