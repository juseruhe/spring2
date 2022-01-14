package com.pagina.crud.desarrollo.util;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.pagina.crud.desarrollo.models.entity.Persona;

@Component("/views/personas/index")
public class ListarPersonasPDF extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Persona> listadoPersonas = (List<Persona>) model.get("personas");
	
		document.setPageSize(PageSize.LETTER);
		document.setMargins(-40,-40,40,40);
		
		document.open();
		PdfPTable tablaTitulo = new PdfPTable(1);
		PdfPCell celda = null;
		Font fuenteTitulo = FontFactory.getFont("Helvetica",16,Color.WHITE);
		
		celda = new PdfPCell(new Phrase("Listado General de Personas",fuenteTitulo));
		celda.setBorder(0);
		celda.setBackgroundColor(new Color(45,65,128));
		celda.setHorizontalAlignment(Element.ALIGN_CENTER);
		celda.setVerticalAlignment(Element.ALIGN_CENTER);
		celda.setPadding(30);
		
		tablaTitulo.addCell(celda);
		tablaTitulo.setSpacingAfter(15);
		
		// Encabezados de Tabla
		
		PdfPTable tablaTema = new PdfPTable(3);
		PdfPCell celdaTema = null;
		PdfPCell celdaTema2 = null;
		PdfPCell celdaTema3 = null;
		Font fuenteTema = FontFactory.getFont("Helvetica",12,Color.WHITE);
		
		celdaTema = new PdfPCell(new Phrase("#",fuenteTema));
		celdaTema2 = new PdfPCell(new Phrase("DNI",fuenteTema));
		celdaTema3 = new PdfPCell(new Phrase("Nombres",fuenteTema));
		
		celdaTema.setBorder(0);
		celdaTema.setBackgroundColor(new Color(45,65,128));
		celdaTema.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTema.setVerticalAlignment(Element.ALIGN_CENTER);
		celdaTema.setPadding(3);
		
		celdaTema2.setBorder(0);
		celdaTema2.setBackgroundColor(new Color(45,65,128));
		celdaTema2.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTema2.setVerticalAlignment(Element.ALIGN_CENTER);
		celdaTema2.setPadding(3);
		
		celdaTema3.setBorder(0);
		celdaTema3.setBackgroundColor(new Color(45,65,128));
		celdaTema3.setHorizontalAlignment(Element.ALIGN_CENTER);
		celdaTema3.setVerticalAlignment(Element.ALIGN_CENTER);
		celdaTema3.setPadding(3);
		
		tablaTema.addCell(celdaTema);
		tablaTema.addCell(celdaTema2);
		tablaTema.addCell(celdaTema3);
	
		PdfPTable tablaPersonas = new PdfPTable(3);
		
	
		listadoPersonas.forEach(persona->{
			tablaPersonas.addCell(String.valueOf(persona.getId()));
			tablaPersonas.addCell(persona.getDni());
			tablaPersonas.addCell(persona.getNombre());
		});
		
		document.add(tablaTitulo);
		document.add(tablaTema);
		document.add(tablaPersonas);
		
	}

}
