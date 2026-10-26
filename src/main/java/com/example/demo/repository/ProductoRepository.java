package com.example.demo.repository;

import com.example.demo.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity,Long> {

    List<ProductoEntity> findByCategoriaId(Long idCategoria);
    List<ProductoEntity> findByActivoTrue();
    List<ProductoEntity> findByCategoriaIdAndActivoTrue(Long idCategoria);
    List<ProductoEntity> findByCategoriaNombreAndActivoTrue(String nombreCategoria);
    List<ProductoEntity> findByActivoTrueAndStockGreaterThan(int stock);

    List<ProductoEntity> findByCategoriaNombreAndActivoTrueAndStockGreaterThan(String nombre, int stock);
}
