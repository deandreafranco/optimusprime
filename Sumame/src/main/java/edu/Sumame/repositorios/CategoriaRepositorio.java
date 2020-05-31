package edu.egg.Sumame.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.egg.Sumame.entidades.Categoria;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, String>{
	
	

}
