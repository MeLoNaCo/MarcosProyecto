-- Insertar Categorías de Electrónica
insert into categoria (nombre, descripcion, activo)
VALUES ('Laptops y Computo', 'Computadoras portátiles, de escritorio y accesorios', true);

insert into categoria (nombre, descripcion, activo)
VALUES ('Consolas', 'Dispositivos móviles y telefonía inteligente', true);

insert into categoria (nombre, descripcion, activo)
VALUES ('Perifericos', 'Audífonos, parlantes y sistemas de sonido', true);


-- Insertar Productos Electrónicos (orden estricto y seguro)
INSERT INTO producto (nombre, descripcion, precio, stock, imagen_url, oferta, activo, id_categoria)
VALUES ('Laptop Gamer Pro 15', 'Laptop con procesador Ryzen 7, 16GB RAM y tarjeta gráfica dedicada', 3500.00, 8, '/images/laptop.jpg', 10, true, 1);

INSERT INTO producto (nombre, descripcion, precio, stock, imagen_url, oferta, activo, id_categoria)
VALUES ('Teclado Mecánico RGB', 'Teclado mecánico retroiluminado para gaming y oficina', 180.00, 25, '/images/teclado.jpg', 0, true, 1);

INSERT INTO producto (nombre, descripcion, precio, stock, imagen_url, oferta, activo, id_categoria)
VALUES ('Smartphone Ultra X', 'Pantalla AMOLED 6.7 pulgadas, 128GB de almacenamiento y cámara triple', 1250.00, 15, '/images/smartphone.jpg', 5, true, 2);

INSERT INTO producto (nombre, descripcion, precio, stock, imagen_url, oferta, activo, id_categoria)
VALUES ('Audífonos Inalámbricos Bluetooth', 'Cancelación de ruido activa y batería de larga duración', 220.00, 30, '/images/audifonos.jpg', 15, true, 3);

INSERT INTO producto (nombre, descripcion, precio, stock, imagen_url, oferta, activo, id_categoria)
VALUES ('Parlante Portátil Waterproof', 'Resistente al agua con sonido envolvente de alta potencia', 150.00, 12, '/images/parlante.jpg', 0, true, 3);

