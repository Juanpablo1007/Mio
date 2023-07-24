package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.time.LocalDateTime.now;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ProductoServicioTest {
/**
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    public void subirProductoTest() {
        Usuario usuario = new Usuario("1001017577","Juan123","Juan Pablo","juanp.delgadod@uqvirutal.edu.co",true,"3218711230","Reserva de la pastorita");
        Set<Categoria_Producto> categorias = new HashSet<Categoria_Producto>();
        categorias.add(Categoria_Producto.BEBES);
        categorias.add(Categoria_Producto.CONSTRUCCION);
        Producto producto = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 3000.0, true, Estado_Producto.AUTORIZADO, now().plusMonths(5), categorias, 30);
        producto.setCodigo(0);
        Carrito carrito = new Carrito(usuario);
        try {
            Usuario u = usuarioServicio.registrarUsuario(usuario);
            Producto p = productoServicio.publicarProducto(producto,u);
            System.out.println(u.getProductos());
            Assertions.assertEquals(1,u.getProductos().size());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertNotNull(null);
        }
    }

    @Test
    public void subirComentarioTest() {
        Usuario usuario = new Usuario("1001017577","Juan123","Juan Pablo","juanp.delgadod@uqvirutal.edu.co",true,"3218711230","Reserva de la pastorita");
        Set<Categoria_Producto> categorias = new HashSet<Categoria_Producto>();
        categorias.add(Categoria_Producto.BEBES);
        categorias.add(Categoria_Producto.CONSTRUCCION);
        Producto producto = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(0);


        try {
            Usuario u = usuarioServicio.registrarUsuario(usuario);
            Producto p = productoServicio.publicarProducto(producto,u);

            Comentario comentario = new Comentario();
            comentario.setTexto("buen producto");
            comentario.setProducto(p);
            comentario.setUsuario(u);
            comentario.setFecha(now());
            comentario.setCodigo(0);


            Producto comentado = productoServicio.comentarProducto(p,comentario,u);
            System.out.println(comentado);System.out.println(u);

        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertNotNull(null);
        }
    }

    @Test
    public void listarProductoPreciosTest() {
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
        Producto producto = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(1);


        Producto producto2 = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 5000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto2.setCodigo(2);
        try {
            usuarioServicio.registrarUsuario(usuario);
            productoServicio.publicarProducto(producto,usuario);
            productoServicio.publicarProducto(producto2,usuario);
            List<Producto> lista = productoServicio.listarProductoPrecio(7000.0, 3000.0);
            lista.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listarProductoTest() {
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


        Producto producto2 = new Producto(usuario, true, "imagen", "play 4", "Consola nintendo", 5000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto2.setCodigo(2);
        try {
            usuarioServicio.registrarUsuario(usuario);
            productoServicio.publicarProducto(producto,usuario);
            productoServicio.publicarProducto(producto2, usuario);
            List<Producto> lista = productoServicio.listarProductosPublicados(usuario.getCedula());
            lista.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void listarProductoNombreTest() {
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


        Producto producto2 = new Producto(usuario, true, "imagen", "play 4", "Consola nintendo", 5000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto2.setCodigo(2);
        try {
            usuarioServicio.registrarUsuario(usuario);
            productoServicio.publicarProducto(producto,usuario);
            productoServicio.publicarProducto(producto2,usuario);
            List<Producto> lista = productoServicio.buscarProductoNombre("play");
            lista.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test

    public void agregarProductoFavorito() {
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
            Usuario u = usuarioServicio.registrarUsuario(usuario);
            Producto p =  productoServicio.publicarProducto(producto,u);
            productoServicio.guardarProductoFavorito(p,u);
            productoServicio.guardarProductoFavorito(p,u);
            List<Producto> lista = productoServicio.listarProductosFavoritos(u);
            lista.forEach(System.out::println);
            System.out.println(u);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test

    public void listarProductoFavorito() {
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
            Usuario u = usuarioServicio.registrarUsuario(usuario);
            Producto p =   productoServicio.publicarProducto(producto,u);
            productoServicio.guardarProductoFavorito(p,u);
            List<Producto> lista = productoServicio.listarProductosFavoritos(u);

            if(lista.isEmpty()){
                System.out.println("ESTA VACIA");
            }
            lista.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void actualizarProductoTest() {
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
        Producto producto = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(0);
        try {
            usuarioServicio.registrarUsuario(usuario);
            Producto p = productoServicio.publicarProducto(producto,usuario);
            System.out.println( "Anterior" + p);
            producto.setNombre("play 3");
            producto.setDescripcion("ahora es un play 3");
            Producto p2 = productoServicio.ActualizarProducto(producto);
            System.out.println("Actualizado" + p2  );
            Assertions.assertNotNull(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    } @Test
    public void EliminarProductoTest() {
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
        Producto producto = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(0);

        try {
            Usuario usuario1 = usuarioServicio.registrarUsuario(usuario);
            Producto producto1 = productoServicio.publicarProducto(producto,usuario1);
            productoServicio.EliminarProducto(producto1);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);

        }
    }**/

}
