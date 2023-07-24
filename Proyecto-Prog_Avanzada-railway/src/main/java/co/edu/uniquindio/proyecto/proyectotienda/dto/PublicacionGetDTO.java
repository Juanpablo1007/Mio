package co.edu.uniquindio.proyecto.proyectotienda.dto;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class PublicacionGetDTO {
    private int codigo;

    private int codigoCuenta;

    private int codigoProducto;

    private LocalDateTime fechaLimite;

    private int descuento;

    private EstadoPublicacion estado;

    private List<ComentarioGetDTO> comentarios;

    private List<DetalleCompraGetDTO> detalleCompras;

    private ProductoGetDTO producto;
}
