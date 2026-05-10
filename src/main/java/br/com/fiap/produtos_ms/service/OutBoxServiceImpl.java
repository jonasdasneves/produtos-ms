package br.com.fiap.produtos_ms.service;

import br.com.fiap.produtos_ms.entities.OutboxEvent;
import br.com.fiap.produtos_ms.repositories.OutBoxEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OutBoxServiceImpl implements OutBoxService{

    private final OutBoxEventRepository repository;

    public OutBoxServiceImpl(OutBoxEventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OutboxEvent> findPedentesToProcess() {
        return this.repository.findDistinctPendentes();
    }

    @Override
    public OutboxEvent save(OutboxEvent outboxEvent) {
        return this.repository.save(outboxEvent);
    }
}
