package org.example.prueba_tecnica.model.dto;

import org.example.prueba_tecnica.entity.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {
    public MaterialDTO toDTO(Material material) {
        MaterialDTO dto = new MaterialDTO();
        dto.setId(material.getId());
        dto.setNombre(material.getNombre());
        dto.setDescripcion(material.getDescripcion());
        dto.setTipo(material.getTipo());
        dto.setPrecio(material.getPrecio());
        dto.setFechaCompra(material.getFechaCompra());
        dto.setFechaVenta(material.getFechaVenta());
        dto.setEstado(material.getEstado());
        dto.setCiudadCodigo(material.getCiudad() != null ? material.getCiudad().getCodigo() : null);
        return dto;
    }

    public Material toEntity(MaterialDTO dto) {
        Material material = new Material();
        material.setId(dto.getId());
        material.setNombre(dto.getNombre());
        material.setDescripcion(dto.getDescripcion());
        material.setTipo(dto.getTipo());
        material.setPrecio(dto.getPrecio());
        material.setFechaCompra(dto.getFechaCompra());
        material.setFechaVenta(dto.getFechaVenta());
        material.setEstado(dto.getEstado());
        return material; // La ciudad debe ser asignada desde el servicio antes de guardar
    }
}
