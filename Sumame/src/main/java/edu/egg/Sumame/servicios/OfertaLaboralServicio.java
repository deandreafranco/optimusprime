package edu.egg.Sumame.servicios;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.egg.Sumame.entidades.OfertaLaboral;
import edu.egg.Sumame.entidades.Usuario;
import edu.egg.Sumame.errores.ErrorServicio;
import edu.egg.Sumame.repositorios.OfertaLaboralRepositorio;
import edu.egg.Sumame.repositorios.UsuarioRepositorio;

@Service
public class OfertaLaboralServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private OfertaLaboralRepositorio ofertaLaboralRepositorio;
	
	@Transactional
	public void agregarOfertaLaboral(String nombreOferta, String descripcion, String id) throws ErrorServicio{
		
		Usuario usuario = usuarioRepositorio.findById(id).get();
		
		validar(nombreOferta, descripcion);
		
		OfertaLaboral ofertaLaboral = new OfertaLaboral();
		ofertaLaboral.setNombreOferta(nombreOferta);
		ofertaLaboral.setDescripcion(descripcion);
		ofertaLaboral.setAlta(new Date());
		
		ofertaLaboralRepositorio.save(ofertaLaboral);	
		
	}
	@Transactional
	public void modificarOferta(String idUsuario, String idOferta, String nombreOferta, String descripcion) throws ErrorServicio{
	
	validar(nombreOferta, descripcion);
	
	Usuario usuario= usuarioRepositorio.findById(idUsuario).get();	
		
	Optional<OfertaLaboral> respuesta= ofertaLaboralRepositorio.findById(idOferta);
	if(respuesta.isPresent()) {
		OfertaLaboral ofertaLaboral = respuesta.get();
		
		if(usuario.getId().equals(idUsuario)) {
			ofertaLaboral.setNombreOferta(nombreOferta);
			ofertaLaboral.setDescripcion(descripcion);
			
			ofertaLaboralRepositorio.save(ofertaLaboral);
			
		} else {
			throw new ErrorServicio("No tiene persmisos suficientes para realizar la operacion");
		}
	} else {
		throw new ErrorServicio("No existe una empresa con el identificador solicitado");
		}
	}
	
	@Transactional
	public void eliminarOferta(String idUsuario, String idOferta) throws ErrorServicio{
		
		Usuario usuario= usuarioRepositorio.findById(idUsuario).get();
		
		Optional<OfertaLaboral> respuesta= ofertaLaboralRepositorio.findById(idOferta);
		if(respuesta.isPresent()) {
			OfertaLaboral ofertaLaboral = respuesta.get();
			
			if(usuario.getId().equals(idUsuario)) {
				ofertaLaboral.setBaja(new Date());
				
				ofertaLaboralRepositorio.save(ofertaLaboral);
				
			} else {
				throw new ErrorServicio("No tiene persmisos suficientes para realizar la operacion");
			}
		} else {
			throw new ErrorServicio("No existe una empresa con el identificador solicitado");
			}
	}
	
	private void validar(String nombreOferta, String descripcion) throws ErrorServicio{
		
		if (nombreOferta == null || nombreOferta.isEmpty()) {
			throw new ErrorServicio("El titulo de la oferta laboral no puede ser nulo o vacío.");
		}
		
		if ( descripcion == null || descripcion.isEmpty()) {
			throw new ErrorServicio("La descripción de la oferta no puede estar vacía.");
		}
	}

}
