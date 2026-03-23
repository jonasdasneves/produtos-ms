CREATE TABLE produto (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(150) NOT NULL,
    descricao   VARCHAR(150) NOT NULL,
    preco       DECIMAL(6, 2) NOT NULL,
    categoria_enum   VARCHAR(35) NOT NULL
);