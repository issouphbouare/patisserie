package com.fst.patisserie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fst.patisserie.model.Achat;
import com.fst.patisserie.model.LigneAchat;
@Repository
public interface LigneAchatRepository extends JpaRepository<LigneAchat, Integer> {
public List<LigneAchat> getLigneAchatByAchat(Achat achat);
}
