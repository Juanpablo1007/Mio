package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModeradorRepo extends JpaRepository <Moderador, String> {
    List <Moderador> findAllByNombreContainsIgnoreCase(String nombre) ;

    Optional <Moderador> findByEmailIgnoreCase(String email);

    Optional<Moderador> findByEmailAndContraseña (String email, String contraseña);
    Page <Moderador>findAll(Pageable paginador);

}
