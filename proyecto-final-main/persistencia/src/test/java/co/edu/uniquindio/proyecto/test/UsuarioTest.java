package co.edu.uniquindio.proyecto.test;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import co.edu.uniquindio.proyecto.entidades.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;


import javax.crypto.spec.PSource;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Test
    public void registrarTest() {
        Usuario usuario = new Usuario();
        usuario.setCedula("1001017577");
        usuario.setEmail("juanp.delgadod@uqvirtual.edu.co");
        usuario.setContraseña("Juan123");
        usuario.setIsCuentaActiva(true);
        usuario.setTelefono("3218711230");
        usuario.setDireccion("Reserva de la pastorita");
        usuario.setNombre("Juan Pablo");

        Usuario usuarioGuardado = usuarioRepo.save(usuario);
        Assertions.assertNotNull(usuarioGuardado);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void eliminarTest() {

        Usuario usuarioGuardado = usuarioRepo.findById("1004870909").orElse(null);
        usuarioRepo.delete(usuarioGuardado);
        Usuario buscado = usuarioRepo.findById("1004870909").orElse(null);
        Assertions.assertNull(buscado);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void actualizarTest() {

        Usuario usuarioGuardado = usuarioRepo.findById("1004870909").orElse(null);
        usuarioGuardado.setNombre("Didier");
        usuarioRepo.save(usuarioGuardado);
        Usuario buscado = usuarioRepo.findById("1004870909").orElse(null);
        Assertions.assertEquals("Didier", buscado.getNombre());


    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarTest() {

        List<Usuario> lista = usuarioRepo.findAll();

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void filtrarnNombreTest() {

        List<Usuario> lista = usuarioRepo.findAllByNombreContainsIgnoreCase("juan");
        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void filtrarCorreoTest() {

        Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase("correo1@gmail.com");
        if (usuario.isPresent()) {
            System.out.println(usuario.get());
        } else {
            System.err.println("Ese correo no exite");
        }

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void verificarAutenticacionTest() {

        Optional<Usuario> usuario = usuarioRepo.findByEmailAndContraseña("correo3@gmail.com", "contraseña3");
        if (usuario.isPresent()) {
            System.out.println(usuario.get());
        } else {
            System.err.println("El email o contraseña no coninciden");
        }

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void paginarListaTest() {

        Pageable paginador = PageRequest.of(0, 2); // El archivo usuario.sql los crea en orden de ID (cedula) es decir, la cedula "menor" va primero
        Page<Usuario> lista = usuarioRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList())); // hace la lista en partes, dependiendo de los pagueRequest
    }
    @Test
    @Sql("classpath:usuarios.sql")
    public void ordenarListaTest() {

        List<Usuario> lista = usuarioRepo.findAll(Sort.by("nombre")); //ordena por orden alfabetico

        lista.forEach(System.out::println);
    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void encontrarUsuariosActivos() {

        List<Usuario> lista = usuarioRepo.findAllByIsCuentaActiva(true);
        lista.forEach(System.out::println);
    }





}
