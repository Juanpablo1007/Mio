package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.EmailGetDTO;

public interface EmailServicio {
    void enviarEmail(EmailGetDTO emailGetDTO) throws Exception;
}
