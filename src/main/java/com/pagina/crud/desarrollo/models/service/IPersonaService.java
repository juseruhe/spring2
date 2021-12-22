package com.pagina.crud.desarrollo.models.service;

import java.util.List;

import com.pagina.crud.desarrollo.models.entity.Persona;

public interface IPersonaService {

	public List<Persona> listarTodos();
	public void guardar(Persona persona);
	public Persona buscarPorId(int id);
	public void eliminar(int id);
}
