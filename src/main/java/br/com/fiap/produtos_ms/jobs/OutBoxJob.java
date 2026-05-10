package br.com.fiap.produtos_ms.jobs;

import br.com.fiap.produtos_ms.entities.OutboxEvent;
import br.com.fiap.produtos_ms.service.OutBoxService;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.propagation.Propagator;
import jakarta.jms.JMSException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OutBoxJob {

    private final OutBoxService outBoxService;
    private final JmsTemplate jmsTemplate;
    private final Logger logger = LoggerFactory.getLogger(OutBoxJob.class);

    public OutBoxJob(OutBoxService outBoxService, JmsTemplate jmsTemplate) {
        this.outBoxService = outBoxService;
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedRate = 10000)
    public void pedidosPendentes() {
        final List<OutboxEvent> pedentesToProcess = this.outBoxService.findPedentesToProcess();
        for (OutboxEvent outboxEvent : pedentesToProcess) {
            try {
                publish(outboxEvent);
                logger.info("Evento {} pendente processado com Sucesso", outboxEvent.getId());
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    @Transactional
    void publish(OutboxEvent outboxEvent) throws Exception {
            outboxEvent.marcarComoEnviado();
            this.outBoxService.save(outboxEvent);

            // Use send() instead of convertAndSend() so we can access the raw
            // JMS Message and inject the B3 trace headers before delivering.
            this.jmsTemplate.send(outboxEvent.getDestination(), session -> {
                var message = session.createTextMessage(outboxEvent.getPayload());
                return message;
            });
    }
}
