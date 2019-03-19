package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Compra;


@Repository
public class CompraDao {

    @PersistenceContext
    private EntityManager manager;

    public Compra findOne(Integer id) {

        return manager.find(Compra.class, id);
    }

    public void save(Compra compra) {
        manager.merge(compra);
    }

    public List<Compra> findAll() {
        return manager.createQuery("select c from Compra c", Compra.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
