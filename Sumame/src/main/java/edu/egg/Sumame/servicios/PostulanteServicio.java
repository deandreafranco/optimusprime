package edu.egg.Sumame.servicios;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.egg.Sumame.entidades.Discapacidad;
import edu.egg.Sumame.entidades.GradoDiscapacidad;
import edu.egg.Sumame.entidades.Postulante;
import edu.egg.Sumame.entidades.Usuario;
import edu.egg.Sumame.enumeraciones.Genero;
import edu.egg.Sumame.enumeraciones.TipoDiscapacidad;
import edu.egg.Sumame.errores.ErrorServicio;
import edu.egg.Sumame.repositorios.DiscapacidadRepositorio;
import edu.egg.Sumame.repositorios.PostulanteRepositorio;
import edu.egg.Sumame.repositorios.UsuarioRepositorio;

@Service
public class PostulanteServicio {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private PostulanteRepositorio postulanteRepositorio;
	
	@Autowired
	private DiscapacidadRepositorio discapacidadRepositorio;
	
	@Transactional
	public void formularioPostulante (String nombre, String apellido, Date fechaNacimiento, String telefono, String mail, String nombreDisc, Genero genero, TipoDiscapacidad tipoDisc, float porcentaje) throws ErrorServicio{
			
		validar(nombre,apellido, mail, fechaNacimiento, telefono, nombreDisc, genero, tipoDisc);
		
		Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
		
		GradoDiscapacidad grado = new GradoDiscapacidad();
		grado.setporcentaje(porcentaje);
		Discapacidad discapacidad = new Discapacidad();
		discapacidad.setNombreDiscapacidad(nombreDisc);
		discapacidad.setTipoDiscapacidad(tipoDisc);
		discapacidad.setGradoDiscapacidad(grado);
		
		discapacidadRepositorio.save(discapacidad);
		
		Postulante postulante = new Postulante();
		postulante.setNombre(nombre);
		postulante.setApellido(apellido);
		postulante.setFechaNacimiento(fechaNacimiento);

		postulanteRepositorio.save(postulante);
		
		}
	
	public void modificarFormulario (String idPostulante,String idDisc, Genero genero, String telefono, TipoDiscapacidad tipoDisc) throws ErrorServicio{
		
		validar(genero,telefono, tipoDisc);
		
		Optional<Postulante> respuesta = postulanteRepositorio.findById(idPostulante);
		if(respuesta.isPresent()) {
			Postulante postulante = respuesta.get();
			
			postulante.setGenero(genero);
			postulante.setTelefono(telefono);
			
			Discapacidad discapacidad = discapacidadRepositorio.findById(idDisc).get();
			discapacidad.setTipoDiscapacidad(tipoDisc);
			
			postulanteRepositorio.save(postulante);
			
		} else {
			throw new ErrorServicio("No se encuentra el postulante solicitado");
			} 
			
		}
	
	private void validar (String nombre, String apellido, String mail, Date fechaNacimiento, String telefono, String nombreDisc, Genero genero, TipoDiscapacidad tipoDisc) throws ErrorServicio {
		
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorServicio("El nombre del postulante no puede ser nulo");
		}
		
		if (apellido ==  null || apellido.isEmpty()) {
			throw new ErrorServicio("El apellido del postulante no puede ser nulo");			
		}
		
		if (telefono == null) {
			throw new ErrorServicio("El telefono no puede ser nulo");			
		}
		
		if (mail == null || mail.isEmpty()) {
			throw new ErrorServicio("El mail del usuario no puede ser nulo");
		}
		
		if (fechaNacimiento == null) {
			throw new ErrorServicio("La fecha de naciomiento no puede estar vacia");
		}
		
		if (nombreDisc == null) {
			throw new ErrorServicio("El nombre de la discapacidad puede ser nulo");			
		}
		
		if (genero == null) {
			throw new ErrorServicio("El genero no puede ser nulo");
		}
		
		if (tipoDisc == null) {
			throw new ErrorServicio("El tipo de discapacidad no es nulo");
		}
	}
	
	private void validar(Genero genero,String telefono, TipoDiscapacidad tipoDisc) throws ErrorServicio{
		
		if (tipoDisc == null) {
			throw new ErrorServicio("El tipo de discapacidad no es nulo");
		}
		
		if (telefono == null) {
			throw new ErrorServicio("El telefono no puede ser nulo");			
		}
		
		if (genero == null) {
			throw new ErrorServicio("El genero no puede ser nulo");
		}
	}
}
