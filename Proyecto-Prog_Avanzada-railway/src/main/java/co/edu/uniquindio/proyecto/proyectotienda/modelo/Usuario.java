package co.edu.uniquindio.proyecto.proyectotienda.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("Usuario")
public class Usuario extends Cuenta implements Serializable {

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(nullable = false, length = 10)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @ManyToMany
    @JoinTable(name = "favorito",
            joinColumns = {@JoinColumn(name = "cuenta_codigo")},
            inverseJoinColumns = {@JoinColumn(name = "publicacion_codigo")})
    private List<Publicacion> favoritos;

    @ManyToMany
    @JoinTable(name = "visita",
            joinColumns = {@JoinColumn(name = "cuenta_codigo")},
            inverseJoinColumns = {@JoinColumn(name = "publicacion_codigo")})
    private List<Publicacion> visitas;
}


