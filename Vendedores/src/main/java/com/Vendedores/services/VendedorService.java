package com.Vendedores.services;

import com.Vendedores.dto.VendedorDTO;
import com.Vendedores.models.Vendedor;
import com.Vendedores.repository.VendedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public List<VendedorDTO> obtenerTodos() {
        return vendedorRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public VendedorDTO obtenerPorId(Long id) {
        return vendedorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));
    }

    public VendedorDTO crear(VendedorDTO dto) {
        Vendedor vendedor = toEntity(dto);
        return toDTO(vendedorRepository.save(vendedor));
    }

    public VendedorDTO actualizar(Long id, VendedorDTO dto) {
        Vendedor vendedor = vendedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        vendedor.setNombre(dto.getNombre());
        vendedor.setCorreo(dto.getCorreo());
        vendedor.setTelefono(dto.getTelefono());

        return toDTO(vendedorRepository.save(vendedor));
    }

    public void eliminar(Long id) {
        vendedorRepository.deleteById(id);
    }

    private VendedorDTO toDTO(Vendedor vendedor) {
        return new VendedorDTO(vendedor.getId(), vendedor.getNombre(), vendedor.getCorreo(), vendedor.getTelefono());
    }

    private Vendedor toEntity(VendedorDTO dto) {
        return new Vendedor(dto.getId(), dto.getNombre(), dto.getCorreo(), dto.getTelefono());
    }
}
