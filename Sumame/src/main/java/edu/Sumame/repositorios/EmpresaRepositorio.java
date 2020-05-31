package edu.egg.Sumame.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.egg.Sumame.entidades.Empresa;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, String> {


}
