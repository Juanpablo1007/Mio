package co.edu.uniquindio.proyecto.proyectotienda.dto;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.EstadoCuenta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class CuentaGetDTO {

    private int codigoCuenta;

    private String rol;

    private EstadoCuenta estado;

    private String email;

    private String contrasenia;
}
