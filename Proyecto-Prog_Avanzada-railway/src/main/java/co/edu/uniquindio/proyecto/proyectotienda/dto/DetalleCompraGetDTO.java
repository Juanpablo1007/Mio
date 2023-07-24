package co.edu.uniquindio.proyecto.proyectotienda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DetalleCompraGetDTO {

    private int codigo;
    private int codigoPublicacion;
    private int unidades;
    private double precioUnidad;
}
