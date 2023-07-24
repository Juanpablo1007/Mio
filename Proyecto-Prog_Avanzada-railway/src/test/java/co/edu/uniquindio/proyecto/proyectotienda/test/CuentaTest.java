package co.edu.uniquindio.proyecto.proyectotienda.test;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.Cuenta;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.CuentaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class CuentaTest {

    @Autowired
    private CuentaServicio cuentaServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void buscarCuentaTest() {
        try {
            Cuenta cuenta = cuentaServicio.buscarCuenta(1);
            Assertions.assertEquals("tomas@mail.com", cuenta.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void cambiarContraseniaTest() {
        try {
            cuentaServicio.cambiarContrasenia("tomas@mail.com", "tomasito");
            Cuenta cuenta = cuentaServicio.buscarCuentaEmail("tomas@mail.com");
            Assertions.assertNotEquals("12345", cuenta.getContrasenia());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
