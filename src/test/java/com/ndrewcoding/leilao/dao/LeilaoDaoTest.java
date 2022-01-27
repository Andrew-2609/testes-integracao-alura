package com.ndrewcoding.leilao.dao;

import com.ndrewcoding.leilao.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;

public class LeilaoDaoTest {

    private LeilaoDao leilaoDao;
    private EntityManager entityManager;

    @BeforeEach
    void beforeEach() {
        this.entityManager = JPAUtil.getEntityManager();

        this.leilaoDao = new LeilaoDao(entityManager);

        this.entityManager.getTransaction().begin();
    }

    @AfterEach
    void afterEach() {
        this.entityManager.getTransaction().rollback();
    }

}
