package com.ndrewcoding.leilao.controller;

import com.ndrewcoding.leilao.dao.LeilaoDao;
import com.ndrewcoding.leilao.dao.UsuarioDao;
import com.ndrewcoding.leilao.dto.NovoLanceDto;
import com.ndrewcoding.leilao.dto.NovoLeilaoDto;
import com.ndrewcoding.leilao.model.Leilao;
import com.ndrewcoding.leilao.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/leiloes")
public class LeilaoController {

    @Autowired
    private LeilaoDao leiloes;

    @Autowired
    private UsuarioDao usuarios;

    @GetMapping
    public ModelAndView index(Principal principal) {
        ModelAndView mv = new ModelAndView("leilao/index");
        mv.addObject("leiloes", leiloes.buscarTodos());
        mv.addObject("usuarioLogado", principal);
        return mv;
    }

    @GetMapping("/{id}/form")
    public ModelAndView form(@PathVariable("id") Long id, Principal principal) {
        Leilao leilao = leiloes.buscarPorId(id);
        NovoLeilaoDto form = new NovoLeilaoDto(leilao);

        ModelAndView mv = new ModelAndView("leilao/form");
        mv.addObject("usuario", principal.getName());
        mv.addObject("leilao", form);
        return mv;
    }

    @PostMapping
    @Transactional
    public ModelAndView saveOrUpdate(@Valid @ModelAttribute("leilao") NovoLeilaoDto leilaoForm, Errors errors, RedirectAttributes attr, Principal principal) {
        if (errors.hasErrors()) {
            ModelAndView mv = new ModelAndView("/leilao/form");
            mv.addObject("leilao", leilaoForm);
            mv.addObject("usuario", principal.getName());
            return mv;
        }

        Usuario usuario = usuarios.buscarPorUsername(principal.getName());
        Leilao leilao = leilaoForm.toLeilao();
        leilao.setUsuario(usuario);

        leiloes.salvar(leilao);

        attr.addFlashAttribute("message", "Leilão salvo com sucesso");

        return new ModelAndView("redirect:/leiloes");
    }

    @GetMapping("/new")
    public ModelAndView newLeilao(Principal principal) {
        ModelAndView mv = new ModelAndView("leilao/form");
        mv.addObject("usuario", principal.getName());
        mv.addObject("leilao", new NovoLeilaoDto());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id, Principal principal) {
        ModelAndView mv = new ModelAndView("leilao/show");
        mv.addObject("usuario", principal.getName());
        mv.addObject("leilao", leiloes.buscarPorId(id));
        mv.addObject("lance", new NovoLanceDto());
        return mv;
    }

}