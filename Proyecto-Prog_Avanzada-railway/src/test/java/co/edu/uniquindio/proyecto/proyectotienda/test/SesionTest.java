package co.edu.uniquindio.proyecto.proyectotienda.test;

import co.edu.uniquindio.proyecto.proyectotienda.dto.SesionDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.TokenDTO;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.SesionServicio;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SesionTest {

    @Autowired
    private SesionServicio sesionServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void loginTest() {
        try{
            SesionDTO sesion = new SesionDTO("tomas@mail.com", "12345");
            TokenDTO token = sesionServicio.login(sesion);
            Assertions.assertNotEquals("", token.getToken());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
