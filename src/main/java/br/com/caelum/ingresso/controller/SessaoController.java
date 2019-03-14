package br.com.caelum.ingresso.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.rest.OmdbClient;

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
    
    @Autowired
    private OmdbClient client;


    @GetMapping("/admin/sessao")
    public ModelAndView form(Integer salaId, SessaoForm form){
        ModelAndView modelAndView = new ModelAndView("sessao/sessao");

        form.setSalaId(salaId);
        
        modelAndView.addObject("filmes", filmeDao.findAll());
        modelAndView.addObject("sala", salaDao.findOne(salaId));
        modelAndView.addObject("form", form);

        return modelAndView;
    }




    @PostMapping("/admin/sessao")
    @Transactional
    public ModelAndView salva(@Valid SessaoForm form, BindingResult result){

        if (result.hasErrors()){
            return form(form.getSalaId() ,form);
        }

        Sessao sessao = form.toSessao(salaDao, filmeDao);
        sessaoDao.save(sessao);
        return new ModelAndView("redirect:/admin/sala/" + form.getSalaId() + "/sessoes");
    }
    
    @GetMapping("/sessao/{id}/lugares")
    public ModelAndView lugaresNaSessao(@PathVariable("id") Integer id) {

        ModelAndView modelAndView = new ModelAndView("sessao/lugares");

        Sessao sessao = sessaoDao.findOne(id);
        Optional<ImagemCapa> imagemCapa =client.request(sessao.getFilme(), ImagemCapa.class);
        modelAndView.addObject("sessao", sessao);
        modelAndView.addObject("imagemCapa", imagemCapa.orElse(new ImagemCapa()));

        return modelAndView;
    }
/*
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
*/


/*
    @DeleteMapping("/admin/sessao/{id}")
    @ResponseBody
    @Transactional
    public void delete(@PathVariable("id") Integer id){
        sessaoDao.delete(id);
    }*/
}
