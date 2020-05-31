package edu.egg.Sumame.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import edu.egg.Sumame.enumeraciones.TipoDiscapacidad;

@Entity
public class Discapacidad {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String nombreDiscapacidad;

	@Enumerated(EnumType.STRING)
	private TipoDiscapacidad tipoDiscapacidad;

	@ManyToOne
	private GradoDiscapacidad gradoDiscapacidad;

	@ManyToOne
	private Postulante postulante;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreDiscapacidad() {
		return nombreDiscapacidad;
	}

	public void setNombreDiscapacidad(String nombreDiscapacidad) {
		this.nombreDiscapacidad = nombreDiscapacidad;
	}

	public TipoDiscapacidad getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}

	public void setTipoDiscapacidad(TipoDiscapacidad tipoDiscapacidad) {
		this.tipoDiscapacidad = tipoDiscapacidad;
	}

	public GradoDiscapacidad getGradoDiscapacidad() {
		return gradoDiscapacidad;
	}

	public void setGradoDiscapacidad(GradoDiscapacidad gradoDiscapacidad) {
		this.gradoDiscapacidad = gradoDiscapacidad;
	}

}
