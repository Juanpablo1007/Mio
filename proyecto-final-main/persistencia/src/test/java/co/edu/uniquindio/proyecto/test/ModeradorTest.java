package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ModeradorTest {
    @Autowired
    private ModeradorRepo moderadorRepo;

    @Test
    public void registrarTest() {
        Moderador moderador = new Moderador("1004870908","contraseña Nueva","Moderador nuevo","moderadornuevo@gmail.com");
        Moderador moderadorGuardado = moderadorRepo.save(moderador);
        Assertions.assertNotNull(moderadorGuardado);

    }

    @Test
    @Sql("classpath:moderadores.sql")
    public void eliminarTest() {

        Moderador moderadorGuardado = moderadorRepo.findById("1004870909").orElse(null);
        moderadorRepo.delete(moderadorGuardado);
        Moderador buscado = moderadorRepo.findById("1004870909").orElse(null);
        Assertions.assertNull(buscado);

    }

    @Test
    @Sql("classpath:moderadores.sql")
    public void actualizarTest() {

        Moderador moderadorGuardado = moderadorRepo.findById("1004870909").orElse(null);
        moderadorGuardado.setNombre("Didier");
        moderadorRepo.save(moderadorGuardado);
        Moderador buscado = moderadorRepo.findById("1004870909").orElse(null);
        Assertions.assertEquals("Didier", buscado.getNombre());


    }

    @Test
    @Sql("classpath:moderadores.sql")
    public void listarTest() {

        List<Moderador> lista = moderadorRepo.findAll();

        lista.forEach(System.out::println);
    }

    @Test
    @Sql("classpath:moderadores.sql")
    public void filtrarNombreTest() {

        List<Moderador> lista = moderadorRepo.findAllByNombreContainsIgnoreCase("juan");
        lista.forEach(System.out::println);

    }




    @Test
    @Sql("classpath:moderadores.sql")
    public void verificarAutenticacionTest() {

        Optional<Moderador> moderador = moderadorRepo.findByEmailAndContraseña("correo3@gmail.com", "contraseña3");
        if (moderador.isPresent()) {
            System.out.println(moderador.get());
        } else {
            System.err.println("El email o contraseña no coninciden");
        }

    }

    @Test
    @Sql("classpath:moderadores.sql")
    public void paginarListaTest() {

        Pageable paginador = PageRequest.of(0, 2); // El archivo usuario.sql los crea en orden de ID (cedula) es decir, la cedula "menor" va primero
        Page<Moderador> lista = moderadorRepo.findAll(paginador);

        System.out.println(lista.stream().collect(Collectors.toList())); // hace la lista en partes, dependiendo de los pagueRequest
    }
    @Test
    @Sql("classpath:moderadores.sql")
    public void ordenarListaTest() {

        List<Moderador> lista = moderadorRepo.findAll(Sort.by("nombre")); //ordena por orden alfabetico

        lista.forEach(System.out::println);
    }

}
