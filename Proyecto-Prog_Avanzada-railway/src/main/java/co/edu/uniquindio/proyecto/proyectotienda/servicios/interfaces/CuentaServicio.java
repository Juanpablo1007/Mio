package co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces;

import co.edu.uniquindio.proyecto.proyectotienda.dto.CuentaGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Cuenta;

public interface CuentaServicio {
    Cuenta buscarCuenta(int codigoCuenta) throws Exception;

    Cuenta buscarCuentaEmail(String email) throws Exception;

    CuentaGetDTO buscarCuentaEmailDTO(String email) throws Exception;

    void existeEmail(String email) throws Exception;

    void recuperarContrasenia(String email) throws Exception;

    int cambiarContrasenia(String email, String nuevaContrasenia) throws Exception;
}
