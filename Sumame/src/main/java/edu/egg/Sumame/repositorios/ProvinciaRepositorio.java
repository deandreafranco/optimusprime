package edu.egg.Sumame.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.egg.Sumame.entidades.Provincia;

@Repository
public interface ProvinciaRepositorio extends JpaRepository<Provincia, String> {

}
