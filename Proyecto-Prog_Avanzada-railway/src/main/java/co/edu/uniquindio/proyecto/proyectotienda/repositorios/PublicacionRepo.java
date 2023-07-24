package co.edu.uniquindio.proyecto.proyectotienda.repositorios;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PublicacionRepo extends JpaRepository<Publicacion, Integer> {

    @Query("select p from Publicacion p where p.estado <> 3")
    List<Publicacion> listar();

    @Query("select p from Publicacion p where p.cuenta.codigo = :codigoCuenta and p.estado <> 3")
    List<Publicacion> obtenerPublicacionUsuario(int codigoCuenta);

    @Query("select p.producto from Publicacion p where p.codigo = :codigoPublicacion")
    Producto obtenerProductoPublicacion(int codigoPublicacion);

    @Query("select p from Publicacion p where p.fechaLimite < :fechaLimiteCuenta")
    List<Publicacion> obtenerPublicacionVencida(LocalDateTime fechaLimiteCuenta);

    @Query("select p from Publicacion p where p.estado = :estado")
    List<Publicacion> listarPublicacionEstado(EstadoPublicacion estado);

    @Query("select p from Publicacion p where lower(p.producto.nombre) like %:nombreProducto% and p.estado = 1 and p.fechaLimite > current_date")
    List<Publicacion> listarPublicacionNombre(String nombreProducto);

    @Query("select p from Publicacion p where p.producto.precio between :precioMinimo and :precioMaximo and p.estado = 1 and p.fechaLimite > current_date")
    List<Publicacion> listarPublicacionPrecio(double precioMinimo, double precioMaximo);

    @Query("select p from Publicacion p join p.producto.categorias c where c = :categoria and p.estado = 1 and p.fechaLimite > current_date")
    List<Publicacion> listarPublicacionCategoria(Categoria categoria);

    @Query("select p2 from Publicacion p2 join p2.producto.categorias c where c = :categoria and p2.producto.precio = (select min(p.producto.precio) from Publicacion p where p.estado <> 3) and p2.estado <> 3")
    List<Publicacion> baratoCategoria(Categoria categoria);

    @Query("select p2 from Publicacion p2 join p2.producto.categorias c where c = :categoria and p2.producto.precio = (select max(p.producto.precio) from Publicacion p where p.estado <> 3) and p2.estado <> 3")
    List<Publicacion> caroCategoria(Categoria categoria);

    @Query("select f from Usuario u join u.favoritos f where u.codigo = :codigoUsuario and f.estado = 1 and f.fechaLimite > current_date")
    List<Publicacion> listarPublicacionFavoritos(int codigoUsuario);

    @Query("select p from Publicacion p where p.descuento <> 0 and p.estado = 1 and p.fechaLimite > current_date")
    List<Publicacion> listarOfertas();

    @Query("select count(v) from Usuario u join u.visitas v where v.codigo = :codigoPublicacion")
    Integer cantidadVisitas(int codigoPublicacion);
}
