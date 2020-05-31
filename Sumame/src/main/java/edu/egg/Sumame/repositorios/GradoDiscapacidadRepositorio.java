package edu.egg.Sumame.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.egg.Sumame.entidades.GradoDiscapacidad;

@Repository
public interface GradoDiscapacidadRepositorio extends JpaRepository<GradoDiscapacidad, String>{

}
