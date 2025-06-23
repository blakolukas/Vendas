-- Sample Products Data
INSERT INTO produtos (descricao, preco_unitario) VALUES
('Notebook Dell Inspiron 15', 3499.99),
('Mouse Logitech Wireless', 89.90),
('Teclado Mecânico RGB', 299.99),
('Monitor LG 24" Full HD', 899.99),
('Webcam Logitech C920', 399.99),
('Headset Gamer HyperX', 599.99),
('SSD Kingston 500GB', 299.99),
('Memória RAM 8GB DDR4', 199.99),
('Placa de Vídeo GTX 1660', 1899.99),
('Processador Intel i5-10400', 1299.99),
('Fonte 500W 80 Plus', 399.99),
('Gabinete ATX Mid Tower', 299.99),
('Mousepad Gamer XXL', 79.99),
('Cabo HDMI 2.0 2m', 49.99),
('Hub USB 3.0 4 Portas', 89.99),
('Pendrive 32GB USB 3.0', 69.99),
('HD Externo 1TB USB 3.0', 399.99),
('Impressora HP LaserJet', 899.99),
('Scanner HP ScanJet', 599.99),
('Roteador Wi-Fi 6', 499.99);

-- Sample Inventory Data (itens_estoque)
INSERT INTO itens_estoque (produto_id, quantidade, estoque_min, estoque_max) VALUES
(1, 25, 5, 50),   -- Notebook Dell Inspiron 15
(2, 100, 20, 200), -- Mouse Logitech Wireless
(3, 30, 10, 60),   -- Teclado Mecânico RGB
(4, 15, 5, 30),    -- Monitor LG 24" Full HD
(5, 40, 10, 80),   -- Webcam Logitech C920
(6, 35, 8, 70),    -- Headset Gamer HyperX
(7, 50, 15, 100),  -- SSD Kingston 500GB
(8, 60, 20, 120),  -- Memória RAM 8GB DDR4
(9, 12, 3, 25),    -- Placa de Vídeo GTX 1660
(10, 18, 5, 35),   -- Processador Intel i5-10400
(11, 25, 8, 50),   -- Fonte 500W 80 Plus
(12, 20, 5, 40),   -- Gabinete ATX Mid Tower
(13, 80, 15, 150), -- Mousepad Gamer XXL
(14, 120, 30, 200), -- Cabo HDMI 2.0 2m
(15, 45, 10, 90),  -- Hub USB 3.0 4 Portas
(16, 200, 50, 400), -- Pendrive 32GB USB 3.0
(17, 30, 8, 60),   -- HD Externo 1TB USB 3.0
(18, 8, 2, 15),    -- Impressora HP LaserJet
(19, 12, 3, 25),   -- Scanner HP ScanJet
(20, 15, 5, 30);   -- Roteador Wi-Fi 6 