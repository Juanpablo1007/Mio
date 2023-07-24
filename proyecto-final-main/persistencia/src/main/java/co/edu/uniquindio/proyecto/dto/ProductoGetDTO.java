package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoGetDTO {

    private Integer codigo;
    private String usuarioCedula;
    private String usuarioNombre;
    private Integer numeroFavoritos;
    private Boolean isActivo;
    private String imagen;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer unidades;
    private Estado_Producto estado;
    private Set<Categoria_Producto> categorias = new HashSet<>();
    private List<ComentarioGetDTO> comentarios= new ArrayList<>();
    private Boolean isDisponible;
    private LocalDateTime fechaLimite;

}
