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

import com.fst.patisserie.model.PrixUnitaire;
import com.fst.patisserie.model.Produit;
import com.fst.patisserie.service.PrixUnitaireService;
import com.fst.patisserie.service.ProduitService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class PrixUnitaireBean {

	private String urlList = "/prixUnitaires.xhtml";

	private List<PrixUnitaire> prixUnitaires;

	private PrixUnitaire selectedPrixUnitaire;

	private List<PrixUnitaire> selectedPrixUnitaires;

	// Pour les filtrages
	private List<PrixUnitaire> filtered;
	private boolean globalFilterOnly;
	private List<FilterMeta> filterBy;

	@Inject
	private PrixUnitaireService prixUnitaireService;
	@Inject
	private ProduitService produitService;

	@PostConstruct
	public void init() {
		this.prixUnitaires = this.prixUnitaireService.findAll();

	}

	public List<PrixUnitaire> getPrixUnitaires() {
		return prixUnitaires;
	}

	public List<PrixUnitaire> getPrixUnitaireByProduit(Produit produit) {
		if (produit != null) {
			return this.prixUnitaireService.getPrixUnitaireByProduit(produit);
		} else {
			return this.prixUnitaireService.getPrixUnitaireByProduit(this.produitService.findById(1).get());
		}

	}

	public void openNew() {
		this.selectedPrixUnitaire = new PrixUnitaire();
		// this.domaineCurr=this.selectedPrixUnitaire.getDomaine();
	}

	public void savePrixUnitaire() {
		if (this.selectedPrixUnitaire.getId() == null) {
			// this.selectedPrixUnitaire.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			this.prixUnitaires.add(this.selectedPrixUnitaire);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("PrixUnitaire ajouté avec succes"));

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("PrixUnitaire modifié avec succes"));
		}

		prixUnitaireService.save(this.selectedPrixUnitaire);
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-prixUnitaires");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public void deletePrixUnitaire() {
		String cod = this.selectedPrixUnitaire.getCode();
		this.prixUnitaires.remove(this.selectedPrixUnitaire);
		prixUnitaireService.delete(selectedPrixUnitaire);
		this.selectedPrixUnitaire = null;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("PrixUnitaire " + cod + " Supprimé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-prixUnitaires");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedPrixUnitaires()) {
			int size = this.selectedPrixUnitaires.size();
			return size > 1 ? size + " PrixUnitaires selectionnés" : "1 PrixUnitaire selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedPrixUnitaires() {
		return this.selectedPrixUnitaires != null && !this.selectedPrixUnitaires.isEmpty();
	}

	public void deleteSelectedPrixUnitaires() {
		this.prixUnitaires.removeAll(this.selectedPrixUnitaires);
		this.prixUnitaireService.deleteAll(selectedPrixUnitaires);
		this.selectedPrixUnitaires = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("PrixUnitaires Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-prixUnitaires");
		PrimeFaces.current().executeScript("PF('dtPrixUnitaires').clearFilters()");

	}

}
