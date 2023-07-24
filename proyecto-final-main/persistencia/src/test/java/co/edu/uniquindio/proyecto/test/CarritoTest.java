package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CarritoProductosRepo;
import co.edu.uniquindio.proyecto.repositorios.CarritoRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class CarritoTest {

    @Autowired
    private CarritoRepo carritoRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private CarritoProductosRepo carritoProductosRepo;



   @Test
   @Sql("classpath:carritos.sql")
   public void registrarTest() {

       Usuario usuario= usuarioRepo.findById("1004870909").orElse(null);
       Carrito carrito = new Carrito(usuario);


        Carrito carritoGenerado = carritoRepo.save(carrito);
        Assertions.assertNotNull(carritoGenerado);

   }

     @Test
     @Sql("classpath:carritos.sql")
     public void eliminarTest() {


          Carrito carritoGenerado = carritoRepo.findById(1).orElse(null);

          carritoRepo.delete(carritoGenerado);

          Carrito busquedaCarrito = carritoRepo.findById(1).orElse(null);

          Assertions.assertNull(busquedaCarrito);

     }

     @Test
     @Sql("classpath:carritos.sql")
     public void actualizarTest() {

          Carrito carritoGenerado = carritoRepo.findById(1).orElse(null);
          Usuario usuario = usuarioRepo.findById("1004254687").orElse(null);
          carritoGenerado.setUsuario(usuario);
          carritoRepo.save(carritoGenerado);
          Carrito busquedaCarrito = carritoRepo.findById(1).orElse(null);
          Assertions.assertEquals("Pedro", busquedaCarrito.getUsuario().getNombre());

     }

     @Test
     @Sql("classpath:carritos.sql")
     public void ListarTest() {

          List<Carrito> lista = carritoRepo.findAll();

          lista.forEach(System.out::println);

     }

    @Test
    @Sql("classpath:carritos.sql")
    public void buscarPorCedulaDeUsuario() {

        Optional<Carrito> carrito = carritoRepo.findByUsuario_Cedula("1004870909");

        if (carrito.isPresent()) {
            System.out.println(carrito.get());
        } else {
            System.err.println("No existe un usuario con esa cedula");
        }

    }

    @Test
    @Sql("classpath:carritos.sql")
    public void agregarProducto() {

        Carrito carritoGenerado = carritoRepo.findById(1).orElse(null);
        Producto producto1 = productoRepo.findById(1).orElse(null);
        Producto producto2 = productoRepo.findById(2).orElse(null);
        CarritoProductos productosEnElCarrito = new CarritoProductos();
        productosEnElCarrito.setCarrito(carritoGenerado);
        productosEnElCarrito.setUnidades(30);
        productosEnElCarrito.setProducto(producto1);
        carritoProductosRepo.save(productosEnElCarrito);
        CarritoProductosLlave llave = new CarritoProductosLlave(1,1);
        CarritoProductos productosBuscados = carritoProductosRepo.findById(llave).orElse(null);
        carritoGenerado.getProductos().add(productosBuscados);
        producto1.getCarritos().add(productosBuscados);
        Carrito carritoActualizado = carritoRepo.save(carritoGenerado);
        Producto productActualizado = productoRepo.save(producto1);
        System.out.println(productActualizado.getCarritos());
        System.out.println(carritoActualizado.getProductos());

    }

    @Test
    @Sql("classpath:carritos.sql")
    public void calcularTotalCarrito() {

        Double total = carritoProductosRepo.calcularTotalCarrito("1004870909");

        System.out.println(total);

    }


}








