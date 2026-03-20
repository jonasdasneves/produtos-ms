package br.com.fiap.produtos_ms.service;

import br.com.fiap.produtos_ms.entities.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {
    Produto save(Produto produto);
    Produto findById(UUID id);
    List<Produto> findAll();
    void delete(UUID id);
}
