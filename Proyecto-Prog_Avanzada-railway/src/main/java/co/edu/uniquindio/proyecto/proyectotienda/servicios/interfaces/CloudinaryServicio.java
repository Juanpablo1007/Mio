package co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces;

import co.edu.uniquindio.proyecto.proyectotienda.dto.ImagenDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Map;

public interface CloudinaryServicio {

    Map subirImagen(File file, String carpeta) throws Exception;

    Map eliminarImagen(String url) throws Exception;

    File convertir(MultipartFile imagen) throws Exception;
}
