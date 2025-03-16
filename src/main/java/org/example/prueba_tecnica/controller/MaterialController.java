package org.example.prueba_tecnica.controller;

import org.example.prueba_tecnica.model.dto.MaterialDTO;
import org.example.prueba_tecnica.service.MaterialService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/materiales")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    // Obtener todos los materiales
    @GetMapping
    public ResponseEntity<List<MaterialDTO>> getAllMaterials() {
        return ResponseEntity.ok(materialService.getAllMaterials());
    }

    // Buscar materiales por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<MaterialDTO>> getMaterialsByTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(materialService.getMaterialsByTipo(tipo));
    }

    // Buscar materiales por fecha de compra
    @GetMapping("/fecha-compra")
    public ResponseEntity<List<MaterialDTO>> getMaterialesByFechaCompra(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaCompra) {

        List<MaterialDTO> materiales = materialService.findByFechaCompra(fechaCompra);
        if (materiales.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron materiales con esa fecha.");
        }
        return ResponseEntity.ok(materiales);
    }

    // Buscar materiales por ciudad
    @GetMapping("/ciudad")
    public ResponseEntity<List<MaterialDTO>> getMaterialsByCiudad(@RequestParam String nombreCiudad) {
        return ResponseEntity.ok(materialService.getMaterialsByCiudad(nombreCiudad));
    }


    // Crear un nuevo material
    @PostMapping
    public ResponseEntity<String> createMaterial(@RequestBody MaterialDTO materialDTO) {
        return materialService.createMaterial(materialDTO);
    }

    // Actualizar un material existente
    @PutMapping("/{id}")
    public ResponseEntity<MaterialDTO> updateMaterial(@PathVariable Long id, @RequestBody MaterialDTO materialDTO) {
        MaterialDTO updatedMaterial = materialService.updateMaterial(id, materialDTO);
        return ResponseEntity.ok(updatedMaterial);
    }
    // Eliminar un material
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMaterial(@PathVariable Long id) {
        return materialService.deleteMaterial(id);
    }

}
