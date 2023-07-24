package co.edu.uniquindio.proyecto.proyectotienda.test;

import co.edu.uniquindio.proyecto.proyectotienda.dto.UsuarioDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.UsuarioGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Usuario;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@SpringBootTest
@Transactional
//Se ejecutan varias cosas en la base de datos, varias consultas, si llega a fallar rolback si falla se vuelve a dejar como estaba
public class UsuarioTest {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void crearUsuarioTest() throws Exception {
        try {
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    "Mateo",
                    "Baez",
                    "mateoBaez@gmail.com",
                    "12345",
                    "Calle 24 # 2",
                    "3193687143"
            );

            int codigoUsuario = usuarioServicio.crearUsuario(usuarioDTO);

            //Se espera que si se registra correctamente entonces el servicio no debe retornar 0
            Assertions.assertNotEquals(0, codigoUsuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarUsuarioTest() throws Exception {
        try {
            usuarioServicio.eliminarUsuario(1);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void actualizarUsuarioTest() throws Exception {
        try {
            //Obtenemos un registro de la base de datos y le cambiamos por ejemplo el nombre
            UsuarioGetDTO usuario = usuarioServicio.obtenerUsuarioDTO(1);
            usuario.setNombre("Felipe");
            //Se convierte el objeto (debe crear esta función en ClienteConverter e inyectarlo acá
            UsuarioDTO modificado = new UsuarioDTO(
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getEmail(),
                    usuario.getContrasenia(),
                    usuario.getDireccion(),
                    usuario.getTelefono()
            );
            usuarioServicio.actualizarUsuario(usuario.getCodigoUsuario(), modificado);
            //Obtenemos el usuario con el código 1 y verificamos que si haya sido modificado
            UsuarioGetDTO consulta = usuarioServicio.obtenerUsuarioDTO(1);
            Assertions.assertEquals("Felipe", consulta.getNombre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void obtenerUsuarioTest() throws Exception {
        try {
            UsuarioGetDTO usuario = usuarioServicio.obtenerUsuarioDTO(1);
            Assertions.assertEquals("Mz 4 Casa 1", usuario.getDireccion());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void marcarFavoritoTest() {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario(1);
            int cantFavoritos = usuario.getFavoritos().size();
            usuarioServicio.marcarFavorito(1, 3);
            Assertions.assertNotEquals(cantFavoritos, usuario.getFavoritos().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void eliminarFavoritoTest() {
        try {
            Usuario usuario = usuarioServicio.obtenerUsuario(1);
            int cantFavoritos = usuario.getFavoritos().size();
            usuarioServicio.eliminarFavorito(1, 2);
            Assertions.assertNotEquals(cantFavoritos, usuario.getFavoritos().size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
