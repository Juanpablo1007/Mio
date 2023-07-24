package co.edu.uniquindio.proyecto.entidades;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;

// add
@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaccion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column( nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    @Column (nullable = false)
    @PositiveOrZero
    private Double total;

    @ManyToOne
    @NotNull
    private Usuario usuarioCompra;

    @ManyToOne
    @NotNull
    private Usuario usuarioVenta;


    @Column (nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private MetodoDePago metodoDePago;

    @ManyToOne
    @NotNull
    private Producto producto;

    @Column (nullable = false)
    @Min(value = 1, message = "Se debe vender minimamente una unidad del producto")
    private Integer unidadesCompradas;

    public Transaccion(LocalDateTime fecha, Double total, Usuario usuarioCompra, Usuario usuarioVenta, MetodoDePago metodoDePago, Producto producto, Integer unidadesCompradas) {
        this.fecha = fecha;
        this.total = total;
        this.usuarioCompra = usuarioCompra;
        this.usuarioVenta = usuarioVenta;
        this.metodoDePago = metodoDePago;
        this.producto = producto;
        this.unidadesCompradas = unidadesCompradas;
    }
}
