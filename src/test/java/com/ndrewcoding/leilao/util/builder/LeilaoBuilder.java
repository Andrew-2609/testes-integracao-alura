package com.ndrewcoding.leilao.util.builder;

import com.ndrewcoding.leilao.model.Leilao;
import com.ndrewcoding.leilao.model.Usuario;

import java.math.BigDecimal;

public class LeilaoBuilder {

    private String nome;
    private BigDecimal valorInicial;
    private Usuario usuario;

    public LeilaoBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public LeilaoBuilder comValorInicial(String valor) {
        this.valorInicial = new BigDecimal(valor);
        return this;
    }

    public LeilaoBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Leilao criar() {
        return new Leilao(nome, valorInicial, usuario);
    }

}
