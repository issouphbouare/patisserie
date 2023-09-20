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

import com.fst.patisserie.model.Client;
import com.fst.patisserie.model.Produit;
import com.fst.patisserie.service.ClientService;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class ClientBean {

	private List<Client> clients;

	private String urlList = "/clients.xhtml";

	private Client selectedClient;

	private List<Client> selectedClients;

	// Pour les filtrages
	private List<Client> filtered;
	private boolean globalFilterOnly;
	private List<FilterMeta> filterBy;

	@Inject
	private ClientService clientService;

	@PostConstruct
	public void init() {
		this.clients = this.clientService.findAll();
	}

	public List<Client> getClients() {
		return clients;
	}

	public void openNew() {
		this.selectedClient = new Client();
	}

	public void saveClient() {
		if (this.selectedClient.getId() == null) {
			// this.selectedClient.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			this.clients.add(this.selectedClient);

			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client ajouté avec succes"));

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client modifié avec succes"));
		}

		clientService.save(this.selectedClient);
		PrimeFaces.current().executeScript("PF('manageClientDialog').hide()");
		PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public void deleteClient() {
		String cod = this.selectedClient.getNomClient();
		this.clients.remove(this.selectedClient);
		clientService.delete(selectedClient);
		this.selectedClient = null;

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Client " + cod + " Supprimé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedClients()) {
			int size = this.selectedClients.size();
			return size > 1 ? size + " Clients selectionnés" : "1 Client selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedClients() {
		return this.selectedClients != null && !this.selectedClients.isEmpty();
	}

	public void deleteSelectedClients() {
		this.clients.removeAll(this.selectedClients);
		this.clientService.deleteAll(selectedClients);
		this.selectedClients = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Clients Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-clients");
		PrimeFaces.current().executeScript("PF('dtClients').clearFilters()");

	}

}
