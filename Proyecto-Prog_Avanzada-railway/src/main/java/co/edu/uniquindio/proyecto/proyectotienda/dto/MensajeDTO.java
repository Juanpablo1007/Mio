package co.edu.uniquindio.proyecto.proyectotienda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class MensajeDTO<T> {
    private HttpStatus estado;
    private boolean error;
    private T respuesta;
}
