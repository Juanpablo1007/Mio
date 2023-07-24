package co.edu.uniquindio.proyecto.repositorios;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ComentarioRepo extends JpaRepository < Comentario , Integer> {
    List<Comentario> findAllByFechaBefore(LocalDateTime fecha);

    List<Comentario> findAllByTextoContainsIgnoreCase(String texto);


    List<Comentario> findAllByUsuario_Cedula(String cedula);

    List<Comentario> findAllByProducto_Codigo(Integer codigo);

    @Query ("SELECT  c.usuario from Producto p join p.comentario c where p.codigo = :id")
    List<Usuario> findAllByUsuario_Cedula_Sin_Repetir(Integer id);




}

