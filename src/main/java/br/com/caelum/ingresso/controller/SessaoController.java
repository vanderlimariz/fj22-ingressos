package br.com.caelum.ingresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;

/**
 * Created by nando on 03/03/17.
 */
@Controller
public class SessaoController {

    @Autowired
    private SessaoDao sessaoDao;
    
    @Autowired
    private FilmeDao filmeDao;
    
    @Autowired
    private SalaDao salaDao;


    @GetMapping("/admin/sessao")
    public ModelAndView form(Integer salaId){
        ModelAndView modelAndView = new ModelAndView("sessao/sessao");

        modelAndView.addObject("filmes", filmeDao.findAll());
        modelAndView.addObject("sala", salaDao.findOne(salaId));
        

        return modelAndView;
    }




    /*  @PostMapping("/admin/sessao")
    @Transactional
    public ModelAndView salva(@Valid Sessao sessao, BindingResult result){

        if (result.hasErrors()){
            return form(Optional.ofNullable(sessao.getId()) ,sessao);
        }

        sessaoDao.save(sessao);
        return new ModelAndView("redirect:/admin/sessaos");
    }

    @GetMapping("/admin/sessaos")
    public ModelAndView lista(){
        ModelAndView modelAndView = new ModelAndView("sessao/lista");

        modelAndView.addObject("sessaos", sessaoDao.findAll());

        return modelAndView;
    }


    @GetMapping("/admin/sessao/{id}/sessoes")
    public ModelAndView listaSessoes(@PathVariable("id") Integer id) {

        Sessao sessao = sessaoDao.findOne(id);

        ModelAndView view = new ModelAndView("sessao/lista");
        view.addObject("sessao", sessao);

        return view;
    }

    @GetMapping("/admin/sessao/{id}/lugares/")
    public ModelAndView listaLugares(@PathVariable("id") Integer id) {

        ModelAndView modelAndView = new ModelAndView("lugar/lista");

        Sessao sessao = sessaoDao.findOne(id);
        modelAndView.addObject("sessao", sessao);

        return modelAndView;
    }


    @DeleteMapping("/admin/sessao/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        sessaoDao.delete(id);
    }*/
}
