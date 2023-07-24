package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;

@Embeddable
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CarritoProductosLlave implements Serializable {

    @Column(name = "carrito_codigo")
    Integer carritoCodigo;

    @Column(name = "producto_codigo")
    Integer productoCodigo;

}
