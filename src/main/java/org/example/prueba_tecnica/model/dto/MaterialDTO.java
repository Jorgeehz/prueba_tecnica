package org.example.prueba_tecnica.model.dto;

import lombok.Data;
import org.example.prueba_tecnica.entity.EstadoMaterial;

import java.time.LocalDate;

@Data
public class MaterialDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private Double precio;
    private LocalDate fechaCompra;
    private LocalDate fechaVenta;
    private EstadoMaterial estado;
    private String ciudadCodigo; // Se usará solo el código de la ciudad
}
