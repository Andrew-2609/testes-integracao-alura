package com.ndrewcoding.leilao.dao;

import com.ndrewcoding.leilao.model.Usuario;
import com.ndrewcoding.leilao.util.JPAUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsuarioDaoTest {

    private UsuarioDao usuarioDao;

    @Test
    void deveriaEncontrarUsuarioCadastrado() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        this.usuarioDao = new UsuarioDao(entityManager);

        Usuario novoUsuario = new Usuario("Andrew", "andrew@email.com", "13579");

        entityManager.getTransaction().begin();
        entityManager.persist(novoUsuario);
        entityManager.getTransaction().commit();

        Usuario usuarioEncontrado = this.usuarioDao.buscarPorUsername(novoUsuario.getNome());

        Assertions.assertNotNull(usuarioEncontrado);
    }

    @Test
    void naoDeveriaEncontrarUsuarioNaoCadastrado() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        this.usuarioDao = new UsuarioDao(entityManager);

        Usuario novoUsuario = new Usuario("Andrew", "andrew@email.com", "13579");

        entityManager.getTransaction().begin();
        entityManager.persist(novoUsuario);
        entityManager.getTransaction().commit();

        Assertions.assertThrows(NoResultException.class, () -> this.usuarioDao.buscarPorUsername("Ndrew"));
    }

}
