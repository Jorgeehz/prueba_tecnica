package org.example.prueba_tecnica.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Departamento {
    @Id
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;
}
