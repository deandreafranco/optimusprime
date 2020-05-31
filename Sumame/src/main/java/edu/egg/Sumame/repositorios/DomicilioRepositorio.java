package edu.egg.Sumame.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.egg.Sumame.entidades.Domicilio;

@Repository
public interface DomicilioRepositorio extends JpaRepository<Domicilio, String>{
	
}
