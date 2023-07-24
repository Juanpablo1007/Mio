package co.edu.uniquindio.proyecto.proyectotienda.test;

import co.edu.uniquindio.proyecto.proyectotienda.dto.*;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.PublicacionServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@SpringBootTest
@Transactional
public class ComentarioTest {

    @Autowired
    private ComentarioServicio comentarioServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearComentarioTest() {
        try {
            ComentarioDTO comentarioDTO = new ComentarioDTO(
                    "Buen producto, excelente calidad :)",
                    1,
                    4
            );

            int codigoComentario = comentarioServicio.crearComentario(comentarioDTO);
            Assertions.assertNotEquals(0, codigoComentario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void listarComentariosPublicacionTest() {
        try {
            List<ComentarioGetDTO> comentarios = comentarioServicio.listarComentariosPublicacion(1);
            Assertions.assertNotEquals(Collections.EMPTY_LIST, comentarios);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
