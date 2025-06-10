package com.Vendedores.controllers;

import com.Vendedores.dto.VendedorDTO;
import com.Vendedores.services.VendedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
@RequiredArgsConstructor
public class VendedorController {

    private final VendedorService vendedorService;

    @GetMapping
    public List<VendedorDTO> listarTodos() {
        return vendedorService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public VendedorDTO obtenerPorId(@PathVariable Long id) {
        return vendedorService.obtenerPorId(id);
    }

    @PostMapping
    public ResponseEntity<VendedorDTO> crear(@RequestBody VendedorDTO dto) {
        return new ResponseEntity<>(vendedorService.crear(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public VendedorDTO actualizar(@PathVariable Long id, @RequestBody VendedorDTO dto) {
        return vendedorService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        vendedorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
