
package edu.egg.Sumame.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.egg.Sumame.entidades.Usuario;
import edu.egg.Sumame.enumeraciones.TipoUsuario;
import edu.egg.Sumame.errores.ErrorServicio;
import edu.egg.Sumame.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServicio implements UserDetailsService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Transactional
	public void registrar(String mail, String contrasena, String contrasena2, TipoUsuario tipoUsuario) throws ErrorServicio {
		
		validar(mail, contrasena, contrasena2, tipoUsuario);
		
		Usuario usuario = new Usuario();
		usuario.setMail(mail);
		usuario.setTipoUsuario(tipoUsuario);
		
		String encriptada = new BCryptPasswordEncoder().encode(contrasena);
		usuario.setContrasena(encriptada);
		usuario.setAlta(new Date());
	
		usuarioRepositorio.save(usuario);
		
	}
	
	@Transactional
	public void modificar(String mail, String contrasena, String contrasena2, TipoUsuario tipoUsuario, String id) throws ErrorServicio {
		
		validar(mail, contrasena, contrasena2, tipoUsuario);
		
		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
		if(respuesta.isPresent()) {
			Usuario usuario = respuesta.get();
			usuario.setMail(mail);
			
			String encriptada = new BCryptPasswordEncoder().encode(contrasena);
			usuario.setContrasena(encriptada);
			
			usuarioRepositorio.save(usuario);
		} else { 
			throw new ErrorServicio("No se encuentra el usuario solicitado");
			}
		
	}
	
	@Transactional
	public void habilitar (String id) throws ErrorServicio {
		
		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
		if(respuesta.isPresent()) {
			Usuario usuario= respuesta.get();
			usuario.setBaja(null);
			usuarioRepositorio.save(usuario);
		} else {
			throw new ErrorServicio("No se encuentra el usuario solicitado");
		}
	}
	
	@Transactional
	public void deshabilitar (String id) throws ErrorServicio {
		
		Optional<Usuario> respuesta = usuarioRepositorio.findById(id);
		if(respuesta.isPresent()) {
			Usuario usuario= respuesta.get();
			usuario.setBaja(new Date());
			usuarioRepositorio.save(usuario);
		} else {
			throw new ErrorServicio("No se encuentra el usuario solicitado");
		}
	}
	
	private void validar( String mail, String contrasena, String contrasena2, TipoUsuario tipoUsuario) throws ErrorServicio {
		
		if (mail == null || mail.isEmpty()) {
			throw new ErrorServicio("El mail del usuario no puede ser nulo");
		}
		
		if (contrasena == null || contrasena.isEmpty() || contrasena.length() <= 6) {
			throw new ErrorServicio("La contrasena de usuario no puede ser nula y debe tener al menos seis digitos");
		}
		
		if (!contrasena.equals(contrasena2)) {
			throw new ErrorServicio("Las contrasenas deben ser guales.");			
		}
		
		if (tipoUsuario == null) {
			throw new ErrorServicio("El tipo de Usuario no puede ser nulo");
		}
		
	}

	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.buscarPorMail(mail);
		
		if(usuario != null) {
			List<GrantedAuthority> permisos = new ArrayList<>();
			GrantedAuthority p1 = new SimpleGrantedAuthority("TipoUsuario");
			permisos.add(p1);
			
			User user = new User(usuario.getMail(), usuario.getContrasena(), permisos);
			System.out.println(usuario.getContrasena()+""+ usuario.getNombre());
			return user;
		} else {
			return null;
		}
		
	}
}
