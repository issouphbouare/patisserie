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

import com.fst.patisserie.model.Fournisseur;
import com.fst.patisserie.model.Produit;
import com.fst.patisserie.service.FournisseurService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class FournisseurBean {

	private List<Fournisseur> fournisseurs;

	private String urlList = "/fournisseurs.xhtml";

	private Fournisseur selectedFournisseur;

	private List<Fournisseur> selectedFournisseurs;
	
	// Pour les filtrages
	private List<Fournisseur> filtered;
	private boolean globalFilterOnly;
	private List<FilterMeta> filterBy;

	@Inject
	private FournisseurService fournisseurService;

	@PostConstruct
	public void init() {
		this.fournisseurs = this.fournisseurService.findAll();
	}

	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public void openNew() {
		this.selectedFournisseur = new Fournisseur();
	}

	public void saveFournisseur() {
		if (this.selectedFournisseur.getId() == null) {
			// this.selectedFournisseur.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			this.fournisseurs.add(this.selectedFournisseur);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fournisseur ajouté avec succes"));

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fournisseur modifié avec succes"));
		}

		fournisseurService.save(this.selectedFournisseur);
		PrimeFaces.current().executeScript("PF('manageFournisseurDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-fournisseurs");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public void deleteFournisseur() {
		String cod = this.selectedFournisseur.getLibelle();
		this.fournisseurs.remove(this.selectedFournisseur);
		fournisseurService.delete(selectedFournisseur);
		this.selectedFournisseur = null;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Fournisseur " + cod + " Supprimé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-fournisseurs");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedFournisseurs()) {
			int size = this.selectedFournisseurs.size();
			return size > 1 ? size + " Fournisseurs selectionnés" : "1 Fournisseur selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedFournisseurs() {
		return this.selectedFournisseurs != null && !this.selectedFournisseurs.isEmpty();
	}

	public void deleteSelectedFournisseurs() {
		this.fournisseurs.removeAll(this.selectedFournisseurs);
		this.fournisseurService.deleteAll(selectedFournisseurs);
		this.selectedFournisseurs = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Fournisseurs Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-fournisseurs");
		PrimeFaces.current().executeScript("PF('dtFournisseurs').clearFilters()");

	}

}
