package com.Vendedores.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendedorDTO {
    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
}

