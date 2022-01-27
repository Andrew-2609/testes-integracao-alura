package com.ndrewcoding.leilao.dao;

import com.ndrewcoding.leilao.model.Usuario;
import com.ndrewcoding.leilao.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsuarioDaoTest {

    private UsuarioDao usuarioDao;
    private EntityManager entityManager;

    @BeforeEach
    void beforeEach() {
        this.entityManager = JPAUtil.getEntityManager();

        this.usuarioDao = new UsuarioDao(entityManager);

        this.entityManager.getTransaction().begin();
    }

    @Test
    void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = criarUsuario();

        Usuario usuarioEncontrado = this.usuarioDao.buscarPorUsername(usuario.getNome());

        Assertions.assertNotNull(usuarioEncontrado);
    }

    @Test
    void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        criarUsuario();

        Assertions.assertThrows(NoResultException.class, () -> this.usuarioDao.buscarPorUsername("Ndrew"));
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
