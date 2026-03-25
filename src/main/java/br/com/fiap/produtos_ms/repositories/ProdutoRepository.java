package br.com.fiap.produtos_ms.repositories;

import br.com.fiap.produtos_ms.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
