package edu.egg.Sumame.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.egg.Sumame.entidades.OfertaLaboral;

@Repository
public interface OfertaLaboralRepositorio extends JpaRepository<OfertaLaboral, String>{
	

}
