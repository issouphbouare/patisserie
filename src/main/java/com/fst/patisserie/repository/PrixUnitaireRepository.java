package com.fst.patisserie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.patisserie.model.PrixUnitaire;
import com.fst.patisserie.model.Produit;
@Repository
public interface PrixUnitaireRepository extends JpaRepository<PrixUnitaire, Integer> {
public List<PrixUnitaire> getPrixUnitaireByProduit(Produit produit);
}
