package org.example.prueba_tecnica.repository;

import org.example.prueba_tecnica.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {

    @NotNull
    // Buscar todos los materiales
    List<Material> findAll();

    @NotNull
    // Buscar materiales por tipo
    List<Material> findByTipo(String tipo);

    @NotNull
    // Buscar materiales por fecha de compra
    List<Material> findByFechaCompra(LocalDate fechaCompra);

    @NotNull
    // Buscar materiales por ciudad
    @Query("SELECT m FROM Material m JOIN m.ciudad c WHERE c.nombre = :nombreCiudad")
    List<Material> findByCiudad(@Param("nombreCiudad") String nombreCiudad);


}
