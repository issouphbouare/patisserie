package com.fst.patisserie.bean;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.FilterMeta;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fst.patisserie.model.FormVenteJour;
import com.fst.patisserie.model.Users;
import com.fst.patisserie.model.Vente;
import com.fst.patisserie.service.UsersService;

import lombok.Data;
import net.bytebuddy.asm.Advice.This;

@Named
@Component
@ManagedBean
@SessionScoped
@Data
public class UsersBean {

	private String urlList = "/users.xhtml";
	private String urlList1 = "/droitUsers.xhtml";
	private String urlList2 = "/profil.xhtml";
	private List<Users> users;
	private boolean btn1 = true;
	private String lastPass;
	private String newPass;
	private String confirmPass;

	// Pour les filtrages
	private List<Users> filtered;
	private boolean globalFilterOnly;
	private List<FilterMeta> filterBy;

	private Users selectedUser;

	private List<Users> selectedUsers;

	@Inject
	private UsersService usersService;
	@Inject
	private AuthBean authBean;

	@PostConstruct
	public void init() {
		this.users = this.usersService.findAll();
	}

	public Users getUser(Users users) {
		return users;
	}

	public List<Users> getUsers() {
		return users;
	}

	public Users profilCourant(Users users) {
		Users profil = this.usersService.findById(users.getId()).get();
		return profil;
	}

	public Users getProfil(Users users) {
		return this.usersService.findById(users.getId()).get();
	}

	public void openNew() {
		this.selectedUser = new Users();
	}

	public void saveUser() {
		if (this.selectedUser.getId() == null) {
			// this.selectedDomaine.setCode(UUID.randomUUID().toString().replaceAll("-",
			// "").substring(0, 9));
			if (usersService.existsByUsername(selectedUser.getUsername())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Cet Compte d'Identifiant: " + this.selectedUser.getUsername() + " existe deja"));
			} else {
				this.selectedUser.setPassword("0000");
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				selectedUser.setPassword(encoder.encode(selectedUser.getPassword()));
				this.selectedUser.setEnabled(false);
				this.selectedUser.setRole("agent");
				this.users.add(this.selectedUser);
				usersService.save(selectedUser);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Compte d'Identifiant: "
						+ this.selectedUser.getUsername() + " et de mot de passe: 000 crée  avec succes"));
				PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
				PrimeFaces.current().ajax().update("form:messages", "form:dt-Users");
				PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");

			}
		} else {
			Users usr;

			usr = usersService.findByUsername(selectedUser.getUsername());
			if (usr != null && !usr.getId().equals(selectedUser.getId())) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Cet Compte d'Identifiant:" + this.selectedUser.getUsername() + " existe deja"));
				// users = usersService.findAll();
				// PrimeFaces.current().ajax().update("form:messages", "form:dt-users");

			} else if (usr != null && usr.getId().equals(selectedUser.getId())) {
				// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				// selectedUser.setPassword(encoder.encode(selectedUser.getPassword()));
				usersService.save(selectedUser);
				PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");
				PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");

				PrimeFaces.current().executeScript("PF('editProfilDialog').show()");

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Compte d'Identifiant:" + this.selectedUser.getUsername() + "  modifié avec succes"));
			} else {

				// BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				// selectedUser.setPassword(encoder.encode(selectedUser.getPassword()));
				usersService.save(selectedUser);
				PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");
				PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
				if (this.authBean.currentUser != null
						&& this.authBean.currentUser.getId() == this.selectedUser.getId()) {
					PrimeFaces.current().executeScript("PF('editLoginDialog').show()");
				}
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
						"Compte d'Identifiant:" + this.selectedUser.getUsername() + "  modifié avec succes"));

			}

		}

	}

	public void saveUserPassword() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		if (encoder.matches(this.lastPass, this.selectedUser.getPassword()) == true
				&& this.newPass.equals(this.confirmPass)) {
			this.selectedUser.setPassword(this.newPass);
			this.selectedUser.setPassword(encoder.encode(this.selectedUser.getPassword()));
			usersService.save(this.selectedUser);

			PrimeFaces.current().executeScript("PF('editPasswordDialog').show()");
			PrimeFaces.current().ajax().update("form:messages", "form:dt-Users");
			PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");

			this.newPass = "";
			this.confirmPass = "";
			this.lastPass = "";

		} else {
			if (!this.newPass.equals(this.confirmPass)
					&& encoder.matches(this.lastPass, this.selectedUser.getPassword()) == true) {
				System.out.println("Password et confiration incorrect");
				PrimeFaces.current().executeScript("PF('errorNewConfirmDialog').show()");
			} else {
				if (encoder.matches(this.lastPass, this.selectedUser.getPassword()) == false
						&& this.newPass.equals(this.confirmPass)) {
					System.out.println("ancien password incoret incorrect");
					PrimeFaces.current().executeScript("PF('errorLastPasswordDialog').show()");
				} else {
					System.out.println("ancien password incoret incorrect et nouveau pass et confirmation differentes");
					PrimeFaces.current().executeScript("PF('errorPasswordDialog').show()");
				}
			}

		}

	}

	public void deleteUser() {
		String cod = this.selectedUser.getUsername();
		this.users.remove(this.selectedUser);
		usersService.delete(selectedUser);
		this.selectedUser = null;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Domaine " + cod + " Supprimé avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
		// PrimeFaces.current().executeScript("PF('dtDomaines').clearFilters()");

	}

	public String getDeleteButtonMessage() {
		if (hasSelectedUsers()) {
			int size = this.selectedUsers.size();
			return size > 1 ? size + " Users selectionnés" : "1 User selectionné";
		}

		return "Suppresion";
	}

	public boolean hasSelectedUsers() {
		return this.selectedUsers != null && !this.selectedUsers.isEmpty();
	}

	public void deleteSelectedUsers() {
		this.users.removeAll(this.selectedUsers);
		this.usersService.deleteAll(selectedUsers);
		this.selectedUsers = null;
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Comptes Supprimés avec succes"));
		PrimeFaces.current().ajax().update("form:messages", "form:dt-users");
		PrimeFaces.current().executeScript("PF('dtUsers').clearFilters()");

	}

}
