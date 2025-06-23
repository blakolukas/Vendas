-- Sample Queries for Products and Inventory Management

-- 1. List all products with their current inventory
SELECT 
    p.id,
    p.descricao,
    p.preco_unitario,
    COALESCE(e.quantidade, 0) as quantidade_estoque,
    e.estoque_min,
    e.estoque_max
FROM produtos p
LEFT JOIN itens_estoque e ON p.id = e.produto_id
ORDER BY p.descricao;

-- 2. Products with low stock (below minimum)
SELECT 
    p.descricao,
    p.preco_unitario,
    e.quantidade as quantidade_atual,
    e.estoque_min as quantidade_minima,
    (e.estoque_min - e.quantidade) as quantidade_faltante
FROM produtos p
JOIN itens_estoque e ON p.id = e.produto_id
WHERE e.quantidade < e.estoque_min
ORDER BY (e.estoque_min - e.quantidade) DESC;

-- 3. Products out of stock
SELECT 
    p.descricao,
    p.preco_unitario,
    COALESCE(e.quantidade, 0) as quantidade_estoque
FROM produtos p
LEFT JOIN itens_estoque e ON p.id = e.produto_id
WHERE e.quantidade IS NULL OR e.quantidade = 0
ORDER BY p.descricao;

-- 4. Total inventory value
SELECT 
    SUM(p.preco_unitario * COALESCE(e.quantidade, 0)) as valor_total_estoque
FROM produtos p
LEFT JOIN itens_estoque e ON p.id = e.produto_id;

-- 5. Products by price range
SELECT 
    p.descricao,
    p.preco_unitario,
    e.quantidade
FROM produtos p
LEFT JOIN itens_estoque e ON p.id = e.produto_id
WHERE p.preco_unitario BETWEEN 100 AND 500
ORDER BY p.preco_unitario;

-- 6. Update inventory quantity (example)
-- UPDATE itens_estoque 
-- SET quantidade = quantidade - 5 
-- WHERE produto_id = 1 AND quantidade >= 5;

-- 7. Add new product with inventory
-- INSERT INTO produtos (descricao, preco_unitario) VALUES ('Novo Produto', 199.99);
-- INSERT INTO itens_estoque (produto_id, quantidade, estoque_min, estoque_max) 
-- VALUES (LASTVAL(), 50, 10, 100);

-- 8. Products with high inventory (above 80% of max)
SELECT 
    p.descricao,
    e.quantidade,
    e.estoque_max,
    ROUND((e.quantidade::float / e.estoque_max::float) * 100, 2) as percentual_estoque
FROM produtos p
JOIN itens_estoque e ON p.id = e.produto_id
WHERE (e.quantidade::float / e.estoque_max::float) > 0.8
ORDER BY percentual_estoque DESC;

-- 9. Inventory status summary
SELECT 
    COUNT(*) as total_produtos,
    COUNT(CASE WHEN e.quantidade > 0 THEN 1 END) as produtos_com_estoque,
    COUNT(CASE WHEN e.quantidade = 0 OR e.quantidade IS NULL THEN 1 END) as produtos_sem_estoque,
    COUNT(CASE WHEN e.quantidade < e.estoque_min THEN 1 END) as produtos_estoque_baixo
FROM produtos p
LEFT JOIN itens_estoque e ON p.id = e.produto_id;

-- 10. Top 5 most expensive products
SELECT 
    p.descricao,
    p.preco_unitario,
    e.quantidade
FROM produtos p
LEFT JOIN itens_estoque e ON p.id = e.produto_id
ORDER BY p.preco_unitario DESC
LIMIT 5; 