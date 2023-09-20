package com.fst.patisserie.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class LigneAchat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double quantite;
	private double prixUnitaire;

	private double montant;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private MatierePremiere matierePremiere;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	private Achat achat;

	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(mappedBy = "mention") private List<Filiere> filieres;
	 */

	// @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	// @JoinColumn(name = "domaine_id" )
	// private PrixUnitaire domaine;

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LigneAchat))
			return false;
		LigneAchat castOther = (LigneAchat) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());

		return result;
	}
}
