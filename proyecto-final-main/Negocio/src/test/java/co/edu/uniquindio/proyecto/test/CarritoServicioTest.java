package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.dto.CarritoGetDTO;
import co.edu.uniquindio.proyecto.dto.CarritoProductosPostDTO;
import co.edu.uniquindio.proyecto.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CarritoServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static java.time.LocalDateTime.now;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class CarritoServicioTest {/**

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CarritoServicio carritoServicio;

    @Test
    public void asignarCarritoAUsuario() throws Exception {
        Usuario usuario = new Usuario("1001017577","Juan123","Juan Pablo","juanp.delgadod@uqvirutal.edu.co",true,"3218711230","Reserva de la pastorita");
        Carrito carrito = new Carrito();

        try{
            Usuario usuarioCreado = usuarioServicio.registrarUsuario(usuario);
            CarritoGetDTO carritoAsignado = carritoServicio.asignarCarrito(usuarioCreado.getCedula());
            System.out.println(usuarioCreado);
            System.out.println(carritoAsignado);

            Assertions.assertNotNull(carritoAsignado);
        }
        catch (Exception e) {
        e.printStackTrace();
            Assertions.assertNotNull(null);

        }

    }

    @Test
    public void agregarProductoACarrito() throws Exception {
        Usuario usuario = new Usuario("1001017577","Juan123","Juan Pablo","juanp.delgadod@uqvirutal.edu.co",true,"3218711230","Reserva de la pastorita");
        Carrito carrito = new Carrito(usuario);
        Set<Categoria_Producto> categorias = new HashSet<Categoria_Producto>();
        categorias.add(Categoria_Producto.BEBES);
        categorias.add(Categoria_Producto.CONSTRUCCION);
        Producto producto = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(0);

        try{
            Usuario usuarioCreado = usuarioServicio.registrarUsuario(usuario);
            Producto productoCreado = productoServicio.publicarProducto(producto,usuarioCreado);
            CarritoGetDTO carritoAsignado = carritoServicio.asignarCarrito(usuarioCreado.getCedula());
            CarritoProductosPostDTO carritoProductosPostDTO = new CarritoProductosPostDTO(carritoAsignado.getCodigo(),productoCreado.getCodigo(),5);
            CarritoGetDTO infoProducto = carritoServicio.agregarProducto(carritoProductosPostDTO);


            System.out.println(usuarioCreado);
            System.out.println(productoCreado);
            System.out.println(carritoAsignado);
            System.out.println(infoProducto);

            Assertions.assertNotNull(infoProducto);
        }
        catch (Exception e) {
            e.printStackTrace();
            Assertions.assertNotNull(null);

        }


    }
    @Test
    public void eliminarProductoDeCarrito() throws Exception {
        Usuario usuario = new Usuario("1001017577", "Juan123", "Juan Pablo", "juanp.delgadod@uqvirutal.edu.co", true, "3218711230", "Reserva de la pastorita");
        Carrito carrito = new Carrito(usuario);
        Set<Categoria_Producto> categorias = new HashSet<Categoria_Producto>();
        categorias.add(Categoria_Producto.BEBES);
        categorias.add(Categoria_Producto.CONSTRUCCION);
        Producto producto = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(0);

        try {
            Usuario usuarioCreado = usuarioServicio.registrarUsuario(usuario);
            UsuarioGetDTO usuarioGetDTO = new UsuarioGetDTO();
            usuarioGetDTO.setCedula(usuarioCreado.getCedula());
            Producto productoCreado = productoServicio.publicarProducto(producto,usuarioCreado);
            CarritoGetDTO carritoAsignado = carritoServicio.asignarCarrito(usuarioCreado.getCedula());
            CarritoProductosPostDTO carritoProductosPostDTO = new CarritoProductosPostDTO(carritoAsignado.getCodigo(),productoCreado.getCodigo(),5);
            CarritoGetDTO infoProducto = carritoServicio.agregarProducto(carritoProductosPostDTO);

            CarritoGetDTO productoEliminado = carritoServicio.eliminarProducto(infoProducto.getCodigo(),productoCreado.getCodigo());

            System.out.println(usuarioCreado);
            System.out.println(productoCreado.getCarritos());
            System.out.println(carritoAsignado.getProductos());
            System.out.println(infoProducto);
            System.out.println(productoEliminado);

           Assertions.assertEquals(1,productoEliminado.getProductos().size());
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);

        }
    }

    @Test
    public void calcularTotal() throws Exception {
       Usuario usuario = new Usuario("1001017577", "Juan123", "Juan Pablo", "juanp.delgadod@uqvirutal.edu.co", true, "3218711230", "Reserva de la pastorita");
        Carrito carrito = new Carrito(usuario);
        Set<Categoria_Producto> categorias = new HashSet<Categoria_Producto>();
        categorias.add(Categoria_Producto.BEBES);
        categorias.add(Categoria_Producto.CONSTRUCCION);
        Producto producto = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 3000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto.setCodigo(0);
        Producto producto2 = new Producto(usuario, true, "imagen", "Nintendo", "Consola nintendo", 5000.0, true, Estado_Producto.SIN_REVISAR, now().plusMonths(5), categorias, 30);
        producto2.setCodigo(0);

        try {
            Usuario usuarioCreado = usuarioServicio.registrarUsuario(usuario);
            UsuarioGetDTO usuarioGetDTO = new UsuarioGetDTO();
            usuarioGetDTO.setCedula(usuarioCreado.getCedula());
            Producto productoCreado = productoServicio.publicarProducto(producto,usuarioCreado);
            Producto producto2Creado = productoServicio.publicarProducto(producto2,usuarioCreado);
            CarritoGetDTO carritoAsignado = carritoServicio.asignarCarrito(usuarioCreado.getCedula());
            CarritoProductosPostDTO carritoProductosPostDTO = new CarritoProductosPostDTO(carritoAsignado.getCodigo(),productoCreado.getCodigo(),5);
            CarritoProductosPostDTO carritoProductosPostDTO2 = new CarritoProductosPostDTO(carritoAsignado.getCodigo(),producto2Creado.getCodigo(),7);
            CarritoGetDTO infoProducto = carritoServicio.agregarProducto(carritoProductosPostDTO);
            CarritoGetDTO infoProducto2 = carritoServicio.agregarProducto(carritoProductosPostDTO2);

            Double total = carritoServicio.calcularTotalCarrito(carritoAsignado.getCodigo());

            Assertions.assertEquals(50000.0,total);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);

        }
    }**/
}
