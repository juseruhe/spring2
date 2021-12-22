package com.pagina.crud.desarrollo.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pagina.crud.desarrollo.models.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer> {

}
