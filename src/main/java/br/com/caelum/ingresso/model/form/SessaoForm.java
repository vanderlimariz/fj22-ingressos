package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {

	private Integer id;

	@NotNull
	private Integer salaId;
	
//	@DateTimeFormat(pattern="HH:mm")
	@NotNull
	private String horario;

	@NotNull
	private Integer filmeId;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSalaId() {
		return salaId;
	}

	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Integer getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}
	
	public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao) {
		Sala sala = salaDao.findOne(salaId);
		Filme filme = filmeDao.findOne(filmeId);
		Sessao sessao = new Sessao(LocalTime.parse(this.horario),sala, filme);
		return sessao;
	}
}
