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

import com.fst.patisserie.model.MatierePremiere;
import com.fst.patisserie.model.Produit;
import com.fst.patisserie.service.MatierePremiereService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class MatierePremiereBean {

	private List<MatierePremiere> matierePremieres;

	private String urlList = "/matierePremieres.xhtml";

	private MatierePremiere selectedMatierePremiere;

	private List<MatierePremiere> selectedMatierePremieres;

	// Pour les filtrages
	private List<MatierePremiere> filtered;
	private boolean globalFilterOnly;
	private List<FilterMeta> filterBy;

	@Inject
	private MatierePremiereService matierePremiereService;

	@PostConstruct
	public void init() {
		this.matierePremieres = this.matierePremiereService.findAll();
	}

	public List<MatierePremiere> getMatierePremieres() {
		return matierePremieres;
	}

	public void openNew() {
		this.selectedMatierePremiere = new MatierePremiere();
	}

	public void saveMatierePremiere() {
		if (this.selectedMatierePremiere.getId() == null) {
			// this.selectedMatierePremiere.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			this.matierePremieres.add(this.selectedMatierePremiere);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("MatierePremiere ajouté avec succes"));

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("MatierePremiere modifié avec succes"));
		}

		matierePremiereService.save(this.selectedMatierePremiere);
		PrimeFaces.current().executeScript("PF('manageMatierePremiereDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-matierePremieres");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public void deleteMatierePremiere() {
		String cod = this.selectedMatierePremiere.getLibelle();
		this.matierePremieres.remove(this.selectedMatierePremiere);
		matierePremiereService.delete(selectedMatierePremiere);
		this.selectedMatierePremiere = null;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("MatierePremiere " + cod + " Supprimé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-matierePremieres");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedMatierePremieres()) {
			int size = this.selectedMatierePremieres.size();
			return size > 1 ? size + " MatierePremieres selectionnés" : "1 MatierePremiere selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedMatierePremieres() {
		return this.selectedMatierePremieres != null && !this.selectedMatierePremieres.isEmpty();
	}

	public void deleteSelectedMatierePremieres() {
		this.matierePremieres.removeAll(this.selectedMatierePremieres);
		this.matierePremiereService.deleteAll(selectedMatierePremieres);
		this.selectedMatierePremieres = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("MatierePremieres Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-matierePremieres");
		PrimeFaces.current().executeScript("PF('dtMatierePremieres').clearFilters()");

	}

}
