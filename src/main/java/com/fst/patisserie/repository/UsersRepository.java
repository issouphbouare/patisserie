package com.fst.patisserie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fst.patisserie.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	

	
	  boolean existsByEmail(String email);
	  
	  boolean existsByUsername(String username);
	  
	  boolean existsByTelephone(String telephone);
	  
	  Users findByEmail(String email);
	  
	  Users findByUsername(String username);
	  
	  Users findByTelephone(String telephone);
	  
	  //List<Users> findAllByOrderByNomAsc();
	 

}
