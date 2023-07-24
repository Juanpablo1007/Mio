package co.edu.uniquindio.proyecto.proyectotienda.dto;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.MetodoPago;
import lombok.*;
import org.hibernate.validator.constraints.CodePointLength;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CompraDTO {
    private int codigoUsuario;
    private MetodoPago metodoPago;
    private List<DetalleCompraDTO> detalleCompra;
}
