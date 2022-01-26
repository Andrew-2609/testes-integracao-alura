package com.ndrewcoding.leilao.service;

import com.ndrewcoding.leilao.dao.LanceDao;
import com.ndrewcoding.leilao.dao.LeilaoDao;
import com.ndrewcoding.leilao.dao.UsuarioDao;
import com.ndrewcoding.leilao.dto.NovoLanceDto;
import com.ndrewcoding.leilao.model.Lance;
import com.ndrewcoding.leilao.model.Leilao;
import com.ndrewcoding.leilao.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanceService {

    @Autowired
    private LanceDao lances;

    @Autowired
    private UsuarioDao usuarios;

    @Autowired
    private LeilaoDao leiloes;

    public boolean propoeLance(NovoLanceDto lanceDto, String nomeUsuario) {

        Usuario usuario = usuarios.buscarPorUsername(nomeUsuario);
        Lance lance = lanceDto.toLance(usuario);

        Leilao leilao = this.getLeilao(lanceDto.getLeilaoId());

        if (leilao.propor(lance)) {
            lances.salvar(lance);
            return true;
        }

        return false;
    }

    public Leilao getLeilao(Long leilaoId) {
        return leiloes.buscarPorId(leilaoId);
    }

}