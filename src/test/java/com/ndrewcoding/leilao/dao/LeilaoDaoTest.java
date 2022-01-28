package com.ndrewcoding.leilao.dao;

import com.ndrewcoding.leilao.model.Leilao;
import com.ndrewcoding.leilao.model.Usuario;
import com.ndrewcoding.leilao.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LeilaoDaoTest {

    private LeilaoDao leilaoDao;
    private EntityManager entityManager;

    @BeforeEach
    void beforeEach() {
        this.entityManager = JPAUtil.getEntityManager();

        this.leilaoDao = new LeilaoDao(entityManager);

        this.entityManager.getTransaction().begin();
    }

    @Test
    void deveriaCadastrarUmLeilao() {
        Usuario usuario = criarUsuario();

        Leilao leilao = new Leilao("Deutschland Flugzeug Ticket", new BigDecimal("100"), LocalDate.now(), usuario);

        Leilao leilaoSalvo = this.leilaoDao.salvar(leilao);

        Leilao leilaoEncontrado = this.leilaoDao.buscarPorId(leilaoSalvo.getId());

        Assertions.assertNotNull(leilaoEncontrado);
    }

    @Test
    void deveriaAtualizarUmLeilao() {
        Usuario usuario = criarUsuario();

        Leilao leilao = new Leilao("Deutschland Flugzeug Ticket", new BigDecimal("100"), LocalDate.now(), usuario);

        leilao = this.leilaoDao.salvar(leilao);

        leilao.setNome("Airplane Ticket to Germany");

        leilao = this.leilaoDao.salvar(leilao);

        Leilao leilaoEncontrado = this.leilaoDao.buscarPorId(leilao.getId());

        Assertions.assertEquals("Airplane Ticket to Germany", leilaoEncontrado.getNome());
        Assertions.assertEquals(usuario, leilaoEncontrado.getUsuario());
    }

    private Usuario criarUsuario() {
        Usuario novoUsuario = new Usuario("Andrew", "andrew@email.com", "13579");
        this.entityManager.persist(novoUsuario);
        return novoUsuario;
    }

    @AfterEach
    void afterEach() {
        this.entityManager.getTransaction().rollback();
    }

}
