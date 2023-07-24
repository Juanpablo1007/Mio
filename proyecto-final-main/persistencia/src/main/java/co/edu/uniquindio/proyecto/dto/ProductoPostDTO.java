package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.Categoria_Producto;
import co.edu.uniquindio.proyecto.entidades.Estado_Producto;
import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductoPostDTO {

    @NotEmpty(message = "La cedula del usuario propietario del producto no puede estar vacia")
    private String usuarioCedula;
    @NotNull(message = "El producto debe tener un estado de actividad")
    private Boolean isActivo;
    @NotEmpty(message = "El url de la imagen del producto no puede estar vacio.")
    private String imagen;
    @NotEmpty(message = "El nombre del producto no puede estar vacio.")
    @Length(max = 100, message = "El nombre del producto debe tener máximo 100 caracteres.")
    private String nombre;
    @NotEmpty(message = "La descripción del producto no puede estar vacía.")
    @Length(max = 100, message = "La descripción del producto debe tener máximo 100 caracteres.")
    private String descripcion;
    @PositiveOrZero(message = "El producto debe tener un precio positivo")
    private  Double precio;
    @NotNull(message = "El producto debe tener un estado de disponibilidad")
    private  Boolean isDisponible;

    private Set<Categoria_Producto> categorias;
    @Min(value = 1, message = "El producto debe tener como mínimo 1 unidad.")
    private  Integer unidades;


}
