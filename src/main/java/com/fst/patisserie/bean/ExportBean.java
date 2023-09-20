package com.fst.patisserie.bean;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.poi.hssf.record.FooterRecord;
import org.apache.poi.ss.usermodel.Footer;
import org.primefaces.PrimeFaces;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.component.export.PDFOrientationType;
import org.springframework.stereotype.Component;

import com.fst.patisserie.model.FormVenteAnnee;
import com.fst.patisserie.model.FormVenteJour;
import com.fst.patisserie.model.FormVenteMois;
import com.fst.patisserie.model.LigneVente;
import com.fst.patisserie.model.Users;
import com.fst.patisserie.model.Vente;
import com.fst.patisserie.model.VenteAnnee;
import com.fst.patisserie.model.VenteJour;
import com.fst.patisserie.model.VenteMois;
import com.fst.patisserie.service.LigneVenteService;
import com.fst.patisserie.service.VenteService;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import lombok.Data;

@Component
@ManagedBean
@SessionScoped
@Data
public class ExportBean {

	
	@PostConstruct
	public void init() {
		customizationOptions();
	}

	
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
