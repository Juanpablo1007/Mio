package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.dto.CuentaGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.EmailDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Cuenta;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Moderador;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Usuario;
import co.edu.uniquindio.proyecto.proyectotienda.repositorios.CuentaRepo;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.CuentaServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.EmailServicio;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaServicioImpl implements CuentaServicio {

    private CuentaRepo cuentaRepo;

    private PasswordEncoder passwordEncoder;

    private EmailServicio emailServicio;

    public CuentaServicioImpl(CuentaRepo cuentaRepo, PasswordEncoder passwordEncoder, EmailServicio emailServicio){
        this.cuentaRepo = cuentaRepo;
        this.passwordEncoder = passwordEncoder;
        this.emailServicio = emailServicio;
    }

    @Override
    public Cuenta buscarCuenta(int codigoCuenta) throws Exception {
        Optional<Cuenta> cuenta = cuentaRepo.findById(codigoCuenta);
        if(cuenta.isEmpty()){
            throw new Exception("La cuenta con el id " + codigoCuenta + " no existe");
        }
        return cuenta.get();
    }

    @Override
    public Cuenta buscarCuentaEmail(String email) throws Exception {
        Cuenta cuenta = cuentaRepo.findByEmail(email);
        if (cuenta == null) {
            throw new Exception("No hay ninguna cuenta con el email " + email);
        }
        return cuenta;
    }

    @Override
    public CuentaGetDTO buscarCuentaEmailDTO(String email) throws Exception {
        return convertir(buscarCuentaEmail(email));
    }

    @Override
    public void existeEmail(String email) throws Exception {
        Cuenta cuenta = cuentaRepo.findByEmail(email);
        if (cuenta != null) {
            throw new Exception("El email ya se encuentra en uso.");
        }
    }

    @Override
    public void recuperarContrasenia(String email) throws Exception {
        validar(email);
        String cuerpo = "Para completar tu cambio de contraseña ingresa en este enlace: https://app-frontend-proyecto-uq-8ca60.web.app/cambiar-contra/" + email +
                "<br/>Si no has sido tu ignora este mensaje.";
        emailServicio.enviarEmail(new EmailDTO("Recuperación contraseña", cuerpo, email));
    }

    @Override
    public int cambiarContrasenia(String email, String nuevaContrasenia) throws Exception {
        Cuenta cuenta = buscarCuentaEmail(email);
        cuenta.setContrasenia(passwordEncoder.encode(nuevaContrasenia));
        return cuentaRepo.save(cuenta).getCodigo();
    }

    private CuentaGetDTO convertir(Cuenta cuenta) {
        String rol = "";
        if(cuenta instanceof Usuario){
            rol = "Cliente";
        } else if(cuenta instanceof Moderador){
            rol = "Moderador";
        }
        return new CuentaGetDTO(
                cuenta.getCodigo(),
                rol,
                cuenta.getEstado(),
                cuenta.getEmail(),
                cuenta.getContrasenia()
        );
    }

    private void validar(String email) throws Exception{
        Cuenta cuenta = cuentaRepo.findByEmail(email);
        if(cuenta == null) {
            throw new Exception("No existe una cuenta con el email: " + email);
        }
    }
}



