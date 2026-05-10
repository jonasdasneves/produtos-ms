# produtos-ms - CP5 Java Advanced

Microsservico de catalogo de produtos com foco em:

- Outbox Pattern para publicacao assicrona em `produto.queue`
- Observabilidade com logs, metricas (Actuator/Micrometer) e tracing (Zipkin)

## Repositorios relacionados

- `produtos-ms` (este repositorio)
- `vendas-ms`: https://github.com/jonasdasneves/vendas-ms.git

## Requisitos CP5 atendidos neste servico

- Outbox integrado ao salvamento de produto (evento pendente salvo na mesma camada de servico)
- Job agendado para publicar eventos pendentes
- Propagacao de contexto de trace nos headers JMS
- Actuator exposto para verificacao de saude, informacoes e metricas

## Endpoints importantes

- Aplicacao: `http://localhost:8082`
- Actuator health: `http://localhost:8082/actuator/health`
- Actuator info: `http://localhost:8082/actuator/info`
- Actuator metrics: `http://localhost:8082/actuator/metrics`
- Actuator http exchanges: `http://localhost:8082/actuator/httpexchanges`
- Actuator prometheus: `http://localhost:8082/actuator/prometheus`

## Banco e migrations

As migrations estao em `src/main/resources/db.migracao` e incluem:

- `V1__create_produto.SQL`
- `V2__create_usuarios.SQL`
- `V3__create_outbox_event.SQL`

## Evidencia de rastreabilidade

Salvar o print solicitado no enunciado em:

- `evidencias/trace.png`

O trace deve mostrar no mesmo `traceId`:

1. Requisicao HTTP de salvamento no `produtos-ms`
2. Publicacao no job do outbox (`produtos-ms`)
3. Consumo JMS no `vendas-ms`

