package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest {


    @Autowired
    private ProductoRepo productoRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    @Sql("classpath:productos.sql")
    public void registrarTest(){

        Usuario usuario= usuarioRepo.findById("1004870909").orElse(null);
        Set<Categoria_Producto> categorias = new HashSet<Categoria_Producto>();
        categorias.add(Categoria_Producto.BEBES);
        categorias.add(Categoria_Producto.CONSTRUCCION);
        categorias.add(Categoria_Producto.HERRAMIENTAS);

        Producto producto = new Producto(usuario,true,"imagen","Nintendo","Consola nintendo",3000.0,true,Estado_Producto.SIN_REVISAR,LocalDateTime.now().plusMonths(5),categorias,30);


        Producto   productoGuardado = productoRepo.save(producto);
        System.out.println(productoGuardado.getCodigo());
       Assertions.assertNotNull(productoGuardado);

    }

    @Test
    @Sql("classpath:productos.sql")
    public void eliminarTest() {

        Producto producto = productoRepo.findById(1).orElse(null);
        productoRepo.delete(producto);
        Producto buscado = productoRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);

    }

    @Test
    @Sql("classpath:productos.sql")
    public void actualizarTest() {


        Producto productoGuardado = productoRepo.findById(1).orElse(null);
        productoGuardado.setEstado(Estado_Producto.AUTORIZADO);
        productoRepo.save(productoGuardado);
        Producto buscado = productoRepo.findById(1).orElse(null);
        Assertions.assertEquals(Estado_Producto.AUTORIZADO, buscado.getEstado());


    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarTest() {

        List<Producto> lista = productoRepo.findAll();

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarPorNombre() {

        List<Producto> lista = productoRepo.findAllByNombreContainsIgnoreCase("Xbox");

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarPorDescripcion() {

        List<Producto> lista = productoRepo.findAllByDescripcionContainsIgnoreCase("descripcion");

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarPorUsuarioCedula() {

        List<Producto> lista = productoRepo.findAllByUsuario_Cedula("1004870909");

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarPorCategoria() {

        List<Producto> lista = productoRepo.findAllByCategoriasContains(Categoria_Producto.TECNOLOGIA);

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarPorEstado() {

        List<Producto> lista = productoRepo.findAllByEstado(Estado_Producto.AUTORIZADO);

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarPorDisponibilidad() {

        List<Producto> lista = productoRepo.findAllByIsDisponible(true);

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarPorIsActivo() {

        List<Producto> lista = productoRepo.findAllByIsActivo(true);

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarPorRangoDePrecio() {

        List<Producto> lista = productoRepo.listarPorRangoDePrecio(2000.,6000.);

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarProductosFavoritosUsuario() {

        List<Producto> lista = productoRepo.obtenerProductosDeUsuarioFavoritos("1004870909");

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:productos.sql")
    public void listarProductosActivos() {

        List<Producto> lista = productoRepo.listarProductosActivos();

        lista.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:productos.sql")
    public void listarProductosDisponibles() {

        List<Producto> lista = productoRepo.listarProductosDisponibles();

        lista.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:productos.sql")
    public void listarProductosPorInicial() {

        List<String> lista = productoRepo.listarProductosPorInicial("p" + "%");

        lista.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:productos.sql")
    public void listarProductosPorPrecioMayor() {

        List<Producto> lista = productoRepo.obtenerProductosDeMayorPrecio();

        lista.forEach(System.out::println);
    }@Test
    @Sql("classpath:productos.sql")
    public void listarProductosPorPrecioMenor() {

        List<Producto> lista = productoRepo.obtenerProductosDeMenorPrecio();

        lista.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:productos.sql")
    public void obtenerProductosENVenta() {

        List<Producto> lista = productoRepo.obtenerProductosEnVenta();

        lista.forEach(System.out::println);

    }
}
