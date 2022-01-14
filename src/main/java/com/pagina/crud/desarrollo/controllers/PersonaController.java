package com.pagina.crud.desarrollo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	
}
