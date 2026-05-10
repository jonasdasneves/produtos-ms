package br.com.fiap.produtos_ms.service;

import br.com.fiap.produtos_ms.dto.ProdutoMessageOutput;
import br.com.fiap.produtos_ms.entities.OutboxEvent;
import br.com.fiap.produtos_ms.entities.Produto;
import br.com.fiap.produtos_ms.repositories.OutBoxEventRepository;
import br.com.fiap.produtos_ms.repositories.ProdutoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
final class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    private final ObjectMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(ProdutoServiceImpl.class);

    private final OutBoxEventRepository outBoxEventRepository;

    private static String PRODUTO = "PRODUTO";

    private static String QUEUE = "produto.queue";

    ProdutoServiceImpl(ProdutoRepository repository, ObjectMapper mapper, OutBoxEventRepository outBoxEventRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.outBoxEventRepository = outBoxEventRepository;
    }

    @Override
    public void save(Produto produto) {
        logger.info("Salvando produto ID={}", produto.getId());
        Produto saved = this.repository.save(produto);
        try {
            final String message = mapProdutoMessage(produto);
            System.out.println(saved.getId().toString());
            this.outBoxEventRepository.save(new OutboxEvent(saved.getId().toString(), PRODUTO, QUEUE, message));
            logger.info("Evento outbox criado produtoId={} queue={}", saved.getId(), QUEUE);
        } catch (JsonProcessingException e) {
            logger.error("Erro ao serializar pedido para outbox produtoId={}", saved.getId(), e);
            throw new RuntimeException(e);
        }
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

    //Definição de mapper e DTO com base para adição de adição posterior de outros campos necessários
    private String mapProdutoMessage(Produto p) throws JsonProcessingException {
        ProdutoMessageOutput message = new ProdutoMessageOutput(p.getId());
        return this.mapper.writeValueAsString(message);
    }
}
