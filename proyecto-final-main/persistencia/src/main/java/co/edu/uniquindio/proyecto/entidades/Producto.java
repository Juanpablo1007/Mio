package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @ManyToMany(mappedBy = "productosFavoritos")
    @ToString.Exclude
    private List<Usuario> usuariosFavoritos = new ArrayList<>();

    @Column ( nullable = false)
    private Boolean isActivo;
    @Column (nullable = false, length = 100)
    private String imagen;

    @Column ( nullable = false, length = 100)
    @NotBlank(message = "El producto debe tener un nombre")
    private String nombre;

    @Column ( nullable = false, length = 100)
    private String descripcion;
    @Column ( nullable = false)
    @PositiveOrZero
    private Double precio;

    @Column ( nullable = false)
    private Boolean isDisponible;

    @Column (nullable = false)
    @PositiveOrZero
    private Integer unidades;

    @Column ( nullable = false,length = 100)
    @Enumerated(EnumType.STRING)
    private Estado_Producto estado;
    @Column( nullable = false)
    @Future
    private LocalDateTime fechaLimite;

    @Column ( nullable = false, length = 100, name = "categoria")
    @Enumerated(EnumType.STRING)
    @ElementCollection
    @ToString.Exclude
    private Set<Categoria_Producto> categorias;
    @OneToMany (mappedBy = "producto",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Comentario> comentario = new ArrayList<>();

    @OneToMany(mappedBy = "producto",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<CarritoProductos> carritos = new ArrayList<>();

    @OneToMany (mappedBy = "producto",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Transaccion> compras = new ArrayList<>();

    @OneToMany (mappedBy = "producto",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Transaccion> ventas = new ArrayList<>();

  public Producto( Usuario usuario,  Boolean isActivo, String imagen, String nombre, String descripcion, Double precio, Boolean isDisponible, Estado_Producto estado, LocalDateTime fechaLimite, Set<Categoria_Producto> categorias, Integer unidades) {this.usuario = usuario;

      this.isActivo = isActivo;
    this.imagen = imagen;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.isDisponible = isDisponible;
    this.estado = estado;
    this.fechaLimite = fechaLimite;
    this.categorias=categorias;
    this.unidades=unidades;

  }
}
