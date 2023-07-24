package co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces;

import co.edu.uniquindio.proyecto.proyectotienda.dto.EmailDTO;

public interface EmailServicio {

    boolean enviarEmail(EmailDTO emailDTO) throws Exception;
}
