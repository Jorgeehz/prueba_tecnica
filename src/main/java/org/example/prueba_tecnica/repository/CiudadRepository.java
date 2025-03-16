package org.example.prueba_tecnica.repository;

import org.example.prueba_tecnica.entity.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CiudadRepository extends JpaRepository<Ciudad, String> {

}
