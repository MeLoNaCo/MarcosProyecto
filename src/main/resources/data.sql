-- Insertar Usuarios de prueba (Admin y Cliente)
-- Dejamos que el ID sea automático (IDENTITY)
INSERT INTO usuario (nombre, email, password, telefono, rol, activo) VALUES
    ('Administrador', 'admin@herogames.com', 'admin123', '999888777', 'ADMIN', TRUE),
    ('Juan Pérez', 'juan@email.com', '123456', '987654321', 'CLIENTE', TRUE),
    ('Jose Pérez', 'jose@email.com', '123456', '987654321', 'CLIENTE', TRUE);

-- Insertar Categorías
INSERT INTO categoria (nombre, activo) VALUES
     ('Videojuegos', TRUE),   -- ID se generará como 1
     ('Consolas', TRUE),      -- ID se generará como 2
     ('Accesorios', TRUE);    -- ID se generará como 3

-- Insertar Productos
-- Usamos las categorías correspondientes (1 = Videojuegos, 2 = Consolas, 3 = Accesorios)
INSERT INTO producto (nombre, descripcion, precio, stock, imagen_url, oferta, activo, id_categoria) VALUES
    ('Cyberpunk 2077', 'Juego de rol y acción en mundo abierto', 150.00, 10, 'https://images.unsplash.com/photo-1542751371-adc38448a05e', 10, TRUE, 1),
    ('PlayStation 5', 'Consola de última generación de Sony', 2500.00, 5, 'https://images.unsplash.com/photo-1606813907291-d86efa9b94db', 0, TRUE, 2),
    ('Control DualSense', 'Mando inalambrico para PS5', 300.00, 15, 'https://images.unsplash.com/photo-1600080972464-8e5f35f63d08', 15, TRUE, 3);

-- Insertar una Venta de prueba (Asumiendo que el usuario con ID 2 es Juan Pérez)
INSERT INTO venta (fecha, usuario, nombre_comprador, direccion, total, estado) VALUES
    (CURRENT_TIMESTAMP(), 2, 'Juan Pérez', 'Av. Siempre Viva 123', 2650.00, 'PAGADO'),
    (CURRENT_TIMESTAMP(), 2, 'Juan Pérez', 'Av. Siempre Viva 123', 3000.00, 'PAGADO');

-- Insertar el Detalle de la venta de prueba
-- Relacionamos con los IDs generados automáticamente de los productos (2 y 3) y ventas (1)
INSERT INTO detalle_venta (id_producto, cantidad, precio_unitario, id_venta) VALUES
    (2, 1, 50.00, 1),
    (3, 1, 80.00, 1);