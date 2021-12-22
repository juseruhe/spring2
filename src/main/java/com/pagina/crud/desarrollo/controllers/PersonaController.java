package com.pagina.crud.desarrollo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
}
