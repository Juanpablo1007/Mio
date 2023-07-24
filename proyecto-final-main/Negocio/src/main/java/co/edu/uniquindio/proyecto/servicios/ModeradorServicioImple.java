package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import co.edu.uniquindio.proyecto.seguridad.modelo.UserDetailsImpl;
import co.edu.uniquindio.proyecto.seguridad.servicios.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.proyecto.entidades.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModeradorServicioImple implements ModeradorServicio {


    private final ModeradorRepo moderadorRepo;
    private final ProductoRepo productoRepo;
    private final ProductoServicio productoServicio;
    private final Mapeador mapeador;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public ModeradorServicioImple(ModeradorRepo moderadorRepo, ProductoRepo productoRepo, ProductoServicio productoServicio, Mapeador mapeador, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.moderadorRepo = moderadorRepo;
        this.productoRepo = productoRepo;
        this.productoServicio = productoServicio;
        this.mapeador = mapeador;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void registrarModerador(ModeradorPostDTO moderadorPostDTO) {

        Moderador moderador = new Moderador(moderadorPostDTO.getCedula(),passwordEncoder.encode(moderadorPostDTO.getContraseña()),moderadorPostDTO.getNombre(),moderadorPostDTO.getEmail());
        moderadorRepo.save(moderador);

    }


    @Override

    public void prohibirProducto(Integer productoCodigo) throws Exception {
        Optional<Producto> p = productoRepo.findById(productoCodigo);

        if (!p.isPresent()) {
            throw new Exception("Producto no esta registrado");
        }
        p.get().setEstado(Estado_Producto.DENEGADO);
        productoRepo.save(p.get());

    }


    @Override
    public void autorizarProducto(Integer productoCodigo) throws Exception {

        Optional<Producto> p = productoRepo.findById(productoCodigo);

        if (!p.isPresent()) {
            throw new Exception("Producto no esta registrado");
        }
        p.get().setEstado(Estado_Producto.AUTORIZADO);
        productoRepo.save(p.get());
    }

    @Override
    public List<ProductoGetDTO> listarProductosPorEstado() {

        List<Producto> productos = productoRepo.productosOrdenadosPorEstado();

        List<ProductoGetDTO> productoGetDTOS = new ArrayList<>();
        for (Producto producto: productos
             ) {
            ProductoGetDTO productoGetDTO = mapeador.productoAProductoGetDTO(producto);
            productoGetDTOS.add(productoGetDTO);

        }

        return productoGetDTOS;
    }

    @Override
    public ModeradorGetDTO obtenerModeradorPorCedula(String cedula) throws Exception {
        Optional<Moderador> moderador = moderadorRepo.findById(cedula);
        if(moderador.isEmpty()){
            throw new Exception("No existe un moderador con esa cedula");
        }
        return mapeador.moderadorAModeradorGetDTO(moderador.get());
    }

    @Override
    public List<ModeradorGetDTO> listarModeradores() {

        List<Moderador> moderadores = moderadorRepo.findAll();

        List<ModeradorGetDTO> moderadorGetDTOS = new ArrayList<>();
        for (Moderador moderador: moderadores
        ) {
            ModeradorGetDTO moderadorGetDTO = mapeador.moderadorAModeradorGetDTO(moderador);
            moderadorGetDTOS.add(moderadorGetDTO);

        }
        return moderadorGetDTOS;
    }


}

