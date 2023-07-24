package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.dto.SesionDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.TokenDTO;
import co.edu.uniquindio.proyecto.proyectotienda.seguridad.modelo.UserDetailsImpl;
import co.edu.uniquindio.proyecto.proyectotienda.seguridad.servicios.JwtService;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.SesionServicio;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SesionServicioImpl implements SesionServicio {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public SesionServicioImpl(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenDTO login(SesionDTO sesionDTO) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                sesionDTO.getEmail(),
                sesionDTO.getContrasenia()
        ));

        UserDetails user = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);

        return new TokenDTO(jwtToken);
    }
}
