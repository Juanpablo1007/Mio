package co.edu.uniquindio.proyecto.servicios;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.proyecto.dto.EmailGetDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailServicioImple implements EmailServicio {

    private JavaMailSender javaMailSender;

    public EmailServicioImple(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void enviarEmail(EmailGetDTO emailGetDTO) throws Exception {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailGetDTO.getDestinatario());
        message.setSubject(emailGetDTO.getAsunto());
        message.setText(emailGetDTO.getCuerpo());

        javaMailSender.send(message);
    }
}
