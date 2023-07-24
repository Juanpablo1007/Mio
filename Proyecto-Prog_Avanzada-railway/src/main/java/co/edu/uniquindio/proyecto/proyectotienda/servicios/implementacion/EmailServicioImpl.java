package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.dto.EmailDTO;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.EmailServicio;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServicioImpl implements EmailServicio {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean enviarEmail(EmailDTO emailDTO) throws Exception {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);

        try {
            helper.setSubject(emailDTO.getAsunto());
            helper.setText(emailDTO.getCuerpo(), true);
            helper.setTo(emailDTO.getDestinatario());
            helper.setFrom("no-reply@unimarket.com");

            javaMailSender.send(mensaje);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
}
