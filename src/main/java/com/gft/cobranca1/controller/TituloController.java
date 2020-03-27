package com.gft.cobranca1.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.cobranca1.CadastroTituloService;
import com.gft.cobranca1.model.StatusTitulo;
import com.gft.cobranca1.model.Titulo;
import com.gft.cobranca1.repository.Titulos;
import com.gft.cobranca1.repository.filter.tituloFilter;




@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private Titulos titulos;
	
	@Autowired
	private CadastroTituloService cadastroTituloService;
	
	
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv= new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar (@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
			
		
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!");
		return "redirect:/titulos/novo";
		
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") tituloFilter filtro) {
		List<Titulo> todosTitulos = cadastroTituloService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
	
		return mv;
		
	}
	
		
	
	@RequestMapping(value = "/delete/{codigo}", method = RequestMethod.GET)
    public String excluir(@PathVariable ("codigo")Long codigo ) {
            titulos.deleteById(codigo);
            
   
        return "redirect:/titulos";
    }
	
	
	
	@ModelAttribute("todosStatusTitulo")
		public List<StatusTitulo> todosStatusTitulo(){
			return Arrays.asList(StatusTitulo.values());
		}
	
	}

