package com.gft.cobranca1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gft.cobranca1.model.Titulo;
import com.gft.cobranca1.repository.Titulos;



@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	
	@Autowired
	private Titulos titulos;
	
	
	@RequestMapping("/novo")
	public String novo() {
		return "CadastroTitulo";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar (Titulo titulo) {
		
		titulos.save(titulo);
		ModelAndView mv= new ModelAndView("CadastroTitulo");
		mv.addObject("mensagem", "Titulo salvo com sucesso!");
		return mv;
		
	}
	
	@RequestMapping
	public String pesquisar() {
		return "PesquisaTitulos";
	}
}
