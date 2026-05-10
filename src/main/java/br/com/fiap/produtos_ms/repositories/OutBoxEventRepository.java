package br.com.fiap.produtos_ms.repositories;

import br.com.fiap.produtos_ms.entities.OutboxEvent;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OutBoxEventRepository extends JpaRepository<OutboxEvent, UUID> {
    @Query(nativeQuery = true, value = "select distinct o.* from outbox_event o where o.status = 'PENDENTE' LIMIT 10 ")
    List<OutboxEvent> findDistinctPendentes();
}
