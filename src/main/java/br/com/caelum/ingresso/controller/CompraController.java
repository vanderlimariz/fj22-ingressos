package br.com.caelum.ingresso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.CompraDao;
import br.com.caelum.ingresso.dao.IngressoDao;
import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.Cartao;
import br.com.caelum.ingresso.model.Compra;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.form.CarrinhoForm;
import br.com.caelum.ingresso.model.form.PagamentoForm;

@Controller
public class CompraController {
	@Autowired
	private SessaoDao sessaoDao;
	@Autowired
	private LugarDao lugarDao;
	@Autowired
	private Carrinho carrinho;
	
	@Autowired
	private	CompraDao	compraDao;
	
	@Autowired
	private IngressoDao ingressoDao;

	@PostMapping("/compra/ingressos")
	public ModelAndView enviarParaPagamento(CarrinhoForm carrinhoForm) {
		ModelAndView modelAndView = new ModelAndView("redirect:/compra");
		carrinhoForm.toIngressos(sessaoDao, lugarDao).forEach(carrinho::add);
		return modelAndView;
	}
	
	@GetMapping("/compra")
	public ModelAndView checkout(Cartao cartao) {
		ModelAndView modelAndView = new ModelAndView("compra/pagamento");
		modelAndView.addObject("carrinho", carrinho);
		return modelAndView;
	}
	
	@PostMapping("/compra/comprar")
	@Transactional
	public ModelAndView comprar(@Valid PagamentoForm pagamentoForm, BindingResult result) {
		synchronized (this) {

			ModelAndView modelAndView = new ModelAndView("redirect:/");
			if (result.hasErrors()) {
				return checkout(pagamentoForm.getCartao());
			}

			if (pagamentoForm.isValido()) {
				Compra compra = carrinho.toCompra();	
				for(Ingresso i : carrinho.getIngressos()) {
					boolean disponivel =ingressoDao.isDisponivel(i);
					if(!disponivel) {
						return checkout(pagamentoForm.getCartao());
					}
				}
				compraDao.save(compra);
				carrinho.limpar();
			} else {
				result.rejectValue("cartao.vencimento", "Vencimento	inválido");
				return checkout(pagamentoForm.getCartao());
			}
			return modelAndView;
		}
	}
}
