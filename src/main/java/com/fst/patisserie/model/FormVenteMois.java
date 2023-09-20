package com.fst.patisserie.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class FormVenteMois {
	private Date date;
	private int annee;
	private int mois;
	private double montant;
	private int nombreVente;
	private Users users;

}
