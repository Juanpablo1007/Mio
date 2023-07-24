package co.edu.uniquindio.proyecto.proyectotienda.seguridad.servicios;

import co.edu.uniquindio.proyecto.proyectotienda.modelo.Moderador;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Usuario;
import co.edu.uniquindio.proyecto.proyectotienda.repositorios.ModeradorRepo;
import co.edu.uniquindio.proyecto.proyectotienda.repositorios.UsuarioRepo;
import co.edu.uniquindio.proyecto.proyectotienda.seguridad.modelo.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UsuarioRepo clienteRepo;
    @Autowired
    private ModeradorRepo adminRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario cliente = clienteRepo.buscarUsuarioCorreo(email);
        if(cliente == null){
            Moderador admin = adminRepo.buscarModerador(email);
            if(admin == null)
                throw new UsernameNotFoundException("El usuario no existe");
            return UserDetailsImpl.build(admin);
        }else{
            return UserDetailsImpl.build(cliente);
        }
    }
}