package com.fst.patisserie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.patisserie.model.Fournisseur;
@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {

}
