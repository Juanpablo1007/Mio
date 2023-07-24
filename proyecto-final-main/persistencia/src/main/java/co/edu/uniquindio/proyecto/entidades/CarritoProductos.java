package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CarritoProductos implements Serializable {

    @EmbeddedId
    @ToString.Exclude
    @EqualsAndHashCode.Include
    CarritoProductosLlave id = new CarritoProductosLlave();

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("carritoCodigo")
    @JoinColumn(name = "carrito_codigo")
    Carrito carrito;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("productoCodigo")
    @JoinColumn(name = "producto_codigo")
    Producto producto;
    @Column (nullable = false)
    @Min(value = 1, message = "No se pueden comprar 0 unidades")
    Integer unidades;


    public CarritoProductos(Carrito carrito, Producto producto, Integer unidades) {
        this.carrito = carrito;
        this.producto = producto;
        this.unidades = unidades;
    }
}
