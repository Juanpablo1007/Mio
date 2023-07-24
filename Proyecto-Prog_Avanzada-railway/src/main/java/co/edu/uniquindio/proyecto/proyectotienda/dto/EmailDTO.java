package co.edu.uniquindio.proyecto.proyectotienda.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@Getter
@Setter
public class EmailDTO {

    @NotEmpty(message = "")
    @Length(max = 100, message = "")
    private String asunto;

    @NotEmpty(message = "")
    @Length(max = 2000, message = "")
    private String cuerpo;

    @NotEmpty(message = "")
    @Length(max = 100, message = "")
    private String destinatario;
}
