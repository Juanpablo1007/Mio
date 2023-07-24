package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.EmailGetDTO;
import co.edu.uniquindio.proyecto.dto.SesionDTO;
import co.edu.uniquindio.proyecto.dto.TokenDTO;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.seguridad.modelo.UserDetailsImpl;
import co.edu.uniquindio.proyecto.seguridad.servicios.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class SesionServicioImple implements SesionServicio {

    private final EmailServicio emailServicio;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final UsuarioRepo usuarioRepo;

    private final ModeradorRepo moderadorRepo;

    public SesionServicioImple(EmailServicio emailServicio, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UsuarioRepo usuarioRepo, ModeradorRepo moderadorRepo) {
        this.emailServicio = emailServicio;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.usuarioRepo = usuarioRepo;
        this.moderadorRepo = moderadorRepo;
    }

    @Override
    public TokenDTO loginUsuario(SesionDTO sesionDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        sesionDTO.getEmail(),
                        sesionDTO.getContraseña())
        );
        UserDetails user = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);
        return new TokenDTO(jwtToken);
    }

    @Override
    public void recuperarContraseña(String email) throws Exception {

        Optional<Usuario> buscadoCorreo = usuarioRepo.findByEmailIgnoreCase(email);
        Optional<Moderador> buscadoModCorreo = moderadorRepo.findByEmailIgnoreCase(email);

        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        Integer tamaño = 10;

        StringBuilder sb = new StringBuilder(tamaño);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < tamaño; i++) {
            int randomIndex = random.nextInt(caracteres.length());
            sb.append(caracteres.charAt(randomIndex));
        }

        String contraseña = sb.toString();

        if(buscadoCorreo.isPresent()){

            buscadoCorreo.get().setContraseña(passwordEncoder.encode(contraseña));
            usuarioRepo.save(buscadoCorreo.get());
            EmailGetDTO emailGetDTO = new EmailGetDTO("Recuperacion de contraseña","La contraseña de su cuenta es: "+contraseña+" por favor cambiarla cuando pueda",email);
            emailServicio.enviarEmail(emailGetDTO);
        }else if(buscadoModCorreo.isPresent()){

            buscadoModCorreo.get().setContraseña(passwordEncoder.encode(contraseña));
            moderadorRepo.save(buscadoModCorreo.get());
            EmailGetDTO emailGetDTO = new EmailGetDTO("Recuperacion de contraseña","La contraseña de su cuenta es: "+contraseña+" por favor cambiarla cuando pueda",email);
            emailServicio.enviarEmail(emailGetDTO);
        }else{
            throw new Exception("No existe un usuario registrado con ese email");
        }

    }
}
