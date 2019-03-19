package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

@Repository
public class SessaoDao {
	@PersistenceContext
    private EntityManager manager;

    public Sessao findOne(Integer id) {

        return manager.createQuery("select distinct(s) from Sessao s left join fetch s.ingressos where s.id = :id", Sessao.class)
        		.setParameter("id", id)
        		.getSingleResult();
    }

    public void save(Sessao sessao) {
        manager.merge(sessao);
    }

    public List<Sessao> findAll() {
        return manager.createQuery("select s from Sessao s", Sessao.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
    
    public List<Sessao> buscaSessoesDaSala(Sala sala){
    	return manager.createQuery("select s from Sessao s where s.sala= :sala", 
    			Sessao.class).setParameter("sala", sala).getResultList();
    }
    
    public List<Sessao> buscaSessoesDaFilme(Filme filme){
    	return manager.createQuery("select s from Sessao s where s.filme= :filme", 
    			Sessao.class).setParameter("filme", filme).getResultList();
    }
    
}
