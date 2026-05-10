package br.com.fiap.produtos_ms.service;

import br.com.fiap.produtos_ms.entities.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {
    void save(Produto produto);
    Produto findById(Long id);
    List<Produto> findAll();
    void delete(Long id);
}
