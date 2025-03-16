package org.example.prueba_tecnica.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ciudad {
    @Id
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "departamento_codigo", nullable = false)
    private Departamento departamento;
}
