package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;

public interface SesionServicio {

    TokenDTO loginUsuario(SesionDTO sesionDTO);

    void recuperarContraseña (String email)  throws Exception;

}
