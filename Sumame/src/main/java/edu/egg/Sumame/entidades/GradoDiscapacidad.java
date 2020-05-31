package edu.egg.Sumame.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class GradoDiscapacidad {
	
@Id
@GeneratedValue(generator = "uuid")
@GenericGenerator(name = "uuid", strategy = "uuid2")
private String id;

private float porcentaje;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public float getporcentaje() {
	return porcentaje;
}

public void setporcentaje(float porcentaje) {
	this.porcentaje = porcentaje;
}



}
