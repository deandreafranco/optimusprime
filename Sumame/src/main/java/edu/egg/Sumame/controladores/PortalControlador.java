package edu.egg.Sumame.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.egg.Sumame.entidades.Departamento;
import edu.egg.Sumame.entidades.Provincia;
import edu.egg.Sumame.enumeraciones.Genero;
import edu.egg.Sumame.enumeraciones.TipoDiscapacidad;
import edu.egg.Sumame.enumeraciones.TipoUsuario;
import edu.egg.Sumame.repositorios.DepartamentoRepositorio;
import edu.egg.Sumame.repositorios.EmpresaRepositorio;
import edu.egg.Sumame.repositorios.PostulanteRepositorio;
import edu.egg.Sumame.repositorios.ProvinciaRepositorio;
import edu.egg.Sumame.repositorios.UsuarioRepositorio;
import edu.egg.Sumame.servicios.EmpresaServicio;
import edu.egg.Sumame.servicios.PostulanteServicio;
import edu.egg.Sumame.servicios.UsuarioServicio;
import edu.egg.Sumame.errores.ErrorServicio;
import edu.egg.Sumame.servicios.OfertaLaboralServicio;
import java.util.Date;

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private EmpresaServicio empresaServicio;

    @Autowired
    private PostulanteServicio postulanteServicio;

    @Autowired
    private OfertaLaboralServicio ofertaLaboralServicio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PostulanteRepositorio postulanteRepositorio;

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

    @Autowired
    private ProvinciaRepositorio provinciaRepositorio;

    @Autowired
    private DepartamentoRepositorio departamentoRepositorio;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

   
    @GetMapping("/login")
    public String login(@RequestParam(required = false ) String logout, ModelMap model, String error) {
    	if (error != null) { 
    		model.put( "error", "Nombre de Usuario o clave incorrectos");
    	}
    	if (logout != null) {
    		model.put("logout", "Ha salido correctamente de la plataforma");
    	}
    	
    	return "login.html";

    }
    
    @PostMapping("/logged")
    public String login(@RequestParam String mail) {
    	    usuarioServicio.loadUserByUsername(mail);
    	    return "redirect:/perfil/postulante";
    	    }
  
    @GetMapping("/exito")
    public String exito(ModelMap modelo) {
        modelo.put("titulo", "Guau! ");
        modelo.put("descripcion", "Ya estás logueado/a!.");
        return "exito.html";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro.html";
    }
    
    @PostMapping("/registrar")
    public String registrar(ModelMap modelo, @RequestParam String mail, @RequestParam String contrasena, @RequestParam String contrasena2, @RequestParam TipoUsuario tipoUsuario) {
       try {
            usuarioServicio.registrar(mail, contrasena, contrasena2, tipoUsuario);
        } catch (ErrorServicio ex) {
        	modelo.put("error", ex.getMessage());
            return "redirect:/registro";
            }
       modelo.put("titulo", "Tus datos han sido procesados con éxito");
       modelo.put("descripcion", "Ya podés acceder!");
    	return "redirect:/exito";
    }
//    @PostMapping("/registrar2")
//    public String registrar(ModelMap modelo, 
//    		@RequestParam String mail, 
//    		@RequestParam String contrasena, 
//    		@RequestParam String contrasena2
//    		) {
//        try {
//        	
//        	System.out.println("---------------------------------Entro-----------------------");
//        	
//          List<Provincia> provincia = provinciaRepositorio.findAll();
//          List<Departamento> departamento = departamentoRepositorio.findAll();
//            usuarioServicio.registrar(mail, contrasena, contrasena2, tipoUsuario);
//        } catch (ErrorServicio ex) {
//            modelo.put("error", ex.getMessage());
//            modelo.put("mail", mail);
////            return "registro.html";
////        }
//        return "index.html";
//    }

    @GetMapping("/registro-empresa")
    public String registro(ModelMap modelo) {
        List<Provincia> provincias = provinciaRepositorio.findAll();
        modelo.put("provincias", provincias);
        List<Departamento> departamentos = departamentoRepositorio.findAll();
        modelo.put("departamentos", departamentos);
       return "redirect:/registro-empresa";
    }

    @PostMapping("/registrar-empresa")
    public String registrar(ModelMap modelo, @RequestParam String nombre, @RequestParam String cuil, @RequestParam String telefono, @RequestParam String mail, @RequestParam String sector, @RequestParam String direccion, @RequestParam String idProvincia, @RequestParam String idDepartamento, @RequestParam boolean rampas, @RequestParam boolean circulacion, @RequestParam boolean estacionamiento, @RequestParam boolean ingresos, @RequestParam boolean bano, @RequestParam boolean senalSonido, @RequestParam boolean escalera, @RequestParam boolean mobiliario, @RequestParam boolean braile, @RequestParam boolean ascensor) {
        try {
            empresaServicio.formularioEmpresa(nombre, cuil, telefono, mail, sector, direccion, idProvincia, idDepartamento, rampas, circulacion, estacionamiento, ingresos, bano, senalSonido, escalera, mobiliario, braile, ascensor);
        } catch (ErrorServicio ex) {
            List<Provincia> provincias = provinciaRepositorio.findAll();
            List<Departamento> departamentos = departamentoRepositorio.findAll();
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("cuil", cuil);
            modelo.put("telefono", telefono);
            modelo.put("mail", mail);
            modelo.put("direccion", direccion);
            modelo.put("provincias", provincias);
            modelo.put("departamentos", departamentos);
            return "redirect:/registro-empresa";
            }
        modelo.put("titulo", "¡Tus datos han sido procesados correctamente!");
        modelo.put("descripcion", "");
        return "redirect:/exito";
    }

    @GetMapping("/registro-postulante")
    public String registroPostulante(ModelMap modelo) {
        return "registropostulante.html";
    }

    @PostMapping("/registrar-postulante")
    public String registrarPostulante(ModelMap modelo, @RequestParam String nombre, @RequestParam String apellido, @RequestParam Date fechaNacimiento, @RequestParam String telefono, @RequestParam String mail, @RequestParam String nombreDisc, @RequestParam Genero genero, @RequestParam TipoDiscapacidad tipoDisc, @RequestParam float porcentaje) {
        try {
            postulanteServicio.formularioPostulante(nombre, apellido, fechaNacimiento, telefono, mail, nombreDisc, genero, tipoDisc, porcentaje);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("apellido", apellido);
            modelo.put("fechaNacimiento", fechaNacimiento);
            modelo.put("telefono", telefono);
            modelo.put("mail", mail);
            return "registropostulante.html";
        }
        modelo.put("titulo", "¡Tus datos han sido procesados correctamente!");
        modelo.put("descripcion", "");
        return "redirect:/exito";
    }

    @GetMapping("/perfil/empresa")
    public String perfilEmpresas(ModelMap modelo) {
    	return "redirect:/perfil/empresa";
    }

    @GetMapping("/perfil/postulante")
    public String perfilPostulante(ModelMap modelo) {
    	return "redirect:/perfil/postulante";
    }

    @GetMapping("/perfilpublico")
    public String perfilpublico(ModelMap modelo) {
    	return "redirect:/perfilpublico";
    }

    @GetMapping("/faq")
    public String faq(ModelMap modelo) {
    	return "redirect:/faq";    
    	}

    @GetMapping("/publicar-aviso")
    public String publicarAviso(ModelMap modelo) {
    	return "redirect:/publicar-aviso";
    	}

    @PostMapping("/aviso-publicado")
    public String avisoPublicado(ModelMap modelo, @RequestParam String nombreOferta, @RequestParam String descripcion, @RequestParam String id) {
        try {
            ofertaLaboralServicio.agregarOfertaLaboral(nombreOferta, descripcion, id);
        } catch (ErrorServicio ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombreOferta", nombreOferta);
            modelo.put("descripcion", descripcion);
            return "redirect:/publicar-aviso";
        }
        modelo.put("titulo", "¡Tu aviso ha sido publicado correctamente!");
        modelo.put("descripcion", "Los/as postulantes podrán verlo en minutos.s");
        return "redirect:/aviso-publicado";
    }

    @GetMapping("/listado-avisos")
    public String listadoAvisos(ModelMap modelo) {
    	return "redirect:/listado-avisos";
    }
}
