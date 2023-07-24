package co.edu.uniquindio.proyecto.proyectotienda.test;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.MetodoPago;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.CompraServicio;
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
public class CompraTest {
    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private PublicacionServicio publicacionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearCompraTest() {
        try {

            DetalleCompraDTO detalleCompraDTO = new DetalleCompraDTO(
                    5,
                    1,
                    2
            );
            CompraDTO compraDTO = new CompraDTO(
                    "juanp040223@gmail.com",
                    MetodoPago.EFECTIVO,
                    Arrays.asList(detalleCompraDTO)
            );
            int codigoCompra = compraServicio.crearCompra(compraDTO);
            Assertions.assertNotEquals(0, codigoCompra);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComprasTest() {
        try {
            List<CompraGetDTO> compras = compraServicio.listarCompras(1);
            Assertions.assertNotEquals(Collections.EMPTY_LIST, compras);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerCompraTest() {
        try {
            CompraGetDTO compra = compraServicio.obtenerCompraDTO(1);
            Assertions.assertNotEquals(0, compra.getValorTotal());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void productosCompradosNoRepetirTest() {
        try {
            List<PublicacionGetDTO> comprasNoRepetir = compraServicio.obtenerComprasUsuarioNoRepetir(1);
            Assertions.assertEquals(2, comprasNoRepetir.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
