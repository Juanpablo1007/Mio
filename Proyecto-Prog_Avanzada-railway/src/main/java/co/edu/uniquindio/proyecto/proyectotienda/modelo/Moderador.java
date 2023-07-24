package co.edu.uniquindio.proyecto.proyectotienda.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Moderador")
public class Moderador extends Cuenta implements Serializable {

    @Column (nullable = false, length = 50)
    private String nombre;

    @Column (nullable = false, length = 50)
    private String apellido;

    @Column(length = 10)
    private String telefono;
}
