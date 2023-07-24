package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest {



    @Autowired
    private ComentarioRepo comentarioRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private ProductoRepo productoRepo;

    @Test
    @Sql("classpath:comentarios.sql")
    public void registrarTest() {

        Usuario usuario = usuarioRepo.findById("1004870909").orElse(null);

        Producto producto = productoRepo.findById(1).orElse(null);

        Comentario comentario = new Comentario("El producto es mediocre", LocalDateTime.now(), usuario, producto,1.0);

        Comentario comentarioGenerado = comentarioRepo.save(comentario);

        Assertions.assertNotNull(comentarioGenerado);

    }
    @Test
    @Sql("classpath:comentarios.sql")
    public void eliminarTest() {


        Comentario comentarioGenerado = comentarioRepo.findById(1).orElse(null);

        comentarioRepo.delete(comentarioGenerado);

        Comentario busquedaComentario = comentarioRepo.findById(1).orElse(null);

        Assertions.assertNull(busquedaComentario);

    }

    @Test
    @Sql("classpath:comentarios.sql")
    public void actualizarTest() {

        Comentario comentarioGenerado = comentarioRepo.findById(1).orElse(null);
        comentarioGenerado.setTexto("El producto es chistoso");
        comentarioRepo.save(comentarioGenerado);
        Comentario busquedaComentario = comentarioRepo.findById(1).orElse(null);
        Assertions.assertEquals("El producto es chistoso", busquedaComentario.getTexto());

    }
    @Test
    @Sql("classpath:comentarios.sql")
    public void ListarTest() {

        List<Comentario> lista = comentarioRepo.findAll();

        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:comentarios.sql")
    public void buscarPorFechasAnteriores() {

        List<Comentario> lista = comentarioRepo.findAllByFechaBefore(LocalDateTime.now());

        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:comentarios.sql")
    public void buscarPorTexto() {

        List<Comentario> lista = comentarioRepo.findAllByTextoContainsIgnoreCase("Buen");

        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:comentarios.sql")
    public void buscarPorCedulaDeUsuario() {

        List<Comentario> lista = comentarioRepo.findAllByUsuario_Cedula("1004870909");

        lista.forEach(System.out::println);

    }

    @Test
    @Sql("classpath:comentarios.sql")
    public void buscarPorIdDeProducto() {

        List<Comentario> lista = comentarioRepo.findAllByProducto_Codigo(1);

        lista.forEach(System.out::println);
    }
    @Test
    @Sql("classpath:comentarios.sql")
    public void lsitarUsuarioComentariosNoRepetidos() {

        List<Usuario> usuarios = comentarioRepo.findAllByUsuario_Cedula_Sin_Repetir(1);

        usuarios.forEach(System.out::println);
    }




    }









