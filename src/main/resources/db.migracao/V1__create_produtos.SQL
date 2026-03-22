CREATE TABLE PRODUTOS (
    id          VARCHAR(36) PRIMARY KEY,
    nome        VARCHAR(150) NOT NULL,
    descricao   VARCHAR(150) NOT NULL,
    preco       DECIMAL(6, 2) NOT NULL,
    categoria    INT NOT NULL
);