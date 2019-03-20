package br.com.caelum.ingresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.IngressoDao;

@Controller
public class VendaController {
	
	@Autowired
	private IngressoDao ingressoDao;
	
	@GetMapping("/admin/vendas")
    public ModelAndView vendas(){
		
        ModelAndView modelAndView = new ModelAndView("venda/lista");       
        modelAndView.addObject("ingressos", ingressoDao.listar());

        return modelAndView;
    }

}
