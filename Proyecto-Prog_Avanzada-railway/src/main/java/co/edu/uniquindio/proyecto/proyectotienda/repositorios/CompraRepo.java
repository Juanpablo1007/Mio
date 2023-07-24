package co.edu.uniquindio.proyecto.proyectotienda.repositorios;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.Compra;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepo extends JpaRepository<Compra, Integer> {
    @Query("select c from Compra c where c.cuenta.codigo = :codigoCuenta")
    List<Compra> obtenerComprasUsuario(int codigoCuenta);

    @Query("select distinct dc.publicacion from DetalleCompra dc where dc.compra.cuenta.codigo = :codigoCuenta")
    List<Publicacion> obtenerComprasUsuarioNoRepetir(int codigoCuenta);

    @Query("SELECT SUM(c.total) FROM Compra c WHERE YEAR(c.fechaCompra) = :anio AND MONTH(c.fechaCompra) = :mes")
    double calcularValorTotalVentas(int anio,  int mes);
}
