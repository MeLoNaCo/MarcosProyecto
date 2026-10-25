package com.example.demo.service;

import com.example.demo.adapter.VentaAdapter;
import com.example.demo.entity.VentaEntity;
import com.example.demo.model.Venta;
import com.example.demo.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final VentaAdapter ventaAdapter;

    public VentaService(VentaRepository ventaRepository, VentaAdapter ventaAdapter) {
        this.ventaRepository = ventaRepository;
        this.ventaAdapter = ventaAdapter;
    }

    public long contarVentas(){
        return ventaRepository.count();
    }

    public List<Venta> listarVentas(){
        List<VentaEntity> ventas = ventaRepository.findAll();

        return ventas.stream()
                .map(e -> ventaAdapter.toModel(e))
                .collect(Collectors.toList());
    }

    public double contarIngresosTotales(){
        return ventaRepository.sumarTotalVentas();
    }
}
