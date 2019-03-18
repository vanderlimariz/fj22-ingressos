package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class DescontoTest {
	
	private Filme filme;
	private Sala sala;
	private Sessao sessao;
	private Ingresso ingresso;
	BigDecimal precoEsperado;
	
	@Before
	public void preparaVariaveis() {
		sala = new Sala("Eldorado - Imax", new BigDecimal("20.5"));
		filme = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));		
		sessao = new Sessao(LocalTime.parse("10:00:00"),sala, filme);
		
	}
	
//	@Test
//	public void semDescontoParaIngressoNormal() {
//		ingresso = new Ingresso(sessao, new SemDesconto());
//		BigDecimal precoEsperado = sala.getPreco().add(filme.getPreco());
//		Assert.assertEquals(precoEsperado,ingresso.getPreco());
//	}
	
//	@Test
//	public void metadeDoPrecoParaEstudantes() {
//		ingresso = new Ingresso(sessao, new DescontoParaEstudantes());
//		precoEsperado = new BigDecimal("16.25");
//		Assert.assertEquals(precoEsperado,ingresso.getPreco());
//	}
}
