package co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces;

import co.edu.uniquindio.proyecto.proyectotienda.dto.SesionDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.TokenDTO;

public interface SesionServicio {

    TokenDTO login(SesionDTO sesionDTO) throws Exception;
   // void logout(int codigoUsuario) throws Exception;
}
