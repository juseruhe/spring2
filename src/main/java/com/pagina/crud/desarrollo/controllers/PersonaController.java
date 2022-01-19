package com.pagina.crud.desarrollo.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.pagina.crud.desarrollo.models.entity.Persona;
import com.pagina.crud.desarrollo.models.service.IPersonaService;

@Controller
@RequestMapping("personas")
public class PersonaController {

	@Autowired
	private IPersonaService personaService;
	
	@GetMapping("/")
	public String index(Model model) {
		List<Persona> listadoPersonas = personaService.listarTodos();
		model.addAttribute("titulo", "Lista de Clientes");
		model.addAttribute("personas", listadoPersonas);
		return "/views/personas/index";
	}
	
	@GetMapping("/crear")
	public String create(Model model) {
		Persona persona = new Persona();
	     model.addAttribute("titulo", "Insertar Nueva Persona");
	     model.addAttribute("persona", persona);
		return "/views/personas/create";
	}
	
	@PostMapping("/")
	public String store(@ModelAttribute Persona persona) {
		personaService.guardar(persona);
		return "redirect:/personas/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		Persona persona = personaService.buscarPorId(id);
	     model.addAttribute("titulo", "Mostrar Datos de ");
	     model.addAttribute("persona", persona);
		return "/views/personas/show";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id,Model model) {
		Persona persona = personaService.buscarPorId(id);
	     model.addAttribute("titulo", "Actualizar Datos de ");
	     model.addAttribute("persona", persona);
		return "/views/personas/edit";
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute Persona persona) {
		personaService.guardar(persona);
		int id = persona.getId();
		return "redirect:/personas/"+id;
	}
	
	
	@GetMapping("/delete/{id}")
	public String destroy(@PathVariable("id") int id,Model model) {
		 personaService.eliminar(id);
	  
		return "redirect:/personas/";
	}
	
	@PostMapping("/importar")
	public String importar(@RequestParam("archivo") MultipartFile files,Model model) throws IOException {
		 List<Persona> personaLista = new ArrayList<>();
		
		XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
              
            	Persona persona = new Persona();

                XSSFRow row = worksheet.getRow(index);
             
                persona.setDni(String.valueOf(row.getCell(0).getNumericCellValue()));
                persona.setNombre(row.getCell(1).getStringCellValue());
                
                personaService.guardar(persona);
                personaLista.add(persona);
            }
        }

		
		model.addAttribute("mensaje","Datos de Importación de Personas Hechas "+personaLista);
		return "redirect:/personas/";
	}
	
	
	
}
