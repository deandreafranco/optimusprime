package edu.egg.Sumame.controladores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControlador {

	
	@RequestMapping("Error")
	public ModelAndView getError() {
		ModelAndView modelo = new ModelAndView();
		modelo.setViewName("error");
		return modelo;
	}
	
	@RequestMapping("main")
	public ModelAndView getMain() {
		ModelAndView modelo = new ModelAndView();
		modelo.setViewName("main");
		return modelo;
	}
	
	@RequestMapping("validador")
	public ModelAndView getValidaLogin( HttpServletRequest req, HttpServletResponse resp) {
		ModelAndView modelo = new ModelAndView();
		String EMAIL = req.getParameter("EMAIL");
		String password = req.getParameter("password");
		if ( EMAIL.equals("admin") &&  password.equals("123456") ) {
			modelo.setViewName("main");
		} else {
			modelo.setViewName("error");
		}
		
		return modelo;
	}
}
