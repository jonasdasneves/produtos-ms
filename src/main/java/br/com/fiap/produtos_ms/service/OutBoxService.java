package br.com.fiap.produtos_ms.service;

import br.com.fiap.produtos_ms.entities.OutboxEvent;

import java.util.List;

public interface OutBoxService {
    List<OutboxEvent> findPedentesToProcess();

    OutboxEvent save(OutboxEvent outboxEvent);
}
