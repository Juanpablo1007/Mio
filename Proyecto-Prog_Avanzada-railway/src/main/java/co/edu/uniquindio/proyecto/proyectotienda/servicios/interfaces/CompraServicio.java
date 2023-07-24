package co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces;

import co.edu.uniquindio.proyecto.proyectotienda.dto.CompraDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.CompraGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.PublicacionGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Compra;

import java.util.List;

public interface CompraServicio {

   int crearCompra(CompraDTO compraDTO) throws Exception;

   List<CompraGetDTO> listarCompras(int codigoUsuario) throws Exception;

   Compra obtenerCompra(int codigoCompra) throws Exception;

   CompraGetDTO obtenerCompraDTO(int codigoCompra) throws Exception;

   List<PublicacionGetDTO> obtenerComprasUsuarioNoRepetir(int codigoCuenta) throws Exception;
}
