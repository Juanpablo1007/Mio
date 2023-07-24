package co.edu.uniquindio.proyecto.proyectotienda.dto;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public class ComentarioGetDTO {
    private int codigo;
    private LocalDateTime fecha;
    private String mensaje;
    private int codigoUsuario;
    private int codigoProducto;
}
