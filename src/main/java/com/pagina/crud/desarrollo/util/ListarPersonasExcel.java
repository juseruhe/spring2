package com.pagina.crud.desarrollo.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.pagina.crud.desarrollo.models.entity.Persona;

@Component("/views/personas/index.xlsx")
public class ListarPersonasExcel extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"listado-personas.xlsx\"");
		
		Sheet hoja = workbook.createSheet("Personas");
		
		Row filaTitulo = hoja.createRow(0);
		
		Cell celda = filaTitulo.createCell(0);
		
		celda.setCellValue("Listado General de Personas");
		
		Row filaData = hoja.createRow(2);
		
		String[] columnas = {"#","DNI","Nombres"};
		
		for(int i=0;i<columnas.length;i++) {
			celda = filaData.createCell(i);
			celda.setCellValue(columnas[i]);
		}
		
		List<Persona> listaP = (List<Persona>) model.get("personas");
		
		int numFila = 3;
		for(Persona persona: listaP) {
			filaData = hoja.createRow(numFila);
			filaData.createCell(0).setCellValue(persona.getId());
			filaData.createCell(1).setCellValue(persona.getDni());
			filaData.createCell(2).setCellValue(persona.getNombre());
			
			numFila++;
		}
	}

}
