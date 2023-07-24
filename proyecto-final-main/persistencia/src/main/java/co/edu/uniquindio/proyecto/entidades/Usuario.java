package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.*;
@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Usuario extends Persona implements Serializable   {

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Producto> productos = new ArrayList<>();
    @ManyToMany
    @ToString.Exclude
    private List<Producto> productosFavoritos = new ArrayList<>();

    @OneToOne (mappedBy = "usuario",cascade = CascadeType.REMOVE)
    private Carrito carrito;

    @Column ( nullable = false)
    private Boolean isCuentaActiva;

    @OneToMany (mappedBy = "usuario",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany (mappedBy = "usuarioCompra",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Transaccion> compras = new ArrayList<>();

    @OneToMany (mappedBy = "usuarioVenta",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Transaccion> ventas = new ArrayList<>();


    @Column ( nullable = false, length = 100)
    private String telefono;

    @Column ( nullable = false, length = 100)
    private String direccion;



    public Usuario(String cedula, String contraseña, String nombre, String email,  Boolean isCuentaActiva, String telefono, String direccion) {
        super(cedula, contraseña, nombre, email);
        this.isCuentaActiva = isCuentaActiva;
        this.telefono = telefono;
        this.direccion = direccion;
    }


}
