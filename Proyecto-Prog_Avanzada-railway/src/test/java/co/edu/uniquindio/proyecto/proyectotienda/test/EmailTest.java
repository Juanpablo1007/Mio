package co.edu.uniquindio.proyecto.proyectotienda.test;

import co.edu.uniquindio.proyecto.proyectotienda.dto.EmailDTO;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.EmailServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class EmailTest {

    @Autowired
    private EmailServicio emailServicio;

    @Test
    public void enviarCorreoTest() throws Exception{
        emailServicio.enviarEmail(new EmailDTO("Prueba de envio", "Este es un mensaje", "juanp040223@gmail.com"));
    }
}
