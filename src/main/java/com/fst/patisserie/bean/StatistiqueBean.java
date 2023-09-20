package com.fst.patisserie.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.springframework.stereotype.Component;

import com.fst.patisserie.model.FormAchat;
import com.fst.patisserie.model.FormAchatByUser;
import com.fst.patisserie.model.FormVenteAnnee;
import com.fst.patisserie.model.FormVenteJour;
import com.fst.patisserie.model.FormVenteMois;
import com.fst.patisserie.model.Statistique;
import com.fst.patisserie.model.StatistiqueByUser;
import com.fst.patisserie.model.Users;
import com.fst.patisserie.model.VenteAnnee;
import com.fst.patisserie.model.VenteJour;
import com.fst.patisserie.model.VenteMois;
import com.fst.patisserie.service.AchatService;
import com.fst.patisserie.service.VenteService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class StatistiqueBean {
	private List<Statistique> statistiquesJr;
	private List<Statistique> statistiquesMois;
	private List<Statistique> statistiquesAnnee;
	
	private List<StatistiqueByUser> statistiquesJrByUsers;
	private List<StatistiqueByUser> statistiquesMoisByUsers;
	private List<StatistiqueByUser> statistiquesAnneeByUsers;
	
	private List<StatistiqueByUser> statistiquesJrByAgCur;
	private List<StatistiqueByUser> statistiquesMoisByAgCur;
	private List<StatistiqueByUser> statistiquesAnneeByAgCur;

	
	
	// Declarations pour les statistiquess
		private String urlListJr = "/statistiqueJr.xhtml";
		private String urlListJrByUser = "/statistiqueJrByUser.xhtml";
		private String urlListMois = "/statistiqueMois.xhtml";
		private String urlListMoisByUser = "/statistiqueMoisByUser.xhtml";
		private String urlListAnnee = "/statistiqueAnnee.xhtml";
		private String urlListAnneeByUser = "/statistiqueAnneeByUser.xhtml";
		
		private String urlListJrByAgCur = "/statistiqueJrByAgCur.xhtml";
		private String urlListMoisByAgCur = "/statistiqueMoisByAgCur.xhtml";
		private String urlListAnneeByAgCur = "/statistiqueAnneeByAgCur.xhtml";
		

	// Pour les filtrages
	private List<Statistique> filteredJr;
	private List<StatistiqueByUser> filteredJrByUser;
	private List<Statistique> filteredMois;
	private List<StatistiqueByUser> filteredMoisByUser;
	private List<Statistique> filteredAnnee;
	private List<StatistiqueByUser> filteredAnneeByUser;
	private List<StatistiqueByUser> filteredJrAgCur;
	private List<StatistiqueByUser> filteredMoisAgCur;
	private List<StatistiqueByUser> filteredAnneeAgCur;
	private boolean globalFilterOnly;
	private List<FilterMeta> filterBy;
	private List<SortMeta> sortBy;
	
	private String date;
	private String mois;
	private String annee;

	@Inject
	private VenteService venteService;

	@Inject
	private AchatService achatService;

	@PostConstruct
	public void init() {
		this.statistiqueJour();
		this.statistiqueMois();
		this.statistiqueAnnee();
		this.statistiqueJourByUser();
		this.statistiqueMoisByUser();
		this.statistiqueAnneeByUser();
	}

	public List<Statistique> statistiqueJour() {
		List<VenteJour> venteJours = this.venteService.VentesParJour();
		List<FormAchat> achatJours = this.achatService.AchatsParJour();
		List<Statistique> liste = new ArrayList<>();

		for (VenteJour venteJour : venteJours) {
			Statistique statistique = new Statistique();
			statistique.setDate(venteJour.getDate());
			statistique.setNombreVente(venteJour.getNombreVente());
			statistique.setMontantVente(venteJour.getMontant());
			statistique.setMontantAchat(0);
			statistique.setBilan(venteJour.getMontant());
			for (FormAchat achatJour : achatJours) {
				if (venteJour.getDate().equals(achatJour.getDate())) {
					statistique.setMontantAchat(achatJour.getMontantAchat());
					statistique.setBilan(statistique.getMontantVente() - achatJour.getMontantAchat());
				}
			}

			boolean b = false;
			for (Statistique s : liste) {
				if (s.getDate().equals(statistique.getDate()) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}

		for (FormAchat achatJour : achatJours) {
			Statistique statistique = new Statistique();
			statistique.setDate(achatJour.getDate());
			statistique.setNombreVente(0);
			statistique.setMontantVente(0);
			statistique.setMontantAchat(achatJour.getMontantAchat());
			statistique.setBilan(statistique.getMontantVente() - achatJour.getMontantAchat());
			for (VenteJour venteJour : venteJours) {
				if (achatJour.getDate().equals(venteJour.getDate())) {
					statistique.setMontantVente(venteJour.getMontant());
					statistique.setBilan(statistique.getMontantVente() - achatJour.getMontantAchat());
				}
			}
			boolean b = false;
			for (Statistique s : liste) {
				if (s.getDate().equals(statistique.getDate()) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}
		this.statistiquesJr= liste;
		return this.statistiquesJr;
	}

	public List<Statistique> statistiqueMois() {
		List<VenteMois> venteMois = this.venteService.VentesParMois();
		List<FormAchat> achatMois = this.achatService.AchatsParJour();
		List<Statistique> liste = new ArrayList<>();

		for (VenteMois v : venteMois) {
			Statistique statistique = new Statistique();
			statistique.setDate(v.getDate());
			statistique.setNombreVente(v.getNombreVente());
			statistique.setMontantVente(v.getMontant());
			statistique.setMontantAchat(0);
			statistique.setBilan(v.getMontant());
			for (FormAchat a : achatMois) {
				if (v.getDate().getMonth() == a.getDate().getMonth()
						&& v.getDate().getYear() == a.getDate().getYear()) {
					statistique.setMontantAchat(a.getMontantAchat());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}

			boolean b = false;
			for (Statistique s : liste) {
				if ( s.getDate().getMonth() == statistique.getDate().getMonth()
						&& s.getDate().getYear() == statistique.getDate().getYear() || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}

		for (FormAchat a : achatMois) {
			Statistique statistique = new Statistique();
			statistique.setDate(a.getDate());
			statistique.setNombreVente(0);
			statistique.setMontantVente(0);
			statistique.setMontantAchat(a.getMontantAchat());
			statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
			for (VenteMois v : venteMois) {
				if (a.getDate().equals(v.getDate())) {
					statistique.setMontantVente(v.getMontant());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}
			boolean b = false;
			for (Statistique s : liste) {
				if (s.getDate().getMonth() == statistique.getDate().getMonth()
						&& s.getDate().getYear() == statistique.getDate().getYear() || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}
		this.statistiquesMois=liste;
		return this.statistiquesMois;
	}

	public List<Statistique> statistiqueAnnee() {
		List<VenteAnnee> venteAnnees = this.venteService.VentesParAnnees();
		List<FormAchat> achatAnnees = this.achatService.AchatsParAnnees();
		List<Statistique> liste = new ArrayList<>();

		for (VenteAnnee v : venteAnnees) {
			Statistique statistique = new Statistique();
			statistique.setDate(v.getDate());
			statistique.setNombreVente(v.getNombreVente());
			statistique.setMontantVente(v.getMontant());
			statistique.setMontantAchat(0);
			statistique.setBilan(v.getMontant());
			for (FormAchat a : achatAnnees) {
				if (v.getDate().getYear() == a.getDate().getYear()) {
					statistique.setMontantAchat(a.getMontantAchat());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}

			boolean b = false;
			for (Statistique s : liste) {
				if (s.getDate().getYear() == statistique.getDate().getYear() || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}

		for (FormAchat a : achatAnnees) {
			Statistique statistique = new Statistique();
			statistique.setDate(a.getDate());
			statistique.setNombreVente(0);
			statistique.setMontantVente(0);
			statistique.setMontantAchat(a.getMontantAchat());
			statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
			for (VenteAnnee v : venteAnnees) {
				if (a.getDate().getYear() == v.getDate().getYear()) {
					statistique.setMontantVente(v.getMontant());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}
			boolean b = false;
			for (Statistique s : liste) {
				if (s.getDate().getYear() == statistique.getDate().getYear() || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		} 
		this.statistiquesAnnee= liste;
		return this.statistiquesAnnee;
	}

	public List<StatistiqueByUser> statistiqueJourByUser() {
		List<FormVenteJour> venteJours = this.venteService.VentesParJourUser();
		List<FormAchatByUser> achatJours = this.achatService.AchatsParJourUser();
		List<StatistiqueByUser> liste = new ArrayList();

		for (FormVenteJour venteJour : venteJours) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(venteJour.getDate());
			statistique.setUsers(venteJour.getUsers());
			statistique.setNombreVente(venteJour.getNombreVente());
			statistique.setMontantVente(venteJour.getMontant());
			statistique.setMontantAchat(0);
			statistique.setBilan(venteJour.getMontant());
			for (FormAchatByUser achatJour : achatJours) {
				if (venteJour.getDate().equals(achatJour.getDate())
						&& venteJour.getUsers().equals(achatJour.getUsers())) {
					statistique.setMontantAchat(achatJour.getMontantAchat());
					statistique.setBilan(statistique.getMontantVente() - achatJour.getMontantAchat());
				}
			}

			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().equals(statistique.getDate()) && s.getUsers().equals(statistique.getUsers()))
						|| statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}

		for (FormAchatByUser achatJour : achatJours) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(achatJour.getDate());
			statistique.setUsers(achatJour.getUsers());
			statistique.setNombreVente(0);
			statistique.setMontantVente(0);
			statistique.setMontantAchat(achatJour.getMontantAchat());
			statistique.setBilan(statistique.getMontantVente() - achatJour.getMontantAchat());
			for (FormVenteJour venteJour : venteJours) {
				if (achatJour.getDate().equals(venteJour.getDate())
						&& venteJour.getUsers().equals(achatJour.getUsers())) {
					statistique.setMontantVente(venteJour.getMontant());
					statistique.setBilan(statistique.getMontantVente() - achatJour.getMontantAchat());
				}
			}
			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().equals(statistique.getDate()) && s.getUsers().equals(statistique.getUsers()))
						|| statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}
		this.statistiquesJrByUsers= liste;
		return this.statistiquesJrByUsers;
	}

	public List<StatistiqueByUser> statistiqueMoisByUser() {
		List<FormVenteMois> venteMois = this.venteService.VentesParMoisUser();
		List<FormAchatByUser> achatMois = this.achatService.AchatsParMoisUser();
		List<StatistiqueByUser> liste = new ArrayList<>();

		for (FormVenteMois v : venteMois) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(v.getDate());
			statistique.setUsers(v.getUsers());
			statistique.setNombreVente(v.getNombreVente());
			statistique.setMontantVente(v.getMontant());
			statistique.setMontantAchat(0);
			statistique.setBilan(v.getMontant());
			for (FormAchatByUser a : achatMois) {
				if (v.getDate().getMonth() == a.getDate().getMonth() && v.getUsers().equals(a.getUsers())
						&& v.getDate().getYear() == a.getDate().getYear()) {
					statistique.setMontantAchat(a.getMontantAchat());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}

			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().getMonth() == statistique.getDate().getMonth()
						&& statistique.getUsers().equals(s.getUsers())
						&& s.getDate().getYear() == statistique.getDate().getYear()) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}

		for (FormAchatByUser a : achatMois) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(a.getDate());
			statistique.setUsers(a.getUsers());
			statistique.setNombreVente(0);
			statistique.setMontantVente(0);
			statistique.setMontantAchat(a.getMontantAchat());
			statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
			for (FormVenteMois v : venteMois) {
				if (a.getDate().equals(v.getDate()) && v.getUsers().equals(a.getUsers())) {
					statistique.setMontantVente(v.getMontant());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}
			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().getMonth() == statistique.getDate().getMonth()
						&& statistique.getUsers().equals(s.getUsers())
						&& s.getDate().getYear() == statistique.getDate().getYear()) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}
		this.statistiquesMoisByUsers= liste;
		return this.statistiquesMoisByUsers;
	}

	public List<StatistiqueByUser> statistiqueAnneeByUser() {
		List<FormVenteAnnee> venteAnnees = this.venteService.VentesParAnneesUser();
		List<FormAchatByUser> achatAnnees = this.achatService.AchatsParAnneesUser();
		List<StatistiqueByUser> liste = new ArrayList<>();

		for (FormVenteAnnee v : venteAnnees) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(v.getDate());
			statistique.setUsers(v.getUsers());
			statistique.setNombreVente(v.getNombreVente());
			statistique.setMontantVente(v.getMontant());
			statistique.setMontantAchat(0);
			statistique.setBilan(v.getMontant());
			for (FormAchatByUser a : achatAnnees) {
				if (v.getDate().getYear() == a.getDate().getYear() && v.getUsers().equals(a.getUsers())) {
					statistique.setMontantAchat(a.getMontantAchat());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}

			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().getYear() == statistique.getDate().getYear()
						&& s.getUsers().equals(statistique.getUsers())) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}

		for (FormAchatByUser a : achatAnnees) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(a.getDate());
			statistique.setUsers(a.getUsers());
			statistique.setNombreVente(0);
			statistique.setMontantVente(0);
			statistique.setMontantAchat(a.getMontantAchat());
			statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
			for (FormVenteAnnee v : venteAnnees) {
				if (a.getDate().getYear() == v.getDate().getYear() && v.getUsers().equals(a.getUsers())) {
					statistique.setMontantVente(v.getMontant());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}
			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().getYear() == statistique.getDate().getYear()
						&& s.getUsers().equals(statistique.getUsers())) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}
		this.statistiquesAnneeByUsers= liste;
		return this.statistiquesAnneeByUsers;
	}

	
	public List<StatistiqueByUser> statistiqueJourAgCur(Users users) {
		List<FormVenteJour> venteJours = this.venteService.VentesParJourAgentCourant(users);
		List<FormAchatByUser> achatJours = this.achatService.AchatsParJourAgentCourant(users);
		List<StatistiqueByUser> liste = new ArrayList<>();

		for (FormVenteJour venteJour : venteJours) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(venteJour.getDate());
			statistique.setUsers(users);
			statistique.setNombreVente(venteJour.getNombreVente());
			statistique.setMontantVente(venteJour.getMontant());
			statistique.setMontantAchat(0);
			statistique.setBilan(venteJour.getMontant());
			for (FormAchatByUser achatJour : achatJours) {
				if (venteJour.getDate().equals(achatJour.getDate())) {
					statistique.setMontantAchat(achatJour.getMontantAchat());
					statistique.setBilan(statistique.getMontantVente() - achatJour.getMontantAchat());
				}
			}

			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if (s.getDate().equals(statistique.getDate()) || statistique.getBilan() == 0.0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}

		for (FormAchatByUser achatJour : achatJours) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(achatJour.getDate());
			statistique.setUsers(users);
			statistique.setNombreVente(0);
			statistique.setMontantVente(0);
			statistique.setMontantAchat(achatJour.getMontantAchat());
			statistique.setBilan(statistique.getMontantVente() - achatJour.getMontantAchat());
			for (FormVenteJour venteJour : venteJours) {
				if (achatJour.getDate().equals(venteJour.getDate())) {
					statistique.setMontantVente(venteJour.getMontant());
					statistique.setBilan(statistique.getMontantVente() - achatJour.getMontantAchat());
				}
			}
			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if (s.getDate().equals(statistique.getDate()) || statistique.getBilan() == 0.0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}
		this.statistiquesJrByAgCur= liste;
		return this.statistiquesJrByAgCur;
	}

	public List<StatistiqueByUser> statistiqueMoisAgCur(Users users) {
		List<FormVenteMois> venteMois = this.venteService.VentesParMoisAgentCourant(users);
		List<FormAchatByUser> achatMois = this.achatService.AchatsParMoisAgentCourant(users);
		List<StatistiqueByUser> liste = new ArrayList<>();

		for (FormVenteMois v : venteMois) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(v.getDate());
			statistique.setUsers(v.getUsers());
			statistique.setNombreVente(v.getNombreVente());
			statistique.setMontantVente(v.getMontant());
			statistique.setMontantAchat(0);
			statistique.setBilan(v.getMontant());
			for (FormAchatByUser a : achatMois) {
				if (v.getDate().getMonth() == a.getDate().getMonth() && v.getDate().getYear() == a.getDate().getYear()) {
					statistique.setMontantAchat(a.getMontantAchat());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}

			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().getMonth() == statistique.getDate().getMonth()
						&& s.getDate().getYear() == statistique.getDate().getYear()) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}

		for (FormAchatByUser a : achatMois) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(a.getDate());
			statistique.setUsers(a.getUsers());
			statistique.setNombreVente(0);
			statistique.setMontantVente(0);
			statistique.setMontantAchat(a.getMontantAchat());
			statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
			for (FormVenteMois v : venteMois) {
				if (a.getDate().equals(v.getDate()) && v.getUsers().equals(a.getUsers())) {
					statistique.setMontantVente(v.getMontant());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}
			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().getMonth() == statistique.getDate().getMonth()
						&& s.getDate().getYear() == statistique.getDate().getYear()) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}
		this.statistiquesMoisByAgCur= liste;
		return this.statistiquesMoisByAgCur;
	}

	public List<StatistiqueByUser> statistiqueAnneeAgCur(Users users) {
		List<FormVenteAnnee> venteAnnees = this.venteService.VentesParAnneesAgentCourant(users);
		List<FormAchatByUser> achatAnnees = this.achatService.AchatsParAnneesAgentCourant(users);
		List<StatistiqueByUser> liste = new ArrayList<>();

		for (FormVenteAnnee v : venteAnnees) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(v.getDate());
			statistique.setUsers(v.getUsers());
			statistique.setNombreVente(v.getNombreVente());
			statistique.setMontantVente(v.getMontant());
			statistique.setMontantAchat(0);
			statistique.setBilan(v.getMontant());
			for (FormAchatByUser a : achatAnnees) {
				if (v.getDate().getYear() == a.getDate().getYear() ) {
					statistique.setMontantAchat(a.getMontantAchat());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}

			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().getYear() == statistique.getDate().getYear()) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}

		for (FormAchatByUser a : achatAnnees) {
			StatistiqueByUser statistique = new StatistiqueByUser();
			statistique.setDate(a.getDate());
			statistique.setUsers(a.getUsers());
			statistique.setNombreVente(0);
			statistique.setMontantVente(0);
			statistique.setMontantAchat(a.getMontantAchat());
			statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
			for (FormVenteAnnee v : venteAnnees) {
				if (a.getDate().getYear() == v.getDate().getYear()) {
					statistique.setMontantVente(v.getMontant());
					statistique.setBilan(statistique.getMontantVente() - a.getMontantAchat());
				}
			}
			boolean b = false;
			for (StatistiqueByUser s : liste) {
				if ((s.getDate().getYear() == statistique.getDate().getYear()) || statistique.getBilan() == 0) {
					b = true;
					break;
				}
			}
			if (b == false)
				liste.add(statistique);
		}
		this.statistiquesAnneeByAgCur= liste;
		return this.statistiquesAnneeByAgCur;
	}

}
