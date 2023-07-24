package co.edu.uniquindio.proyecto.proyectotienda.modelo;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comentario implements Serializable {

    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Publicacion publicacion;

    @Column(nullable = false)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime fechaComentario;

}
