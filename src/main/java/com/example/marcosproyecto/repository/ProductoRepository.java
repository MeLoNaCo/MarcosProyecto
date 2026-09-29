package com.example.marcosproyecto.repository;

import com.example.marcosproyecto.model.Categoria;
import com.example.marcosproyecto.model.Producto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// Avance 2: backend dummy con List en memoria. En Avance 3 cambiar a JpaRepository.
// Las categorías vienen de CategoriaRepository (único dueño), no se crean aquí.
@Repository
public class ProductoRepository {

    private final List<Producto> productos = new ArrayList<>();
    private long siguienteId = 7;

    public ProductoRepository(CategoriaRepository categoriaRepository) {
        Categoria laptops = categoriaRepository.findById(2L);
        Categoria perifericos = categoriaRepository.findById(1L);
        Categoria monitores = categoriaRepository.findById(3L);
        Categoria juegos = categoriaRepository.findById(4L);

        Producto p1 = new Producto();
        p1.setId(1L);
        p1.setNombre("Laptop Gamer RTX");
        p1.setDescripcion("Intel Core i7, 16GB RAM, SSD 1TB");
        p1.setPrecio(1199.99);
        p1.setStock(10);
        p1.setOferta(10);
        p1.setImagenUrl("https://cdn.pixabay.com/photo/2020/10/21/18/07/laptop-5673901_1280.jpg");
        p1.setCategoria(laptops);
        productos.add(p1);

        Producto p2 = new Producto();
        p2.setId(2L);
        p2.setNombre("Mouse Gamer RGB");
        p2.setDescripcion("Sensor optico de 12,000 DPI");
        p2.setPrecio(9.99);
        p2.setStock(50);
        p2.setImagenUrl("https://cdn.pixabay.com/photo/2015/03/21/19/27/pc-684125_1280.jpg");
        p2.setCategoria(perifericos);
        productos.add(p2);

        Producto p3 = new Producto(3L, "Mando Xbox", "Mando de Xbox para consolas y pc",
                49.9, 10, "https://cdn.pixabay.com/photo/2015/03/21/19/27/pc-684125_1280.jpg",
                0, perifericos);
        productos.add(p3);

        Producto p4 = new Producto(4L, "Monitor Gamer 144Hz", "Panel IPS Full HD con 1ms de respuesta",
                159.99, 15, "https://cdn.pixabay.com/photo/2015/03/21/19/27/pc-684125_1280.jpg",
                0, monitores);
        productos.add(p4);

        Producto p5 = new Producto(5L, "Teclado Mecanico RGB", "Switches Red silenciosos con anti-ghosting",
                64.99, 30, "https://cdn.pixabay.com/photo/2015/03/21/19/27/pc-684125_1280.jpg",
                20, perifericos);
        productos.add(p5);

        Producto p6 = new Producto(6L, "Silla Gamer Ergonomica", "Soporte lumbar regulable y reclinable 180",
                189.99, 8, "https://cdn.pixabay.com/photo/2015/03/21/19/27/pc-684125_1280.jpg",
                0, juegos);
        productos.add(p6);
    }

    public List<Producto> findAll() {
        return productos;
    }

    public Producto findById(Long id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Producto save(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(siguienteId);
            siguienteId = siguienteId + 1;
        }
        productos.add(producto);
        return producto;
    }

    public void deleteById(Long id) {
        Producto borrar = null;
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                borrar = p;
            }
        }
        if (borrar != null) {
            productos.remove(borrar);
        }
    }
}
