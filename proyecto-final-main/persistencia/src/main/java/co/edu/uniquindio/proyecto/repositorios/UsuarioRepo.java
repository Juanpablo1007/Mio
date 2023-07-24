package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository

public interface UsuarioRepo extends JpaRepository <Usuario, String> {
 List <Usuario> findAllByNombreContainsIgnoreCase(String nombre);

    Optional <Usuario> findByEmailIgnoreCase(String email);

    Optional<Usuario> findByEmailAndContraseña (String email, String contraseña);
    Page <Usuario>findAll(Pageable paginador);

    List <Usuario> findAllByIsCuentaActiva(Boolean isActiva);

    @Query ("select u.productosFavoritos from Usuario u where u.cedula =  :cedula")
    List<Producto> buscarFavoritos (String cedula);

   @Query ("select p.usuariosFavoritos from Producto p where p.codigo =  :codigo")
    List<Usuario> listaUsuariosFavoritosProductoCodigo (Integer codigo);
}
