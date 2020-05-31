package edu.egg.Sumame.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.egg.Sumame.entidades.Postulante;

@Repository
public interface PostulanteRepositorio extends JpaRepository<Postulante, String> {

}
