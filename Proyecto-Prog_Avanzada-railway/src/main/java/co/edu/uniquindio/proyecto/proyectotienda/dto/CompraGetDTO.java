package co.edu.uniquindio.proyecto.proyectotienda.dto;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.MetodoPago;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class CompraGetDTO {

    private int codigo;

    private LocalDateTime fecha;

    private double valorTotal;

    private int codigoUsuario;

    private MetodoPago metodoPago;

    private List<DetalleCompraDTO> detalleCompra;
}
