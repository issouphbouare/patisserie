package com.fst.patisserie.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VenteAnnee {
	private Date date;
	private int annee;
	private double montant;
	private int nombreVente;
	

}
