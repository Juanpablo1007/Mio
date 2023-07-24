package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Transaccion;
import co.edu.uniquindio.proyecto.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransaccionRepo extends JpaRepository <Transaccion, Integer> {

 List<Transaccion> findAllByFechaBefore(LocalDateTime fecha);

    List<Transaccion> findAllByUsuarioCompra_Nombre(String nombre);

    List<Transaccion> findAllByUsuarioVenta_Nombre(String nombre);

    List<Transaccion> findAllByProducto_Codigo(Integer codigo);

    List<Transaccion> findAllByUsuarioCompra_Cedula(String cedula);

    List<Transaccion> findAllByUsuarioVenta_Cedula(String cedula);
    @Query("select c.producto from Transaccion  c where c.usuarioCompra.cedula = :cedula ")
    List<Producto> obtenerListaProductosComprados(String cedula);

   @Query("select c.producto from Transaccion  c where c.usuarioVenta.cedula = :cedula ")
   List<Producto> obtenerListaProductosVendidos(String cedula);

    @Query("select  count (distinct c.producto) from Transaccion  c where c.usuarioCompra.cedula = :cedula ")
    Integer obtenerNumeroProductosCompradosSinRepetir(String cedula);

   @Query("select  count (distinct c.producto) from Transaccion  c where c.usuarioVenta.cedula = :cedula ")
   Integer obtenerNumeroProductosVendidosSinRepetir(String cedula);

    @Query ("select c.producto.precio * c.unidadesCompradas from Transaccion c where c.codigo = :codigo")
    Double calcularTotalCompras(Integer codigo);



}
