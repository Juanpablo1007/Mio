package co.edu.uniquindio.proyecto.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComentarioPostDTO {
    @NotEmpty(message = "El texto del comentario no puede estar vacio")
    @Length(max = 100, message = "El comentario debe ser de 100 o menos caracteres")
    private String texto;
    @Min(value = 1, message = "La minima calificacion es 1")
    @Max(value = 5,message = "La maxima calificacion es 5")
    @NotNull
    private Double calificacion;

    private String usuarioCedula;
    private Integer productoCodigo;

}
