package org.example.prueba_tecnica.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private LocalDate fechaCompra;

    private LocalDate fechaVenta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoMaterial estado;

    @ManyToOne
    @JoinColumn(name = "ciudad_codigo", referencedColumnName = "codigo", nullable = false)
    private Ciudad ciudad;

}
