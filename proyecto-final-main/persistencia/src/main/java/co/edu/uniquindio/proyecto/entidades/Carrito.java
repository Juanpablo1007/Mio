package co.edu.uniquindio.proyecto.entidades;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor


public class Carrito implements  Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @OneToMany(mappedBy = "carrito",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List <CarritoProductos> productos = new ArrayList<>();;

    @OneToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    public Carrito(Usuario usuario) {

        this.usuario = usuario;

    }

    @Override
    public String toString() {
        return "Carrito(" +"codigo: " + codigo + ", cedula de usuario: " + usuario.getCedula() + ")";
    }
}
