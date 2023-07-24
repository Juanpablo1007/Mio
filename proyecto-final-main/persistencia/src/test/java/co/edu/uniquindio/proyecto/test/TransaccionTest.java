package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.TransaccionRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransaccionTest {

    @Autowired
    private TransaccionRepo transaccionRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Test
    @Sql("classpath:transacciones.sql")
    public void registrarTest() {

        Usuario usuarioVendedor= usuarioRepo.findById("1004870909").orElse(null);

        Producto producto=  productoRepo.findById(1).orElse(null);

        Usuario usuarioComprador= usuarioRepo.findById("1004223311").orElse(null);

        int unidadesVendidas = 2;

        Transaccion compra = new Transaccion(LocalDateTime.now(),producto.getPrecio()*unidadesVendidas,usuarioComprador,usuarioVendedor,MetodoDePago.TARJETA_DE_CREDITO,producto,unidadesVendidas);

        Transaccion compraGenerada = transaccionRepo.save(compra);

        System.out.println(compra);

        Assertions.assertNotNull(compraGenerada);

    }
    @Test
    @Sql("classpath:transacciones.sql")
    public void eliminarTest() {


        Transaccion compraGenerada = transaccionRepo.findById(1).orElse(null);

        transaccionRepo.delete(compraGenerada);

        Transaccion busquedaCompra = transaccionRepo.findById(1).orElse(null);

        Assertions.assertNull(busquedaCompra);

    }

    @Test
    @Sql("classpath:transacciones.sql")
    public void actualizarTest() {

        Transaccion compraGenerada = transaccionRepo.findById(1).orElse(null);
        compraGenerada.setMetodoDePago(MetodoDePago.TRANSACCION_BANCARIA);
        transaccionRepo.save(compraGenerada);
        Transaccion busquedaCompra = transaccionRepo.findById(1).orElse(null);
        Assertions.assertEquals(MetodoDePago.TRANSACCION_BANCARIA, busquedaCompra.getMetodoDePago());

    }
    @Test
    @Sql("classpath:transacciones.sql")
    public void ListarTest() {

        List<Transaccion> lista = transaccionRepo.findAll();

        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:transacciones.sql")
    public void buscarPorFechasAnteriores() {

        List<Transaccion> lista = transaccionRepo.findAllByFechaBefore(LocalDateTime.now());

        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:transacciones.sql")
    public void buscarPorNombreDeUsuario() {

        List<Transaccion> lista = transaccionRepo.findAllByUsuarioVenta_Nombre("Juan");

        lista.forEach(System.out::println);

    }
    @Test
    @Sql("classpath:transacciones.sql")
    public void buscarPorCedulaDeUsuario() {

        List<Transaccion> lista = transaccionRepo.findAllByUsuarioCompra_Cedula("1004870909");

        lista.forEach(System.out::println);

    }
    @Test
    @Sql("classpath:transacciones.sql")
    public void buscarPorIdDeProducto() {

        List<Transaccion> lista = transaccionRepo.findAllByProducto_Codigo(3);

        lista.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:transacciones.sql")
    public void ListarProductosCompradosPorUsuario() {

        List<Producto> lista = transaccionRepo.obtenerListaProductosVendidos("1004870909");

        lista.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:transacciones.sql")
    public void ListarCantidadProductosCompradosPorUsuario() {

        Integer total = transaccionRepo.obtenerNumeroProductosCompradosSinRepetir("1004870909");

       System.out.println(total);
    }
    @Test
    @Sql("classpath:transacciones.sql")
    public void calcularTotalCompras() {


        Double compras = transaccionRepo.calcularTotalCompras(3);
        System.out.println(compras);
    }

}
