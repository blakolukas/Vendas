-- Inserir produtos
INSERT INTO produtos (descricao, preco_unitario) VALUES 
('Televisor', 2000.0),
('Geladeira', 3500.0),
('Fogao', 1200.0),
('Lava-louça', 1800.0),
('Lava-roupas', 2870.0)
ON CONFLICT DO NOTHING;

-- Inserir itens de estoque
INSERT INTO itens_estoque (produto_id, quantidade, estoque_min, estoque_max) VALUES 
(1, 10, 5, 20),
(2, 8, 3, 15),
(3, 15, 5, 25),
(4, 12, 4, 18),
(5, 6, 2, 12)
ON CONFLICT DO NOTHING; 