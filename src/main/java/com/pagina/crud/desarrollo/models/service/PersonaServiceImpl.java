package com.pagina.crud.desarrollo.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pagina.crud.desarrollo.models.entity.Persona;
import com.pagina.crud.desarrollo.models.repository.PersonaRepository;


@Service
public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public List<Persona> listarTodos() {
	   return (List<Persona>) personaRepository.findAll();
	}

	@Override
	public void guardar(Persona persona) {
		personaRepository.save(persona);

	}

	@Override
	public Persona buscarPorId(int id) {
		
		return personaRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id) {
		personaRepository.deleteById(id);
	}

}
