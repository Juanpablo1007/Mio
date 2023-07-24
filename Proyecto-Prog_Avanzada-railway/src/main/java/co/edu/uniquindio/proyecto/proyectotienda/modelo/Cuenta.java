package co.edu.uniquindio.proyecto.proyectotienda.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "rol")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cuenta implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(nullable = false, length = 10)
    private EstadoCuenta estado;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String contrasenia;

    @OneToMany(mappedBy = "cuenta")
    private List<Publicacion> publicaciones;

    @OneToMany(mappedBy = "cuenta")
    private List<Compra> compras;




}
