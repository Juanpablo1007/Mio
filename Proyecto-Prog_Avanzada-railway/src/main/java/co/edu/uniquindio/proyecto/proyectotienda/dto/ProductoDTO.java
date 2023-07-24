package co.edu.uniquindio.proyecto.proyectotienda.dto;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.Categoria;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ProductoDTO {

    @NotEmpty(message = "El nombre del producto no puede estar vacío.")
    @Length(max = 50, message = "El nombre del producto debe tener maximo 50 caracteres,")
    private String nombre;
    @NotEmpty(message = "La descripción del producto no puede estar vacía.")
    @Length(max = 100, message = "La descripción del producto debe tener máximo 100 caracteres.")
    private String descripcion;

    @Min(value = 1, message = "El producto debe tener como mínimo 1 unidad.")
    private int unidades;

    @Min(value = 1000, message = "El producto debe tener como mínimo un valor de COP$1000")
    private double precio;
    private List<String> imagenes;
    private List<Categoria> categorias;
}
