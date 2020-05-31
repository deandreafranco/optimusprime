package edu.egg.Sumame.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.egg.Sumame.entidades.Departamento;

@Repository
public interface DepartamentoRepositorio extends JpaRepository<Departamento, String>{
	


}
