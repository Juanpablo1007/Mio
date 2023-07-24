package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComentarioGetDTO {
    private String texto;
    private LocalDateTime fecha;
    private String usuarioCedula;
    private Integer productoCodigo;

    private Double calificacion;

}
