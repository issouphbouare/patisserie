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

import com.fst.patisserie.model.Produit;
import com.fst.patisserie.model.Users;
import com.fst.patisserie.service.ProduitService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class ProduitBean {

	private List<Produit> produits;

	private String urlList = "/produits.xhtml";

	private Produit selectedProduit;

	private List<Produit> selectedProduits;

	// Pour les filtrages
	private List<Produit> filtered;
	private boolean globalFilterOnly;
	private List<FilterMeta> filterBy;

	@Inject
	private ProduitService produitService;

	@PostConstruct
	public void init() {
		this.produits = this.produitService.findAll();
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void openNew() {
		this.selectedProduit = new Produit();
	}

	public void saveProduit() {
		if (this.selectedProduit.getId() == null) {
			// this.selectedProduit.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			this.produits.add(this.selectedProduit);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit ajouté avec succes"));

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit modifié avec succes"));
		}

		produitService.save(this.selectedProduit);
		PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-produits");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public void deleteProduit() {
		String cod = this.selectedProduit.getCode();
		this.produits.remove(this.selectedProduit);
		produitService.delete(selectedProduit);
		this.selectedProduit = null;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Produit " + cod + " Supprimé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-produits");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedProduits()) {
			int size = this.selectedProduits.size();
			return size > 1 ? size + " Produits selectionnés" : "1 Produit selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedProduits() {
		return this.selectedProduits != null && !this.selectedProduits.isEmpty();
	}

	public void deleteSelectedProduits() {
		this.produits.removeAll(this.selectedProduits);
		this.produitService.deleteAll(selectedProduits);
		this.selectedProduits = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produits Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-produits");
		PrimeFaces.current().executeScript("PF('dtProduits').clearFilters()");

	}

}
