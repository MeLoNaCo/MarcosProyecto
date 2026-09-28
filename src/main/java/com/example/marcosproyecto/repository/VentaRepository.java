package com.example.marcosproyecto.repository;

import com.example.marcosproyecto.model.Venta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// Avance 2: backend dummy con List en memoria. En Avance 3 cambiar a JpaRepository.
@Repository
public class VentaRepository {

    private final List<Venta> ventas = new ArrayList<>();
    private long siguienteId = 1;

    public Venta save(Venta venta) {
        if (venta.getId() == null) {
            venta.setId(siguienteId);
            siguienteId = siguienteId + 1;
        }
        ventas.add(venta);
        return venta;
    }

    public List<Venta> findAll() {
        return ventas;
    }

    public Venta findById(Long id) {
        for (Venta v : ventas) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    public List<Venta> findByUsuarioId(Long usuarioId) {
        List<Venta> resultado = new ArrayList<>();
        for (Venta v : ventas) {
            if (v.getUsuario() != null && v.getUsuario().getId().equals(usuarioId)) {
                resultado.add(v);
            }
        }
        return resultado;
    }
}
