package co.edu.uniquindio.proyecto.dto;


import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmailGetDTO {
    @NotEmpty(message = "El email debe tener asunto")
    @Length(max = 100, message = "El asunto debe ser de 100 caracteres o menos")
    private String asunto;

    @NotEmpty(message = "El email debe tener un cuerpo")
    @Length(max = 2000, message = "El cuerpo debe ser de 2000 caracteres o menos")
    private String cuerpo;

    @NotEmpty(message = "El email debe tener un destinatario")
    @Length(max = 100, message = "El correo del destinatario debe ser de 100 caracteres o menos")
    private String destinatario;
}
