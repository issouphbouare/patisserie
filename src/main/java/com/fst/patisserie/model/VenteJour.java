package com.fst.patisserie.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VenteJour {
	private Date date;

	private double montant;

	private int nombreVente;
	

}
