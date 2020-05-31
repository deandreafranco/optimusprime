package edu.egg.Sumame.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.egg.Sumame.entidades.Discapacidad;

@Repository
public interface DiscapacidadRepositorio extends JpaRepository<Discapacidad, String> {
	

}
