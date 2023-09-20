package com.fst.patisserie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.patisserie.model.MatierePremiere;
@Repository
public interface MatierePremiereRepository extends JpaRepository<MatierePremiere, Integer> {

}
