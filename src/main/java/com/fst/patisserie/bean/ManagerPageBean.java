package com.fst.patisserie.bean;


import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.text.DateFormatter;

import org.apache.poi.ss.formula.functions.Now;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Component
@ManagedBean
@SessionScoped
public class ManagerPageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String page="ventes.xhtml";
	String module;
	String fonctionnalite;
	

	public  ManagerPageBean()
	{
		System.out.println("******** ManagerPageBean init ***************");
		
		
		
	}
	public void changePage(String page)
	{
		//FacesContext facesContext=FacesContext.getCurrentInstance();
		//facesContext.getExternalContext().get
		this.page=page;
		
		System.out.println("okkk*************page :"+page);
		
	}
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getFonctionnalite() {
		return fonctionnalite;
	}
	public void setFonctionnalite(String fonctionnalite) {
		this.fonctionnalite = fonctionnalite;
	}
	
	
	

}
