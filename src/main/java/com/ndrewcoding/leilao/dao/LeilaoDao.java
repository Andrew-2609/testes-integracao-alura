package com.ndrewcoding.leilao.dao;

import com.ndrewcoding.leilao.model.Leilao;
import com.ndrewcoding.leilao.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

@Repository
public class LeilaoDao {

    private final EntityManager em;

    @Autowired
    public LeilaoDao(EntityManager entityManager) {
        this.em = entityManager;
    }

    public Leilao salvar(Leilao leilao) {
        return em.merge(leilao);
    }

    public Leilao buscarPorId(Long id) {
        return em.find(Leilao.class, id);
    }

    public List<Leilao> buscarTodos() {
        return em.createQuery("SELECT l FROM Leilao l", Leilao.class)
                .getResultList();
    }

    public List<Leilao> buscarLeiloesDoPeriodo(LocalDate inicio, LocalDate fim) {
        return em.createQuery("SELECT l FROM Leilao l WHERE l.dataAbertura BETWEEN :inicio AND :fim", Leilao.class)
                .setParameter("inicio", inicio)
                .setParameter("fim", inicio)
                .getResultList();
    }

    public List<Leilao> buscarLeiloesDoUsuario(Usuario usuario) {
        return em.createQuery("SELECT l FROM Leilao l WHERE l.usuario = :usuario", Leilao.class)
                .setParameter("usuario", usuario)
                .getResultList();
    }

}
