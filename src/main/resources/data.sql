-- USUARIOS
INSERT INTO usuario (nombre, email, password, telefono, rol, activo) VALUES
    ('Administrador', 'admin@herogames.com', 'admin123', '999888777', 'ADMIN', TRUE),   -- 1
    ('Juan Pérez', 'juan@email.com', '123456', '987654321', 'CLIENTE', TRUE),          -- 2
    ('Jose Pérez', 'jose@email.com', '123456', '987654322', 'CLIENTE', TRUE);          -- 3

-- CATEGORÍAS (3 nuevas: 4, 5 y 6)
INSERT INTO categoria (nombre, activo) VALUES
    ('Videojuegos', TRUE),          -- 1
    ('Consolas', TRUE),             -- 2
    ('Accesorios', TRUE),           -- 3
    ('Periféricos', TRUE),          -- 4
    ('Tarjetas de regalo', TRUE),   -- 5
    ('Coleccionables', TRUE);       -- 6

-- PRODUCTOS (19 en total)
INSERT INTO producto (nombre, descripcion, precio, stock, imagen_url, oferta, activo, id_categoria) VALUES
    ('Cyberpunk 2077', 'Juego de rol y acción en mundo abierto', 150.00, 10, 'https://images.unsplash.com/photo-1542751371-adc38448a05e', 10, TRUE, 1),          -- 1
    ('PlayStation 5', 'Consola de última generación de Sony', 2500.00, 5, 'https://images.unsplash.com/photo-1606813907291-d86efa9b94db', 0, TRUE, 2),            -- 2
    ('Control DualSense', 'Mando inalámbrico para PS5', 300.00, 15, 'https://images.unsplash.com/photo-1600080972464-8e5f35f63d08', 15, TRUE, 3),                  -- 3
    ('Zelda: Tears of the Kingdom', 'Aventura épica en Hyrule para Nintendo Switch', 250.00, 12, 'https://images.unsplash.com/photo-1542751371-adc38448a05e', 0, TRUE, 1),   -- 4
    ('God of War Ragnarök', 'Acción y mitología nórdica para PlayStation', 200.00, 8, 'https://images.unsplash.com/photo-1542751371-adc38448a05e', 20, TRUE, 1),         -- 5
    ('EA Sports FC 25', 'El simulador de fútbol más popular', 220.00, 20, 'https://images.unsplash.com/photo-1542751371-adc38448a05e', 0, TRUE, 1),                  -- 6
    ('Elden Ring', 'RPG de acción de mundo abierto', 180.00, 9, 'https://images.unsplash.com/photo-1542751371-adc38448a05e', 15, TRUE, 1),                            -- 7
    ('Nintendo Switch OLED', 'Consola híbrida con pantalla OLED de 7 pulgadas', 1400.00, 6, 'https://images.unsplash.com/photo-1606813907291-d86efa9b94db', 5, TRUE, 2),  -- 8
    ('Xbox Series X', 'Consola de Microsoft con 1TB de almacenamiento', 2300.00, 4, 'https://images.unsplash.com/photo-1606813907291-d86efa9b94db', 0, TRUE, 2),       -- 9
    ('Steam Deck', 'PC portátil para juegos', 2100.00, 3, 'https://images.unsplash.com/photo-1606813907291-d86efa9b94db', 10, TRUE, 2),                                -- 10
    ('Audífonos Pulse 3D', 'Audífonos inalámbricos con audio 3D para PS5', 450.00, 14, 'https://images.unsplash.com/photo-1600080972464-8e5f35f63d08', 10, TRUE, 3),   -- 11
    ('Cable HDMI 2.1', 'Cable 8K de alta velocidad, 2 metros', 60.00, 40, 'https://images.unsplash.com/photo-1600080972464-8e5f35f63d08', 0, TRUE, 3),               -- 12
    ('Teclado Mecánico RGB', 'Teclado mecánico con switches rojos e iluminación RGB', 280.00, 18, 'https://images.unsplash.com/photo-1600080972464-8e5f35f63d08', 10, TRUE, 4),  -- 13
    ('Mouse Gamer 16000 DPI', 'Mouse ergonómico con 7 botones programables', 150.00, 25, 'https://images.unsplash.com/photo-1600080972464-8e5f35f63d08', 0, TRUE, 4),      -- 14
    ('Monitor Gamer 27" 165Hz', 'Monitor QHD con tasa de refresco de 165Hz', 1200.00, 5, 'https://images.unsplash.com/photo-1600080972464-8e5f35f63d08', 15, TRUE, 4),   -- 15
                                                                                                        ('Tarjeta PSN S/ 100', 'Recarga de saldo para PlayStation Store', 100.00, 50, 'https://images.unsplash.com/photo-1606813907291-d86efa9b94db', 0, TRUE, 5),         -- 16
                                                                                                        ('Xbox Game Pass 3 meses', 'Suscripción de 3 meses a Game Pass Ultimate', 150.00, 30, 'https://images.unsplash.com/photo-1606813907291-d86efa9b94db', 0, TRUE, 5),  -- 17
    ('Funko Pop Kratos', 'Figura coleccionable de Kratos', 90.00, 0, 'https://images.unsplash.com/photo-1542751371-adc38448a05e', 0, TRUE, 6),                      -- 18 (agotado, para probar)
    ('Figura Master Chief', 'Figura articulada de 30 cm', 350.00, 7, 'https://images.unsplash.com/photo-1542751371-adc38448a05e', 25, TRUE, 6);                      -- 19

-- VENTAS (ids 1 a 7, en este orden). total = suma de sus detalles
INSERT INTO venta (fecha, usuario, nombre_comprador, direccion, total, estado) VALUES
                                                                                   (DATEADD('MONTH', -5, CURRENT_TIMESTAMP()), 2, 'Juan Pérez', 'Av. Siempre Viva 123', 2755.00, 'PAGADO'),   -- 1
                                                                                   (DATEADD('MONTH', -4, CURRENT_TIMESTAMP()), 2, 'Juan Pérez', 'Av. Siempre Viva 123', 330.00, 'PAGADO'),    -- 2
                                                                                   (DATEADD('MONTH', -3, CURRENT_TIMESTAMP()), 3, 'Jose Pérez', 'Jr. Los Olivos 456', 1580.00, 'PAGADO'),    -- 3
                                                                                   (DATEADD('MONTH', -2, CURRENT_TIMESTAMP()), 3, 'Jose Pérez', 'Jr. Los Olivos 456', 807.00, 'PAGADO'),     -- 4
                                                                                   (DATEADD('MONTH', -1, CURRENT_TIMESTAMP()), 2, 'Juan Pérez', 'Av. Siempre Viva 123', 513.00, 'PAGADO'),   -- 5
                                                                                   (CURRENT_TIMESTAMP(), 3, 'Jose Pérez', 'Jr. Los Olivos 456', 772.50, 'PAGADO'),                           -- 6
                                                                                   (CURRENT_TIMESTAMP(), 2, 'Juan Pérez', 'Av. Siempre Viva 123', 1320.00, 'PAGADO');                        -- 7

-- DETALLES (id_venta apunta a la venta; precio_unitario = precio final con oferta)
INSERT INTO detalle_venta (id_producto, cantidad, precio_unitario, id_venta) VALUES
                                                                                 -- Venta 1: 2500 + 255 = 2755
                                                                                 (2, 1, 2500.00, 1),
                                                                                 (3, 1, 255.00, 1),
                                                                                 -- Venta 2: 2x135 + 60 = 330
                                                                                 (1, 2, 135.00, 2),
                                                                                 (12, 1, 60.00, 2),
                                                                                 -- Venta 3: 1330 + 250 = 1580
                                                                                 (8, 1, 1330.00, 3),
                                                                                 (4, 1, 250.00, 3),
                                                                                 -- Venta 4: 252 + 150 + 405 = 807
                                                                                 (13, 1, 252.00, 4),
                                                                                 (14, 1, 150.00, 4),
                                                                                 (11, 1, 405.00, 4),
                                                                                 -- Venta 5: 153 + 160 + 2x100 = 513
                                                                                 (7, 1, 153.00, 5),
                                                                                 (5, 1, 160.00, 5),
                                                                                 (16, 2, 100.00, 5),
                                                                                 -- Venta 6: 2x255 + 262.50 = 772.50
                                                                                 (3, 2, 255.00, 6),
                                                                                 (19, 1, 262.50, 6),
                                                                                 -- Venta 7: 1020 + 2x150 = 1320
                                                                                 (15, 1, 1020.00, 7),
                                                                                 (14, 2, 150.00, 7);