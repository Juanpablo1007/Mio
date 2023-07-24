package co.edu.uniquindio.proyecto.proyectotienda.dto;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.EstadoCuenta;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.EstadoPublicacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class RevisionDTO {

    private int codigoCuenta;

    private int codigoPublicacion;

    private EstadoPublicacion estado;

}
