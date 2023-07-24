package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface ProductoRepo  extends  JpaRepository <Producto, Integer>{

 List <Producto> findAllByNombreContainsIgnoreCase(String nombre);

    List<Producto> findAllByDescripcionContainsIgnoreCase(String descripcion);

    List<Producto> findAllByUsuario_Cedula(String cedula);

    @Query ("select p from Producto p group by p.categorias  ")
    List<Objects []> listarProductosCategorias();

    List<Producto> findAllByCategoriasContains(Categoria_Producto categoria);

    List<Producto> findAllByEstado(Estado_Producto estado);

    List<Producto> findAllByIsDisponible(Boolean isDisponible);

    List<Producto> findAllByIsActivo(Boolean isActivo);

    @Query ("select p from Producto p where p.precio between :precio1 and :precio2")
    List<Producto> listarPorRangoDePrecio(Double precio1, Double precio2);


    Page<Producto> findAll(Pageable paginador);

    @Query ("select p from Usuario u, IN (u.productosFavoritos) p where u.cedula = :cedula")

    List<Producto> obtenerProductosDeUsuarioFavoritos(String cedula);
    @Query ("Select p from Producto p where p.isActivo = true ")
    List<Producto> listarProductosActivos(); //lista los productos que estan o no activos
    @Query ("Select p from Producto p where p.isDisponible = true ")
    List<Producto> listarProductosDisponibles(); //lista los productos que estan o no activos
    @Query ("select p.nombre from Producto p where p.nombre like :inicial")
    List<String> listarProductosPorInicial (String inicial );

    @Query ("select p from Producto p order by p.precio desc ")
    List<Producto> obtenerProductosDeMayorPrecio();
    @Query ("select p from Producto p order by p.precio asc ")
    List<Producto> obtenerProductosDeMenorPrecio();



         @Query ("select p from Producto p where p.estado = 'AUTORIZADO'")
         List<Producto> obtenerProductosEnVenta();


    @Query ("select p from Carrito c, IN (c.productos) p where c.usuario.cedula = :cedula")
    List<Producto> obtenerProductosDeUsuarioCarrito(String cedula);

    @Query ("select p.comentario from Producto p where p.codigo =  :id")
    List<Comentario> buscarComentarios (Integer id);

    @Query ("select p from Producto p order by p.estado desc")
    List<Producto> productosOrdenadosPorEstado();

}
