drop table if exists producto CASCADE;
drop table if exists categoria CASCADE;

create table if not exists categoria(
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(200) not null,
    descripcion VARCHAR(500) null,
    activo boolean not null default true
    );

create table if not exists producto(
    id int GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR(200) not null,
    descripcion VARCHAR(1000) null,
    precio DECIMAL(10, 2) not null,
    stock int not null default 0,
    imagen_url VARCHAR(500) null,
    oferta int not null default 0,
    activo boolean not null default true,
    id_categoria int null,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
    );


