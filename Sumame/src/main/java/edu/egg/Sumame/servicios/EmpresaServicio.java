package edu.egg.Sumame.servicios;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.egg.Sumame.entidades.Departamento;
import edu.egg.Sumame.entidades.Domicilio;
import edu.egg.Sumame.entidades.Empresa;
import edu.egg.Sumame.entidades.Provincia;
import edu.egg.Sumame.entidades.Usuario;
import edu.egg.Sumame.errores.ErrorServicio;
import edu.egg.Sumame.repositorios.DepartamentoRepositorio;
import edu.egg.Sumame.repositorios.DomicilioRepositorio;
import edu.egg.Sumame.repositorios.EmpresaRepositorio;
import edu.egg.Sumame.repositorios.ProvinciaRepositorio;
import edu.egg.Sumame.repositorios.UsuarioRepositorio;

@Service
public class EmpresaServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private EmpresaRepositorio empresaRepositorio;
	
	@Autowired
	private DomicilioRepositorio domicilioRepositorio;
	
	@Autowired
	private ProvinciaRepositorio provinciaRepositorio;
	 
	@Autowired
	private DepartamentoRepositorio departamentoRepositorio;
	
	@Transactional
	public void formularioEmpresa (String nombre, String cuil, String telefono, String mail, String sector, String direccion, String idProvincia, String idDepartamento, boolean rampas, boolean circulacion, boolean estacionamiento, boolean ingresos, boolean bano, boolean senalSonido, boolean escalera, boolean mobiliario, boolean braile, boolean ascensor) throws ErrorServicio{
		
		Provincia provincia = provinciaRepositorio.getOne(idProvincia);
		Departamento departamento = departamentoRepositorio.getOne(idDepartamento);		
		
		validar(nombre,cuil, telefono, mail, sector, direccion, provincia, departamento);
		
		Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
		
		Domicilio domicilio= new Domicilio();
		domicilio.setDireccion(direccion);
		domicilio.setDepartamento(departamento);
		domicilio.setProvincia(provincia);
		
		domicilio= domicilioRepositorio.save(domicilio);
		
		Empresa empresa= new Empresa();
		empresa.setNombre(nombre);
		empresa.setCuil(cuil);
		empresa.setTelefono(telefono);
		empresa.setMail(usuario.getMail());
		empresa.setSectorEmpresarial(sector);
		empresa.setDomicilio(domicilio);
		empresa.setRampas(rampas);
		empresa.setCirculacionAmplia(circulacion);
		empresa.setEstacionamiento(estacionamiento);
		empresa.setIngresoSenalizado(ingresos);
		empresa.setBanoAdaptado(bano);
		empresa.setsenaleticaSonora(senalSonido);
		empresa.setEscaleraLey(escalera);
		empresa.setMobiliarioAdaptado(mobiliario);
		empresa.setSenaleticaBraille(braile);
		empresa.setAscensorLey(ascensor);
		
		empresaRepositorio.save(empresa);		
		
	}
	
	@Transactional
	public void modificarFormulario (String id, String nombre, String telefono, String mail, String sector, String direccion, String idProvincia, String idDepartamento, boolean rampas, boolean circulacion, boolean estacionamiento, boolean ingresos, boolean bano, boolean senalSonido, boolean escalera, boolean mobiliario, boolean brailLe, boolean ascensor) throws ErrorServicio {
		
		Provincia provincia = provinciaRepositorio.getOne(idProvincia);
		Departamento departamento = departamentoRepositorio.getOne(idDepartamento);
		
		validar(nombre, telefono, mail, sector, direccion, provincia, departamento);
		
		Optional<Empresa> respuesta = empresaRepositorio.findById(id);
		if(respuesta.isPresent()) {
			Empresa empresa = respuesta.get();
			
			Domicilio domicilio= new Domicilio();
			domicilio.setDireccion(direccion);
			domicilio.setDepartamento(departamento);
			domicilio.setProvincia(provincia);
			
			domicilio= domicilioRepositorio.save(domicilio);
			
			empresa.setNombre(nombre);
			empresa.setTelefono(telefono);
			empresa.setMail(mail);
			empresa.setSectorEmpresarial(sector);
			empresa.setDomicilio(domicilio);
			empresa.setRampas(rampas);
			empresa.setCirculacionAmplia(circulacion);
			empresa.setEstacionamiento(estacionamiento);
			empresa.setIngresoSenalizado(ingresos);
			empresa.setBanoAdaptado(bano);
			empresa.setsenaleticaSonora(senalSonido);
			empresa.setEscaleraLey(escalera);
			empresa.setMobiliarioAdaptado(mobiliario);
			empresa.setSenaleticaBraille(brailLe);
			empresa.setAscensorLey(ascensor);
			
			empresaRepositorio.save(empresa);
			
		} else { 
			throw new ErrorServicio("No se encuentra la empresa solicitada");
			}	
	}
	
	private void validar( String nombre, String cuil, String telefono, String mail, String sector, String direccion, Provincia provincia, Departamento departamento) throws ErrorServicio {
		
		
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre de la empresa no puede ser nula");
		}
		
		if (cuil == null || cuil.isEmpty()) {
			throw new ErrorServicio("El cuil de la empresa no puede ser nulo");			
		}
		
		if (telefono == null) {
			throw new ErrorServicio("El telefono no puede ser nulo");			
		}
		
		if (mail == null || mail.isEmpty()) {
			throw new ErrorServicio("El mail del usuario no puede ser nulo");
		}
		
		if (sector == null) {
			throw new ErrorServicio("El sector empresarial no puede ser nulo");
		}
		
		if (direccion == null) {
			throw new ErrorServicio("La direccion de la empresa no puede ser nula");			
		}
		
		if (provincia == null) {
			throw new ErrorServicio("No se encontro la provincia solicitada");
		}
		
		if (departamento == null) {
			throw new ErrorServicio("No se encontro el departamento solicitado");
		}
	}
	
	private void validar( String nombre, String telefono, String mail, String sector, String direccion, Provincia provincia, Departamento departamento) throws ErrorServicio {
		
				
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre de la empresa no puede ser nula");
		}
		
		if (telefono == null) {
			throw new ErrorServicio("El telefono no puede ser nulo");			
		}
		
		if (mail == null || mail.isEmpty()) {
			throw new ErrorServicio("El mail del usuario no puede ser nulo");
		}
		
		if (sector == null) {
			throw new ErrorServicio("El sector empresarial no puede ser nulo");
		}
		
		if (direccion == null) {
			throw new ErrorServicio("La direccion de la empresa no puede ser nula");			
		}
		
		if (provincia == null) {
			throw new ErrorServicio("No se encontro la provincia solicitada");
		}
		
		if (departamento == null) {
			throw new ErrorServicio("No se encontro el departamento solicitado");
		}
	}


}
