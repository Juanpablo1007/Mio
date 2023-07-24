package co.edu.uniquindio.proyecto.proyectotienda.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class TokenDTO {
    @NotNull
    private String token;
}
