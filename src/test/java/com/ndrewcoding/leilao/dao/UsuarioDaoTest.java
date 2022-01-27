package com.ndrewcoding.leilao.dao;

import com.ndrewcoding.leilao.model.Usuario;
import com.ndrewcoding.leilao.util.JPAUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

public class UsuarioDaoTest {

    private UsuarioDao usuarioDao;

    @Test
    void testeBuscaDeUsuarioPeloUsername() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        this.usuarioDao = new UsuarioDao(entityManager);

        Usuario novoUsuario = new Usuario("Andrew", "andrew@email.com", "13579");

        entityManager.getTransaction().begin();
        entityManager.persist(novoUsuario);
        entityManager.getTransaction().commit();

        Usuario usuarioEncontrado = this.usuarioDao.buscarPorUsername(novoUsuario.getNome());

        Assertions.assertNotNull(usuarioEncontrado);
    }

}
