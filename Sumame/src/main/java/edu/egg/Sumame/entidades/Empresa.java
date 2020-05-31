package edu.egg.Sumame.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Empresa extends Usuario {
	
	private String sectorEmpresarial;
	
	private String valoracion;
	
	private boolean rampas;
	
	private boolean circulacionAmplia;
	
	private boolean estacionamiento;
	
	private boolean ingresoSenalizado;
	
	private boolean banoAdaptado;
	
	private boolean senaleticaSonora;
	
	private boolean escaleraLey;
	
	private boolean mobiliarioAdaptado;
	
	private boolean senaleticaBraille;
	
	private boolean ascensorLey;
	
	@ManyToOne
	private Categoria categoria;
	
	@OneToMany
	private List<OfertaLaboral> ofertaLaboral;
	
	public String getSectorEmpresarial() {
		return sectorEmpresarial;
	}
	public void setSectorEmpresarial(String sectorEmpresarial) {
		this.sectorEmpresarial = sectorEmpresarial;
	}
	public String getValoracion() {
		return valoracion;
	}
	public void setValoracion(String valoracion) {
		this.valoracion = valoracion;
	}

	public boolean isRampas() {
		return rampas;
	}
	public void setRampas(boolean rampas) {
		this.rampas = rampas;
	}
	public boolean isCirculacionAmplia() {
		return circulacionAmplia;
	}
	public void setCirculacionAmplia(boolean circulacionAmplia) {
		this.circulacionAmplia = circulacionAmplia;
	}
	public boolean isEstacionamiento() {
		return estacionamiento;
	}
	public void setEstacionamiento(boolean estacionamiento) {
		this.estacionamiento = estacionamiento;
	}
	public boolean isIngresoSenalizado() {
		return ingresoSenalizado;
	}
	public void setIngresoSenalizado(boolean ingresoSenalizado) {
		this.ingresoSenalizado = ingresoSenalizado;
	}
	public boolean isBanoAdaptado() {
		return banoAdaptado;
	}
	public void setBanoAdaptado(boolean banoAdaptado) {
		this.banoAdaptado = banoAdaptado;
	}
	public boolean isSenaleticaSonora() {
		return senaleticaSonora;
	}
	public void setsenaleticaSonora(boolean senaleticaSonora) {
		this.senaleticaSonora = senaleticaSonora;
	}
	public boolean isEscaleraLey() {
		return escaleraLey;
	}
	public void setEscaleraLey(boolean escaleraLey) {
		this.escaleraLey = escaleraLey;
	}
	public boolean isMobiliarioAdaptado() {
		return mobiliarioAdaptado;
	}
	public void setMobiliarioAdaptado(boolean mobiliarioAdaptado) {
		this.mobiliarioAdaptado = mobiliarioAdaptado;
	}
	public boolean issenaleticabraille() {
		return senaleticaBraille;
	}
	public void setSenaleticaBraille(boolean senaleticabraille) {
		this.senaleticaBraille = senaleticabraille;
	}
	public boolean isAscensorLey() {
		return ascensorLey;
	}
	public void setAscensorLey(boolean ascensorLey) {
		this.ascensorLey = ascensorLey;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<OfertaLaboral> getOfertaLaboral() {
		return ofertaLaboral;
	}
	public void setOfertaLaboral(List<OfertaLaboral> ofertaLaboral) {
		this.ofertaLaboral = ofertaLaboral;
	}	

}
