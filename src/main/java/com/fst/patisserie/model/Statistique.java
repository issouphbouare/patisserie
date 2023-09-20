package com.fst.patisserie.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Statistique {
	private Date date;

	private double montantVente;

	private int nombreVente;
	
	private double montantAchat;

	private int nombreAchat;
	
	private double bilan;

	
	

}
