package com.example.demo.repository;

import com.example.demo.entity.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VentaRepository extends JpaRepository<VentaEntity, Long> {

    @Query("SELECT COALESCE(SUM(v.total), 0) FROM venta v")
    double sumarTotalVentas();
}
