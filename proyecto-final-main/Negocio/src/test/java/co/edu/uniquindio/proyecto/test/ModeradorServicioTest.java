package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.LocalDateTime.now;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ModeradorServicioTest {

/**
    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    public void registrarModeradoresTest() {
        Moderador moderador = new Moderador();
        moderador.setCedula("1000");
        moderador.setEmail("juanp.delgadod@uqvirtual.edu.co");
        moderador.setContraseña("Juan123");
        moderador.setNombre("juan Pablo");

        Moderador moderador1 = new Moderador();
        moderador1.setCedula("2000");
        moderador1.setEmail("juan.delgadod@uqvirtual.edu.co");
        moderador1.setContraseña("Juan12");
        moderador1.setNombre("juan Felipe");


        try {
            Moderador moderadorP = moderadorServicio.registrarModerador(moderador1);
            Moderador moderadorP1 = moderadorServicio.registrarModerador(moderador);
            Assertions.assertNotNull(moderadorP1);
            Assertions.assertNotNull(moderadorP);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void logModeradorTest() {
        Moderador moderador = new Moderador();
        moderador.setCedula("1000");
        moderador.setEmail("juanp.delgadod@uqvirtual.edu.co");
        moderador.setContraseña("Juan123");
        moderador.setNombre("juan Pablo");

        try {
            moderadorServicio.registrarModerador(moderador);
            Optional<Moderador> u = moderadorServicio.loginMod(moderador.getEmail(), moderador.getContraseña());

            Assertions.assertNotNull(u);


        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);

        }
    }

    @Test
    public void prohibirProducto() {

        Usuario usuario = new Usuario();
        usuario.setCedula("1001017577");
        usuario.setEmail("juanp.delgadod@uqvirtual.edu.co");
        usuario.setContraseña("Juan123");
        usuario.setIsCuentaActiva(true);
        usuario.setTelefono("3218711230");
        usuario.setDireccion("Reserva de la pastorita");
        usuario.setNombre("Juan Pablo");
        Set<Categoria_Producto> categorias = new HashSet<Categoria_Producto>();
        categorias.add(Categoria_Producto.BEBES);
        categorias.add(Categoria_Producto.CONSTRUCCION);
        categorias.add(Categoria_Producto.HERRAMIENTAS);
        Producto producto = new Producto(usuario, true, "imagen", "play 5", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(1);


        try {
            Producto productoPublicado = productoServicio.publicarProducto(producto, usuarioServicio.registrarUsuario(usuario));

            moderadorServicio.prohibirProducto(productoPublicado);

            Assertions.assertNotNull(producto);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AutorizarProducto() {

        Usuario usuario = new Usuario();
        usuario.setCedula("1001017577");
        usuario.setEmail("juanp.delgadod@uqvirtual.edu.co");
        usuario.setContraseña("Juan123");
        usuario.setIsCuentaActiva(true);
        usuario.setTelefono("3218711230");
        usuario.setDireccion("Reserva de la pastorita");
        usuario.setNombre("Juan Pablo");
        Set<Categoria_Producto> categorias = new HashSet<Categoria_Producto>();
        categorias.add(Categoria_Producto.BEBES);
        categorias.add(Categoria_Producto.CONSTRUCCION);
        categorias.add(Categoria_Producto.HERRAMIENTAS);
        Producto producto = new Producto(usuario, true, "imagen", "play 5", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(1);


        try {
            Producto productoPublicado = productoServicio.publicarProducto(producto, usuarioServicio.registrarUsuario(usuario));

            moderadorServicio.AutorizarProducto(productoPublicado);
            System.out.println(productoPublicado);
            Assertions.assertNotNull(producto);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void listarPorudctosOrdenadaEstadoTEST() {
        Usuario usuario = new Usuario();
        usuario.setCedula("1001017577");
        usuario.setEmail("juanp.delgadod@uqvirtual.edu.co");
        usuario.setContraseña("Juan123");
        usuario.setIsCuentaActiva(true);
        usuario.setTelefono("3218711230");
        usuario.setDireccion("Reserva de la pastorita");
        usuario.setNombre("Juan Pablo");
        Set<Categoria_Producto> categorias = new HashSet<Categoria_Producto>();
        categorias.add(Categoria_Producto.BEBES);
        categorias.add(Categoria_Producto.CONSTRUCCION);
        categorias.add(Categoria_Producto.HERRAMIENTAS);
        Producto producto = new Producto(usuario, true, "imagen", "asta", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(1);
        Producto producto2 = new Producto(usuario, true, "imagen", "bebe", "Consola nintendo", 3000.0, true, Estado_Producto.DENEGADO, now().plusMonths(5), categorias, 30);
        producto.setCodigo(2);


        try {
            Usuario u = usuarioServicio.registrarUsuario(usuario);
            productoServicio.publicarProducto(producto, u);
            productoServicio.publicarProducto(producto2, u);

            List<Producto> productosOrdenados = moderadorServicio.listarProductosPorEstado();
            productosOrdenados.forEach(System.out::println);
            Assertions.assertEquals(2,productosOrdenados.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }**/
}




