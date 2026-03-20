package br.com.fiap.produtos_ms.service;

import br.com.fiap.produtos_ms.entities.Produto;
import br.com.fiap.produtos_ms.repositories.ProdutoRepository;

import java.util.List;
import java.util.UUID;

final class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto save(Produto produto) {
        return this.repository.save(produto);
    }

    @Override
    public Produto findById(UUID id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        this.repository.deleteById(id);
    }

    public List<Produto> findAll() {
        return this.repository.findAll();
    }
}
