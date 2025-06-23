DROP TABLE IF EXISTS itens_pedido CASCADE;
DROP TABLE IF EXISTS itens_estoque CASCADE;
DROP TABLE IF EXISTS pedidos CASCADE;
DROP TABLE IF EXISTS orcamentos CASCADE;
DROP TABLE IF EXISTS produtos CASCADE;

CREATE TABLE produtos (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    preco_unitario DOUBLE PRECISION NOT NULL
);

CREATE TABLE orcamentos (
    id BIGSERIAL PRIMARY KEY,
    custo_itens DOUBLE PRECISION,
    imposto DOUBLE PRECISION,
    desconto DOUBLE PRECISION,
    custo_consumidor DOUBLE PRECISION,
    efetivado BOOLEAN,
    estado VARCHAR(255),
    data_criacao TIMESTAMP
);

CREATE TABLE pedidos (
    id BIGSERIAL PRIMARY KEY,
    estado VARCHAR(255)
);

CREATE TABLE itens_estoque (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT REFERENCES produtos(id),
    quantidade INTEGER,
    estoque_min INTEGER,
    estoque_max INTEGER
);

CREATE TABLE itens_pedido (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT REFERENCES pedidos(id),
    produto_id BIGINT REFERENCES produtos(id),
    orcamento_id BIGINT REFERENCES orcamentos(id),
    quantidade INTEGER
); 