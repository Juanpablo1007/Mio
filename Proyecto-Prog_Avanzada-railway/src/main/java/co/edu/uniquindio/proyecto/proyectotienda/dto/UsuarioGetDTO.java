package co.edu.uniquindio.proyecto.proyectotienda.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UsuarioGetDTO {

    private int codigoUsuario;

    private String nombre;

    private String apellido;

    private String email;

    private String contrasenia;

    private String direccion;

    private String telefono;

}
