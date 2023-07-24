package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Carrito;
import co.edu.uniquindio.proyecto.entidades.CarritoProductos;
import co.edu.uniquindio.proyecto.entidades.CarritoProductosLlave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarritoProductosRepo extends JpaRepository<CarritoProductos, CarritoProductosLlave> {

 @Query("select sum(c.producto.precio * c.unidades) from CarritoProductos c where c.carrito.usuario.cedula =:usuarioCedulaCarrito")
    Double calcularTotalCarrito(String usuarioCedulaCarrito);


}
