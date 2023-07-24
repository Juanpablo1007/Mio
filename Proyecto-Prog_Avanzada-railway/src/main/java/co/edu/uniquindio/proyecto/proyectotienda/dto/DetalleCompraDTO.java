package co.edu.uniquindio.proyecto.proyectotienda.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class DetalleCompraDTO {
    private int codigoPublicacion;
    private int unidades;
    private double precioUnidad;
}
