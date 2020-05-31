package edu.egg.Sumame.entidades;

import java.util.Date;
//import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import edu.egg.Sumame.enumeraciones.Genero;

@Entity
public class Postulante extends Usuario {

	private String apellido;
	private Date fechaNacimiento;

	@Enumerated(EnumType.STRING)
	private Genero genero;

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Genero getSexo() {
		return genero;
	}

	public void setSexo(Genero genero) {
		this.genero = genero;
	}

}
