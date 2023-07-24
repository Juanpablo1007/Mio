package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;


import co.edu.uniquindio.proyecto.seguridad.modelo.UserDetailsImpl;
import co.edu.uniquindio.proyecto.seguridad.servicios.JwtService;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImple implements UsuarioServicio {

    private final UsuarioRepo usuarioRepo;
    private final ProductoRepo productoRepo;
    private final CarritoRepo carritoRepo;
    private final ComentarioRepo comentarioRepo;
    private final TransaccionRepo transaccionRepo;
    private final Mapeador mapeador;
    private final EmailServicio emailServicio;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UsuarioServicioImple(UsuarioRepo usuarioRepo, ProductoRepo productoRepo, CarritoRepo carritoRepo, ComentarioRepo comentarioRepo, TransaccionRepo transaccionRepo, Mapeador mapeador, EmailServicio emailServicio, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
        this.carritoRepo = carritoRepo;
        this.comentarioRepo = comentarioRepo;
        this.transaccionRepo = transaccionRepo;
        this.mapeador = mapeador;
        this.emailServicio = emailServicio;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void registrarUsuario(UsuarioPostDTO usuarioPostDTO) throws Exception {
        Optional<Usuario> buscadoCedula = usuarioRepo.findById(usuarioPostDTO.getCedula());
        if (buscadoCedula.isPresent()) {
            throw new Exception("Ya existe un usuario registrado con esa cedula");
        }
        Optional<Usuario> buscadoCorreo = usuarioRepo.findByEmailIgnoreCase(usuarioPostDTO.getEmail());

        if (buscadoCorreo.isPresent()) {
            throw new Exception("Ya existe un usuario registrado con ese email");
        }

        Usuario usuario = new Usuario(usuarioPostDTO.getCedula(),passwordEncoder.encode(usuarioPostDTO.getContraseña()),usuarioPostDTO.getNombre(),usuarioPostDTO.getEmail(),usuarioPostDTO.getIsCuentaActiva(),usuarioPostDTO.getTelefono(),usuarioPostDTO.getDireccion());

        usuarioRepo.save(usuario);

    }

    @Override
    public void actualizarUsuario(UsuarioPostDTO usuarioPostDTO) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(usuarioPostDTO.getCedula());
        if (buscado.isEmpty()) {
            throw new Exception("No existe un usuario registrado con esa cedula");
        }
        Usuario usuario = buscado.get();
        Optional<Usuario> buscadoCorreo = usuarioRepo.findByEmailIgnoreCase(usuarioPostDTO.getEmail());
        if (buscadoCorreo.isPresent() && !buscadoCorreo.get().equals(usuario)) {
            throw new Exception("Ya existe un usuario diferente registrado con ese email");
        }
        usuario.setContraseña(passwordEncoder.encode(usuarioPostDTO.getContraseña()));
        usuario.setNombre(usuarioPostDTO.getNombre());
        usuario.setEmail(usuarioPostDTO.getEmail());
        usuario.setIsCuentaActiva(usuarioPostDTO.getIsCuentaActiva());
        usuario.setTelefono(usuarioPostDTO.getTelefono());
        usuario.setDireccion(usuarioPostDTO.getDireccion());

        usuarioRepo.save(usuario);


    }

    @Override
    public void eliminarUsuario(String cedula) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(cedula);
        if (buscado.isPresent()) {
            usuarioRepo.deleteById(cedula);
        } else {
            throw new Exception("No existe un usuario registrado con esa cedula");
        }


    }

    @Override
    public UsuarioGetDTO buscarUsuario(String cedula) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findById(cedula);
        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario registrado con esa cedula");
        }
        return mapeador.usuarioADTO(usuario.get());
    }

    @Override
    public List<UsuarioGetDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepo.findAll();
        List<UsuarioGetDTO> usuarioGetDTOS = new ArrayList<>();
        for (Usuario usuario: usuarios
        ) {
            UsuarioGetDTO usuarioGetDTO = mapeador.usuarioADTO(usuario);
            usuarioGetDTOS.add(usuarioGetDTO);
        }

        return usuarioGetDTOS;
    }

    @Override
    public UsuarioGetDTO ObtenerUsuario(String correo) throws Exception {
        Optional<Usuario> usuario = usuarioRepo.findByEmailIgnoreCase(correo);
        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario registrado con esa cedula");
        }
        return mapeador.usuarioADTO(usuario.get());
    }
    }




