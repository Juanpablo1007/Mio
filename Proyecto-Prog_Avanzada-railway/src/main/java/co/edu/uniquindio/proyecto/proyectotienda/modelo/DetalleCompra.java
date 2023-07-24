package co.edu.uniquindio.proyecto.proyectotienda.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class DetalleCompra implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;
    @ManyToOne
    private Publicacion publicacion;
    @ManyToOne
    private Compra compra;
    @Column(nullable = false)
    private int unidades;
    @Column(nullable = false)
    private double precioUnidad;
}
