package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioGetDTO implements Serializable {
    private String cedula;
    private String nombre;
    private String email;
    private List<ProductoGetDTO> productos = new ArrayList<>(0);
    private List<ProductoGetDTO> productosFavoritos = new ArrayList<>(0);
    private CarritoGetDTO carrito;
    private Boolean isCuentaActiva;
    private List<ComentarioGetDTO> comentarios = new ArrayList<>(0);
    private List<TransaccionGetDTO> compras = new ArrayList<>(0);
    private List<TransaccionGetDTO> ventas = new ArrayList<>(0);
    private String telefono;
    private String direccion;

}
