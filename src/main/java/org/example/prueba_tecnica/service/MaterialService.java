package org.example.prueba_tecnica.service;

import org.example.prueba_tecnica.entity.Ciudad;
import org.example.prueba_tecnica.entity.Material;
import org.example.prueba_tecnica.model.dto.MaterialDTO;
import org.example.prueba_tecnica.model.dto.MaterialMapper;
import org.example.prueba_tecnica.repository.MaterialRepository;
import org.example.prueba_tecnica.repository.CiudadRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final CiudadRepository ciudadRepository;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepository materialRepository, CiudadRepository ciudadRepository, MaterialMapper materialMapper) {
        this.materialRepository = materialRepository;
        this.ciudadRepository = ciudadRepository;
        this.materialMapper = materialMapper;
    }

    // Buscar todos los materiales
    public List<MaterialDTO> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar materiales por tipo
    public List<MaterialDTO> getMaterialsByTipo(String tipo) {
        return materialRepository.findByTipo(tipo).stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar materiales por fecha de compra
    public List<MaterialDTO> findByFechaCompra(LocalDate fechaCompra) {
        return materialRepository.findByFechaCompra(fechaCompra).stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Buscar materiales por ciudad
    public List<MaterialDTO> getMaterialsByCiudad(String nombreCiudad) {
        return materialRepository.findByCiudad(nombreCiudad)
                .stream()
                .map(materialMapper::toDTO)
                .collect(Collectors.toList());
    }


    // Crear nuevo material
    public ResponseEntity<String> createMaterial(MaterialDTO materialDTO) {
        // Validar que la fecha de compra no sea mayor a la fecha de venta
        if (materialDTO.getFechaCompra().isAfter(materialDTO.getFechaVenta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de compra no puede ser mayor a la fecha de venta.");
        }

        // Buscar la ciudad asociada
        Optional<Ciudad> ciudadOpt = ciudadRepository.findById(materialDTO.getCiudadCodigo());
        if (ciudadOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad no encontrada.");
        }

        // Convertir DTO a entidad
        Material material = materialMapper.toEntity(materialDTO);
        material.setCiudad(ciudadOpt.get());

        materialRepository.save(material);
        return ResponseEntity.status(HttpStatus.CREATED).body("Material creado exitosamente.");
    }

    // Actualizar material
    public MaterialDTO updateMaterial(Long id, MaterialDTO materialDTO) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado."));
        if (materialDTO.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El ID del material no puede ser nulo.");
        }


        // Validar fechas
        if (materialDTO.getFechaCompra().isAfter(materialDTO.getFechaVenta())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de compra no puede ser superior a la fecha de venta.");
        }

        // Validar la ciudad
        Ciudad ciudad = ciudadRepository.findById(materialDTO.getCiudadCodigo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad no encontrada."));


        // Actualizar datos
        material.setNombre(materialDTO.getNombre());
        material.setDescripcion(materialDTO.getDescripcion());
        material.setTipo(materialDTO.getTipo());
        material.setPrecio(materialDTO.getPrecio());
        material.setFechaCompra(materialDTO.getFechaCompra());
        material.setFechaVenta(materialDTO.getFechaVenta());
        material.setEstado(materialDTO.getEstado());
        material.setCiudad(ciudad);

        materialRepository.save(material);

        return materialMapper.toDTO(material);
    }

    // Eliminar un material por ID
    public ResponseEntity<String> deleteMaterial(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado.");
        }
        materialRepository.deleteById(id);
        return ResponseEntity.ok("Material eliminado exitosamente.");
    }

}
