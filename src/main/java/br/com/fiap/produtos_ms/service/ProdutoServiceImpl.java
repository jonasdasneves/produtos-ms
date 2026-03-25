package br.com.fiap.produtos_ms.service;

import br.com.fiap.produtos_ms.entities.Produto;
import br.com.fiap.produtos_ms.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
    public Produto findById(Long id) {
        Optional<Produto> produto = this.repository.findById(id);

        return produto.orElse(null);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public List<Produto> findAll() {
        return this.repository.findAll();
    }
}
