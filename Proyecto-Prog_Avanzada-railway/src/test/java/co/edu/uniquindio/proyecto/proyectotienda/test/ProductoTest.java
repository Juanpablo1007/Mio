package co.edu.uniquindio.proyecto.proyectotienda.test;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Categoria;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Producto;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.PublicacionServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Transactional
public class ProductoTest {

    @Autowired
    private ProductoServicio productoServicio;

    @Test
    public void crearProductoTest() {
        try {
            ProductoDTO productoDTO = new ProductoDTO(
                    "Iphone 14",
                    "Nuevo",
                    2,
                    3000000,
                    Arrays.asList("Img1.png", "Img2.png"),
                    Arrays.asList(Categoria.TECNOLOGIA)
            );
            int codigoProducto = productoServicio.crearProducto(productoDTO);
            Assertions.assertNotEquals(0, codigoProducto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarProductoTest() {
        try {
            ProductoGetDTO producto = productoServicio.obtenerProductoDTO(1);
            producto.setNombre("Nuevo producto");
            ProductoDTO modificado = new ProductoDTO(
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getUnidades(),
                    producto.getPrecio(),
                    producto.getImagenes(),
                    producto.getCategorias()
            );
            productoServicio.actualizarProducto(producto.getCodigo(), modificado);
            ProductoGetDTO consulta = productoServicio.obtenerProductoDTO(1);
            Assertions.assertEquals("Nuevo producto", consulta.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarProducto() {
        try {
            productoServicio.eliminarProducto(1);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerProductoTest() {
        try {
            Producto producto = productoServicio.obtenerProducto(1);
            Assertions.assertEquals(2000000, producto.getPrecio());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
