package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS )
@MappedSuperclass
public class Persona implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String cedula;

    @Column ( nullable = false, length = 100)
    @NotEmpty(message = "El usuario debe tener una contraseña")

    private String contraseña;
    @Column ( nullable = false, length = 100)
    @NotEmpty(message = "El usuario debe tener un nombre")
    private String nombre;
    @Column ( nullable = false, unique = true, length = 100)
    @Email
    private String email;

}
