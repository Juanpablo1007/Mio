package co.edu.uniquindio.proyecto.proyectotienda.dto;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.Categoria;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class ProductoGetDTO {

    private int codigo;
    private String nombre;
    private String descripcion;
    private int unidades;
    private double precio;
    private List<String> imagenes;
    private List<Categoria> categorias;
}
