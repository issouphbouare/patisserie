package com.fst.patisserie.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.patisserie.model.Users;
import com.fst.patisserie.model.Vente;
@Repository
public interface VenteRepository extends JpaRepository<Vente, Integer> {
	public List<Vente> findByUsers(Users users);
	public List<Vente> findByDateVente(Date date);
	public List<Vente> findByDateVenteAndUsers(Date date, Users users);
	
	public List<Vente> findByUsersOrderByIdDesc(Users users);
	public List<Vente> findAllByOrderByIdDesc();

}
