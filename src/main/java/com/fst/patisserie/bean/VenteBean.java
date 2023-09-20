package com.fst.patisserie.bean;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.springframework.stereotype.Component;

import com.fst.patisserie.model.FormVenteAnnee;
import com.fst.patisserie.model.FormVenteJour;
import com.fst.patisserie.model.FormVenteMois;
import com.fst.patisserie.model.LigneVente;
import com.fst.patisserie.model.Users;
import com.fst.patisserie.model.Vente;
import com.fst.patisserie.service.LigneVenteService;
import com.fst.patisserie.service.VenteService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class VenteBean {

	// Declarations pour vente
	private String urlList = "/ventes.xhtml";
	private String urlList3 = "/toutesLesVentes.xhtml";
	private Vente selectedVente, venteCourant;
	private List<Vente> selectedVentes;
	private List<Vente> ventes, venteByUsers;
	private String urlList2 = "/factureVentes.xhtml";
	private double espece;

	@Inject
	private VenteService venteService;

	// Declarations pour ligneVente
	private String urlList1 = "/ligneVentes.xhtml";
	private LigneVente selectedLigneVente;
	private List<LigneVente> selectedLigneVentes;
	private List<LigneVente> ligneVentes, listeVide;



	// les ventes de l'Agent courant aux date ,mois et annee courant
	public FormVenteJour getVenteJourCourantAgent(Users users) {
		return this.venteService.VenteJourCourantAgent(users);
	}

	public FormVenteMois getVenteMoisCourantAgent(Users users) {
		return this.venteService.VenteMoisCourantAgent(users);
	}

	public FormVenteAnnee getVenteAnneeCourantAgent(Users users) {
		return this.venteService.VenteAnneeCourantAgent(users);
	}

	@Inject
	private LigneVenteService ligneVenteService;

	@Inject
	private AuthBean authBean;

	// Les methodes pour la vente

	@PostConstruct
	public void init() {
		this.ventes = this.venteService.findAll();
	}

	

	public List<Vente> getVentes() {
		return ventes;
	}

	public List<Vente> getVentesByUsers(Users users) {
		return this.venteService.findByUsers(users);
	}

	public void openNew() {
		this.selectedVente = new Vente();
	}

	public void saveVente() {
		this.venteCourant = this.selectedVente;
		if (this.selectedVente.getId() == null) {
			this.ligneVentes = this.listeVide;
			// this.selectedVente.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			this.selectedVente.setUsers(this.authBean.currentUser);
			// this.ventes.add(selectedVente);
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vente
			// ajouté avec succes"));
		} else {
			this.ligneVentes = ligneVenteService
					.getLigneVenteByVente(this.venteService.getOne(this.venteCourant.getId()));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vente modifié avec succes"));
		}

		venteService.save(this.selectedVente);
		this.ventes = venteService.findAll();
		PrimeFaces.current().executeScript("PF('manageVenteDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-ventes");

	}

	public void deleteVente() {
		// String cod = this.selectedVente.getFournisseur().getLibelle();
		this.ventes.remove(this.selectedVente);
		venteService.delete(selectedVente);
		this.selectedVente = null;

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vente  Supprimé ou annulé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-ventes");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedVentes()) {
			int size = this.selectedVentes.size();
			return size > 1 ? size + " Ventes selectionnés" : "1 Vente selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedVentes() {
		return this.selectedVentes != null && !this.selectedVentes.isEmpty();
	}

	public void deleteSelectedVentes() {
		this.ventes.removeAll(this.selectedVentes);
		this.venteService.deleteAll(selectedVentes);
		this.selectedVentes = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ventes Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-ventes");
		PrimeFaces.current().executeScript("PF('dtVentes').clearFilters()");

	}

	// Les methodes pour LigneVente

	public List<LigneVente> getLigneVentes() {
		return ligneVentes;
	}

	public void openNew1() {
		this.selectedLigneVente = new LigneVente();

	}

	public void saveLigneVente() {
		// this.venteCourant=this.venteService.getOne(this.venteCourant.getId());
		if (this.selectedLigneVente.getId() == null) {
			// this.selectedLigneVente.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));

			// this.ligneVentes.add(this.selectedLigneVente);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("LigneVente ajouté avec succes"));

		} else {
			this.venteCourant.setMontant(this.venteCourant.getMontant() - this.selectedLigneVente.getMontant());
			this.venteService.save(this.venteCourant);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("LigneVente modifié avec succes"));
		}
		this.selectedLigneVente.setMontant(
				this.selectedLigneVente.getPrixUnitaire().getPrix() * this.selectedLigneVente.getQuantite());
		this.selectedLigneVente.setVente(this.venteCourant);
		ligneVenteService.save(this.selectedLigneVente);
		this.venteCourant.setMontant(this.venteCourant.getMontant() + this.selectedLigneVente.getMontant());
		venteService.save(this.venteCourant);
		this.ventes = venteService.findAll();
		
		this.espece = 0;

		this.ligneVentes = ligneVenteService.getLigneVenteByVente(this.venteService.getOne(this.venteCourant.getId()));
		PrimeFaces.current().executeScript("PF('manageLigneVenteDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-ligneVentes");

	}

	public void deleteLigneVente() {
		this.venteCourant.setMontant(this.venteCourant.getMontant() - this.selectedLigneVente.getMontant());
		this.venteService.save(this.venteCourant);
		this.ligneVentes.remove(this.selectedLigneVente);
		ligneVenteService.delete(selectedLigneVente);

		this.ventes = venteService.findAll();
		
		this.ligneVentes = ligneVenteService.getLigneVenteByVente(this.venteService.getOne(this.venteCourant.getId()));

		this.selectedLigneVente = null;
		this.espece = 0;

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ligne de Commande  Supprimé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-ligneVentes");
		PrimeFaces.current().executeScript("PF('dtLigneVentes').clearFilters()");

	}

	public String getDeleteButtonMessage1() {
		if (hasSelectedLigneVentes()) {
			int size = this.selectedLigneVentes.size();
			return size > 1 ? size + " Lignes selectionnés" : "1 Ligne selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedLigneVentes() {
		return this.selectedLigneVentes != null && !this.selectedLigneVentes.isEmpty();
	}

	public void deleteSelectedLigneVentes() {
		for (LigneVente ligne : this.selectedLigneVentes) {
			this.venteCourant.setMontant(this.venteCourant.getMontant() - ligne.getMontant());
			this.venteService.save(this.venteCourant);
		}
		this.ligneVentes.removeAll(this.selectedLigneVentes);
		this.ligneVenteService.deleteAll(selectedLigneVentes);

		this.ventes = venteService.findAll();

		this.ligneVentes = ligneVenteService.getLigneVenteByVente(this.venteService.getOne(this.venteCourant.getId()));

		this.selectedLigneVentes = null;
		this.espece = 0;

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("LigneVentes Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-ligneVentes");
		PrimeFaces.current().executeScript("PF('dtLigneVentes').clearFilters()");

	}

	public void confirmVente() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vente effectué avec succes"));
	}

	public void details(Vente vente) {
		this.venteCourant = vente;
		this.ligneVentes = ligneVenteService.getLigneVenteByVente(vente);
		this.espece = 0;
	}

	public List<Vente> getVenteByUser(Users users) {
		if (users.getRole().equals("admin"))
			this.ventes = venteService.findAllByOrderByIdDesc();
		else
		    this.ventes = this.venteService.findByUsersOrderByIdDesc(users);
		
		return this.ventes;
	}

	// Pour les filtrages
	private List<FormVenteJour> filteredCustomers1;
	private List<Vente> filtered;
	private boolean globalFilterOnly;
	private List<FilterMeta> filterBy;
	

	
}
