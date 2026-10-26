package com.example.demo.service;

import com.example.demo.entity.DetalleVentaEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.entity.VentaEntity;
import com.example.demo.repository.DetalleVentaRepository;
import com.example.demo.repository.VentaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    public record FilaCategoria(String nombre, long unidades, double ingresos) {}
    public record FilaProducto(String nombre, long unidades) {}

    public record Reporte(List<String> meses, List<Double> ventas, List<Integer> pedidos,
                          List<Integer> compradores, List<Long> productos, List<Double> ticket,
                          double totalMes, int ordenesMes, double ticketMes) {}

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleRepository;

    public ReporteService(VentaRepository ventaRepository, DetalleVentaRepository detalleRepository) {
        this.ventaRepository = ventaRepository;
        this.detalleRepository = detalleRepository;
    }

    @Transactional(readOnly = true)
    public Reporte generarReporte() {
        Map<YearMonth, List<VentaEntity>> porMes = ventaRepository.findAll().stream()
                .filter(v -> v.getFecha() != null)
                .collect(Collectors.groupingBy(v -> YearMonth.from(v.getFecha())));

        List<String> meses = new ArrayList<>();
        List<Double> ventas = new ArrayList<>();
        List<Integer> pedidos = new ArrayList<>();
        List<Integer> compradores = new ArrayList<>();
        List<Long> productos = new ArrayList<>();
        List<Double> ticket = new ArrayList<>();

        YearMonth actual = YearMonth.now();
        for (int i = 5; i >= 0; i--) {
            YearMonth ym = actual.minusMonths(i);
            List<VentaEntity> lista = porMes.getOrDefault(ym, List.of());

            double total = lista.stream().mapToDouble(VentaEntity::getTotal).sum();
            long unidades = lista.stream()
                    .flatMap(v -> v.getDetalles().stream())
                    .mapToLong(DetalleVentaEntity::getCantidad).sum();
            long distintos = lista.stream()
                    .map(VentaEntity::getUsuario).filter(Objects::nonNull)
                    .map(UsuarioEntity::getId).distinct().count();

            meses.add(ym.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("es-PE")));
            ventas.add(redondear(total));
            pedidos.add(lista.size());
            compradores.add((int) distintos);
            productos.add(unidades);
            ticket.add(lista.isEmpty() ? 0.0 : redondear(total / lista.size()));
        }

        int ultimo = meses.size() - 1;
        return new Reporte(meses, ventas, pedidos, compradores, productos, ticket,
                ventas.get(ultimo), pedidos.get(ultimo), ticket.get(ultimo));
    }

    @Transactional(readOnly = true)
    public List<FilaCategoria> ventasPorCategoria() {
        return detalleRepository.ventasPorCategoria().stream()
                .map(r -> new FilaCategoria((String) r[0], ((Number) r[1]).longValue(),
                        ((Number) r[2]).doubleValue()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilaProducto> productosMasVendidos(int limite) {
        return detalleRepository.productosMasVendidos(PageRequest.of(0, limite)).stream()
                .map(r -> new FilaProducto((String) r[0], ((Number) r[1]).longValue()))
                .collect(Collectors.toList());
    }

    private double redondear(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}