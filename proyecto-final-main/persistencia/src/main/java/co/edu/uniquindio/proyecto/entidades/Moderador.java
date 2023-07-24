package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@ToString
public class Moderador extends Persona implements Serializable {

    public Moderador(String cedula, @NotEmpty(message = "El usuario debe tener una contraseña") String contraseña, @NotEmpty(message = "El usuario debe tener un nombre") String nombre, @Email String email) {
        super(cedula, contraseña, nombre, email);
    }
}
