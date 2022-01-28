package com.ndrewcoding.leilao.dao;

import com.ndrewcoding.leilao.model.Leilao;
import com.ndrewcoding.leilao.model.Usuario;
import com.ndrewcoding.leilao.util.JPAUtil;
import com.ndrewcoding.leilao.util.builder.LeilaoBuilder;
import com.ndrewcoding.leilao.util.builder.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
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
        Usuario usuario = new UsuarioBuilder()
                .comNome("Andrew")
                .comEmail("andrew@email.com")
                .comSenha("13579")
                .criar();

        entityManager.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Computer")
                .comValorInicial("30")
                .comUsuario(usuario)
                .comDataDeAbertura(LocalDate.now())
                .criar();

        leilao = this.leilaoDao.salvar(leilao);

        Leilao leilaoEncontrado = this.leilaoDao.buscarPorId(leilao.getId());

        Assertions.assertNotNull(leilaoEncontrado);
    }

    @Test
    void deveriaAtualizarUmLeilao() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Andrew")
                .comEmail("andrew@email.com")
                .comSenha("13579")
                .criar();

        entityManager.persist(usuario);

        Leilao leilao = new LeilaoBuilder()
                .comNome("Computer")
                .comValorInicial("30")
                .comUsuario(usuario)
                .comDataDeAbertura(LocalDate.now())
                .criar();

        leilao = this.leilaoDao.salvar(leilao);

        leilao.setNome("Airplane Ticket to Germany");

        leilao = this.leilaoDao.salvar(leilao);

        Leilao leilaoEncontrado = this.leilaoDao.buscarPorId(leilao.getId());

        Assertions.assertEquals("Airplane Ticket to Germany", leilaoEncontrado.getNome());
        Assertions.assertEquals(usuario, leilaoEncontrado.getUsuario());
    }

    @AfterEach
    void afterEach() {
        this.entityManager.getTransaction().rollback();
    }

}
