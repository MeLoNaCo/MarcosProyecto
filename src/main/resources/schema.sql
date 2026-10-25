DROP TABLE IF EXISTS detalle_venta;
DROP TABLE IF EXISTS venta;
DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS usuario;

-- 1. Tabla Usuario
CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    telefono VARCHAR(50),
    rol VARCHAR(50) DEFAULT 'CLIENTE',
    activo BOOLEAN DEFAULT TRUE
);

-- 2. Tabla Categoria
CREATE TABLE categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
);

-- 3. Tabla Producto
CREATE TABLE producto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion TEXT,
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    imagen_url VARCHAR(255),
    oferta INT DEFAULT 0,
    activo BOOLEAN DEFAULT TRUE,
    id_categoria BIGINT,
    CONSTRAINT fk_producto_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

-- 4. Tabla Task
CREATE TABLE task (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    fecha_creacion DATE,
    id_categoria BIGINT,
    CONSTRAINT fk_task_categoria FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

-- 5. Tabla Venta
CREATE TABLE venta (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       fecha TIMESTAMP,
                       usuario BIGINT,
                       nombre_comprador VARCHAR(255),
                       direccion VARCHAR(255),
                       total DOUBLE NOT NULL,
    estado VARCHAR(50) DEFAULT 'PAGADO',
    CONSTRAINT fk_venta_usuario FOREIGN KEY (usuario) REFERENCES usuario(id)
);

CREATE TABLE detalle_Venta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_producto BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DOUBLE NOT NULL,
    id_venta BIGINT NOT NULL,
    CONSTRAINT fk_detalle_producto FOREIGN KEY (id_producto) REFERENCES producto(id),
    CONSTRAINT fk_detalle_venta FOREIGN KEY (id_venta) REFERENCES venta(id)
);