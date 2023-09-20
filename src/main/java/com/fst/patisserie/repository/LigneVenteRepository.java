package com.fst.patisserie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.patisserie.model.Vente;
import com.fst.patisserie.model.LigneVente;
@Repository
public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
public List<LigneVente> getLigneVenteByVente(Vente vente);
}
