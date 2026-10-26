package com.example.demo.repository;

import com.example.demo.entity.DetalleVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;


public interface DetalleVentaRepository extends JpaRepository<DetalleVentaEntity, Long> {

    // [categoría, unidades, ingresos]
    @Query("SELECT d.producto.categoria.nombre, SUM(d.cantidad), SUM(d.cantidad * d.precioUnitario) " +
            "FROM DetalleVenta d GROUP BY d.producto.categoria.nombre " +
            "ORDER BY SUM(d.cantidad * d.precioUnitario) DESC")
    List<Object[]> ventasPorCategoria();

    // [producto, unidades]
    @Query("SELECT d.producto.nombre, SUM(d.cantidad) FROM DetalleVenta d " +
            "GROUP BY d.producto.nombre ORDER BY SUM(d.cantidad) DESC")
    List<Object[]> productosMasVendidos(Pageable pageable);
}