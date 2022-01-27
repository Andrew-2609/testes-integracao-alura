package com.ndrewcoding.leilao.dao;

import com.ndrewcoding.leilao.model.Lance;
import com.ndrewcoding.leilao.model.Leilao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class LanceDao {

    private final EntityManager em;

    @Autowired
    public LanceDao(EntityManager entityManager) {
        this.em = entityManager;
    }

    public void salvar(Lance lance) {
        em.persist(lance);
    }

    public Lance buscarMaiorLanceDoLeilao(Leilao leilao) {
        return em.createQuery("SELECT l FROM Lance l WHERE l.valor = (SELECT MAX(lance.valor) FROM Lance lance)", Lance.class)
                .setParameter("leilao", leilao)
                .getSingleResult();
    }

}
