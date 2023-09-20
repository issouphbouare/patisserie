package com.fst.patisserie.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor


public class Vente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private double montant;

	@Temporal(TemporalType.DATE)
	@Column(columnDefinition = "Date DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Date dateVente;

	

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Users users;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Client client;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vente",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<LigneVente> ligneVentes;


	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Vente))
			return false;
		Vente castOther = (Vente) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());

		return result;
	}

	@Override
	public String toString() {
		return "Vente [id=" + id + ", montant=" + montant + ", dateVente=" + dateVente + ", users=" + users
				+ ", client=" + client + "]";
	}

}
