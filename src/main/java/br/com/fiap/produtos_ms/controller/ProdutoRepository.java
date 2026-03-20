package br.com.fiap.produtos_ms.controller;

import br.com.fiap.produtos_ms.service.ProdutoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produtos")
public class ProdutoRepository {

    private final ProdutoService service;

    public ProdutoRepository(ProdutoService service) {
        this.service = service;
    }
}
