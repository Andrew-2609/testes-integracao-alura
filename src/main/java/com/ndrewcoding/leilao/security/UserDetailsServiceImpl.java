package com.ndrewcoding.leilao.security;

import com.ndrewcoding.leilao.dao.UsuarioDao;
import com.ndrewcoding.leilao.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioDao.buscarPorUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario nao encontrado");
        }

        return new LeilaoUserDetails(user);
    }

}
