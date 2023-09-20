package com.fst.patisserie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.patisserie.model.Produit;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {

}
