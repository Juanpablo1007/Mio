package co.edu.uniquindio.proyecto.proyectotienda.test;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Categoria;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.EstadoPublicacion;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Producto;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Publicacion;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.PublicacionServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Transactional
public class PublicacionTest {

    @Autowired
    private PublicacionServicio publicacionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearPublicacionTest() {
        try {
            ProductoDTO productoDTO = new ProductoDTO(
                    "Papas",
                    "Papas de limon",
                    10,
                    2000,
                    Arrays.asList("img1.png", "img2.png"),
                    Arrays.asList(Categoria.MERCADO)
            );
            PublicacionDTO publicacionDTO = new PublicacionDTO(
                    1,
                    0,
                    productoDTO
            );
            int codigoPublicacion = publicacionServicio.crearPublicacion(publicacionDTO);
            Assertions.assertNotEquals(0, codigoPublicacion);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarPublicacionTest() {
        try{
            PublicacionGetDTO publicacion = publicacionServicio.obtenerPublicacionDTO(4);
            publicacion.setDescuento(40);
            PublicacionDTO modificado = new PublicacionDTO(
                    publicacion.getCodigoCuenta(),
                    publicacion.getDescuento(),
                    new ProductoDTO(
                            "Camara",
                            "Usado",
                            3,
                            150000,
                            Arrays.asList("img.png"),
                            Arrays.asList(Categoria.TECNOLOGIA)
                    )
            );
            publicacionServicio.actualizarPublicacion(publicacion.getCodigo(), modificado);
            PublicacionGetDTO consulta = publicacionServicio.obtenerPublicacionDTO(4);
            Assertions.assertEquals(40, consulta.getDescuento());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPublicacionTest() {
        try{
            publicacionServicio.eliminarPublicacion(1);
            Assertions.assertTrue(true);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPublicacionTest() {
        try {
            Publicacion publicacion = publicacionServicio.obtenerPublicacion(2);
            Assertions.assertEquals(0, publicacion.getDescuento());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerPublicacionUsuarioTest() {
        try {
            List<PublicacionGetDTO> publicaciones = publicacionServicio.obtenerPublicacionUsuario(1);
            Assertions.assertNotEquals(Collections.EMPTY_LIST, publicaciones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarPublicacionVencidaTest() {
        try {
            List<PublicacionGetDTO> publicaciones = publicacionServicio.eliminarPublicacionVencida(LocalDateTime.now().plusMonths(5));
            Assertions.assertTrue(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPublicacionEstadoTest() {
        try {
            List<PublicacionGetDTO> publicaciones = publicacionServicio.listarPublicacionEstado(EstadoPublicacion.AUTORIZADO);
            Assertions.assertNotEquals(Collections.EMPTY_LIST, publicaciones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPublicacionCategoriaTest() {
        try {
            List<PublicacionGetDTO> publicaciones = publicacionServicio.listarPublicacionCategoria(Categoria.TECNOLOGIA);
            Assertions.assertNotEquals(Collections.EMPTY_LIST, publicaciones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPublicacionFavoritosTest() {
        try {
            List<PublicacionGetDTO> favoritos = publicacionServicio.listarPublicacionFavoritos(1);
            Assertions.assertNotEquals(Collections.EMPTY_LIST, favoritos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarPublicacionNombreTest() {
        try {
            List<PublicacionGetDTO> publicaciones = publicacionServicio.listarPublicacionNombre("Camara");
            Assertions.assertNotEquals(Collections.EMPTY_LIST, publicaciones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void listarPublicacionPrecioTest() {
        try {
            List<PublicacionGetDTO> publicaciones = publicacionServicio.listarPublicacionPrecio(1000000, 5000000);
            Assertions.assertNotEquals(Collections.EMPTY_LIST, publicaciones);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cantidadVisitasTest() {
        try {
            int visitas = publicacionServicio.cantidadVisitas(2);
            Assertions.assertEquals(3, visitas);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void caroTest() {
        try {
            List<PublicacionGetDTO> visitas = publicacionServicio.caroBaratoCategoria(Categoria.TECNOLOGIA);
            Assertions.assertEquals(2, visitas.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
