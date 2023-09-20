package com.fst.patisserie.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.patisserie.model.Achat;
import com.fst.patisserie.model.Users;
import com.fst.patisserie.model.Vente;
@Repository
public interface AchatRepository extends JpaRepository<Achat, Integer> {
	public List<Achat> findByUsers(Users users);
	public List<Achat> findByDateAchat(Date date);
	public List<Achat> findByDateAchatAndUsers(Date date, Users users);
	
	public List<Achat> findByUsersOrderByIdDesc(Users users);
	public List<Achat> findAllByOrderByIdDesc();


}
