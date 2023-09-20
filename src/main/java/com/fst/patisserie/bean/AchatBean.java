package com.fst.patisserie.bean;

import java.io.IOException;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;
import org.primefaces.model.FilterMeta;
import org.springframework.stereotype.Component;

import com.fst.patisserie.model.Achat;
import com.fst.patisserie.model.LigneAchat;
import com.fst.patisserie.model.Users;
import com.fst.patisserie.service.AchatService;
import com.fst.patisserie.service.LigneAchatService;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class AchatBean {

	// Declarations pour achat
	private String urlList = "/achats.xhtml";
	private String urlList3 = "/toutesLesAchats.xhtml";
	private Achat selectedAchat, achatCourant;
	private List<Achat> selectedAchats;
	private List<Achat> achats, achatByUsers;
	private String urlList2 = "/factureAchats.xhtml";
	private double espece;

	@Inject
	private AchatService achatService;

	// Declarations pour ligneAchat
	private String urlList1 = "/ligneAchats.xhtml";
	private LigneAchat selectedLigneAchat;
	private List<LigneAchat> selectedLigneAchats;
	private List<LigneAchat> ligneAchats, listeVide;

	
	@Inject
	private LigneAchatService ligneAchatService;

	@Inject
	private AuthBean authBean;

	// Les methodes pour la achat

	@PostConstruct
	public void init() {
		this.achats = this.achatService.findAll();
		customizationOptions();
	}

	

	public List<Achat> getAchats() {
		return achats;
	}

	public List<Achat> getAchatsByUsers(Users users) {
		return this.achatService.findByUsers(users);
	}

	public void openNew() {
		this.selectedAchat = new Achat();
	}

	public void saveAchat() {
		this.achatCourant = this.selectedAchat;
		if (this.selectedAchat.getId() == null) {
			this.ligneAchats = this.listeVide;
			// this.selectedAchat.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			this.selectedAchat.setUsers(this.authBean.currentUser);
			// this.achats.add(selectedAchat);
			// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Achat
			// ajouté avec succes"));
		} else {
			this.ligneAchats = ligneAchatService
					.getLigneAchatByAchat(this.achatService.getOne(this.achatCourant.getId()));
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Achat modifié avec succes"));
		}

		achatService.save(this.selectedAchat);
		this.achats = achatService.findAll();
		
		PrimeFaces.current().executeScript("PF('manageAchatDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-achats");

	}

	public void deleteAchat() {
		// String cod = this.selectedAchat.getFournisseur().getLibelle();
		this.achats.remove(this.selectedAchat);
		achatService.delete(selectedAchat);
		this.selectedAchat = null;

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Achat  Supprimé ou annulé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-achats");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedAchats()) {
			int size = this.selectedAchats.size();
			return size > 1 ? size + " Achats selectionnés" : "1 Achat selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedAchats() {
		return this.selectedAchats != null && !this.selectedAchats.isEmpty();
	}

	public void deleteSelectedAchats() {
		this.achats.removeAll(this.selectedAchats);
		this.achatService.deleteAll(selectedAchats);
		this.selectedAchats = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Achats Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-achats");
		PrimeFaces.current().executeScript("PF('dtAchats').clearFilters()");

	}

	// Les methodes pour LigneAchat

	public List<LigneAchat> getLigneAchats() {
		return ligneAchats;
	}

	public void openNew1() {
		this.selectedLigneAchat = new LigneAchat();

	}

	public void saveLigneAchat() {
		// this.achatCourant=this.achatService.getOne(this.achatCourant.getId());
		if (this.selectedLigneAchat.getId() == null) {
			// this.selectedLigneAchat.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));

			// this.ligneAchats.add(this.selectedLigneAchat);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("LigneAchat ajouté avec succes"));

		} else {
			this.achatCourant.setMontant(this.achatCourant.getMontant() - this.selectedLigneAchat.getMontant());
			this.achatService.save(this.achatCourant);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("LigneAchat modifié avec succes"));
		}
		this.selectedLigneAchat.setMontant(
				this.selectedLigneAchat.getPrixUnitaire() * this.selectedLigneAchat.getQuantite());
		this.selectedLigneAchat.setAchat(this.achatCourant);
		ligneAchatService.save(this.selectedLigneAchat);
		this.achatCourant.setMontant(this.achatCourant.getMontant() + this.selectedLigneAchat.getMontant());
		achatService.save(this.achatCourant);
		this.achats = achatService.findAll();
		
		this.espece = 0;

		this.ligneAchats = ligneAchatService.getLigneAchatByAchat(this.achatService.getOne(this.achatCourant.getId()));
		PrimeFaces.current().executeScript("PF('manageLigneAchatDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-ligneAchats");

	}

	public void deleteLigneAchat() {
		this.achatCourant.setMontant(this.achatCourant.getMontant() - this.selectedLigneAchat.getMontant());
		this.achatService.save(this.achatCourant);
		this.ligneAchats.remove(this.selectedLigneAchat);
		ligneAchatService.delete(selectedLigneAchat);

		this.achats = achatService.findAll();
		
		this.ligneAchats = ligneAchatService.getLigneAchatByAchat(this.achatService.getOne(this.achatCourant.getId()));

		this.selectedLigneAchat = null;
		this.espece = 0;

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ligne de Commande  Supprimé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-ligneAchats");
		PrimeFaces.current().executeScript("PF('dtLigneAchats').clearFilters()");

	}

	public String getDeleteButtonMessage1() {
		if (hasSelectedLigneAchats()) {
			int size = this.selectedLigneAchats.size();
			return size > 1 ? size + " Lignes selectionnés" : "1 Ligne selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedLigneAchats() {
		return this.selectedLigneAchats != null && !this.selectedLigneAchats.isEmpty();
	}

	public void deleteSelectedLigneAchats() {
		for (LigneAchat ligne : this.selectedLigneAchats) {
			this.achatCourant.setMontant(this.achatCourant.getMontant() - ligne.getMontant());
			this.achatService.save(this.achatCourant);
		}
		this.ligneAchats.removeAll(this.selectedLigneAchats);
		this.ligneAchatService.deleteAll(selectedLigneAchats);

		this.achats = achatService.findAll();
		this.ligneAchats = ligneAchatService.getLigneAchatByAchat(this.achatService.getOne(this.achatCourant.getId()));

		this.selectedLigneAchats = null;
		this.espece = 0;

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("LigneAchats Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-ligneAchats");
		PrimeFaces.current().executeScript("PF('dtLigneAchats').clearFilters()");

	}

	public void confirmAchat() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Achat effectué avec succes"));
	}

	public void details(Achat achat) {
		this.achatCourant = achat;
		this.ligneAchats = ligneAchatService.getLigneAchatByAchat(achat);
		this.espece = 0;
	}

	public List<Achat> getAchatByUser(Users users) {
		if (users.getRole().equals("admin"))
			return this.achats = achatService.findAllByOrderByIdDesc();
		else
			return this.achats = this.achatService.findByUsersOrderByIdDesc(users);
	}

	// Pour les filtrages
	private List<Achat> filtered;
	private List<Achat> filteredAgCurJr;
	private List<Achat> filteredAgCurM;
	private List<Achat> filteredAgCurA;
	private boolean globalFilterOnly;
	private List<FilterMeta> filterBy;
	private String date;
	private String mois;
	private String annee;

	// Pour exportation
	private PDFOptions pdfOpt;

	public void customizationOptions() {
		pdfOpt = new PDFOptions();
		pdfOpt.setFacetBgColor("#F88017");
		pdfOpt.setFacetFontColor("#0000ff");
		pdfOpt.setFacetFontStyle("BOLD");
		pdfOpt.setCellFontSize("12");
		pdfOpt.setFontName("Courier");
		pdfOpt.setOrientation(PDFOrientationType.LANDSCAPE);

	}

	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		Document pdf = (Document) document;
		pdf.open();
		pdf.setPageSize(PageSize.A4);

	}

}
