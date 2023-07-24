package co.edu.uniquindio.proyecto.proyectotienda.test;

import co.edu.uniquindio.proyecto.proyectotienda.dto.PublicacionGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.RevisionDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.EstadoPublicacion;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Moderador;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.PublicacionServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ModeradorTest {

    @Autowired
    private ModeradorServicio moderadorServicio;

    @Autowired
    private PublicacionServicio publicacionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerModeradorTest() {
        try {
            Moderador moderador = moderadorServicio.obtenerModerador(6);
            Assertions.assertEquals("Carlos", moderador.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void revisarPublicacionTest() {
        try {
            PublicacionGetDTO publicacion = publicacionServicio.obtenerPublicacionDTO(4);
            PublicacionGetDTO mod = moderadorServicio.revisarPublicacion(new RevisionDTO(4, 4, EstadoPublicacion.AUTORIZADO));
            Assertions.assertNotEquals(publicacion.getEstado(), mod.getEstado());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
