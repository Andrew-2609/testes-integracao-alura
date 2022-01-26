package com.ndrewcoding.leilao.dto;

import com.ndrewcoding.leilao.model.Lance;
import com.ndrewcoding.leilao.model.Usuario;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLanceDto {

    @NotNull
    @DecimalMin(value = "0.1")
    private BigDecimal valor;

    private Long leilaoId;

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getLeilaoId() {
        return leilaoId;
    }

    public void setLeilaoId(Long leilaoId) {
        this.leilaoId = leilaoId;
    }

    public Lance toLance(Usuario usuario) {
        Lance lance = new Lance(usuario, valor);
        lance.setData(LocalDate.now());
        return lance;
    }

}
