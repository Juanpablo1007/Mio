package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.dto.DetalleCompraDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.EmailDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Compra;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.DetalleCompra;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Producto;
import co.edu.uniquindio.proyecto.proyectotienda.repositorios.DetalleCompraRepo;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DetalleCompraServicioImpl implements DetalleCompraServicio {

    private final DetalleCompraRepo detalleCompraRepo;

    private final PublicacionServicio publicacionServicio;

    private final ProductoServicio productoServicio;

    private EmailServicio emailServicio;

    private CuentaServicio cuentaServicio;

    public DetalleCompraServicioImpl(DetalleCompraRepo detalleCompraRepo, PublicacionServicio publicacionServicio, ProductoServicio productoServicio, EmailServicio emailServicio, CuentaServicio cuentaServicio) {
        this.detalleCompraRepo = detalleCompraRepo;
        this.publicacionServicio = publicacionServicio;
        this.productoServicio = productoServicio;
        this.emailServicio = emailServicio;
        this.cuentaServicio = cuentaServicio;
    }

    @Override
    @Transactional
    public int crearDetalleCompra(DetalleCompraDTO detalleCompraDTO, Compra compra) throws Exception {
        DetalleCompra detalleCompra = convertir(detalleCompraDTO);
        detalleCompra.setCompra(compra);
        productoServicio.actualizarUnidades(publicacionServicio.obtenerProductoPublicacion(detalleCompraDTO.getCodigoPublicacion()).getCodigo(), detalleCompra.getUnidades());

        String cuerpoCorreo = "<h1>¡Has vendido un producto!</h1>";
        cuerpoCorreo += "<p>Detalles de la venta:</p>";
        cuerpoCorreo += "<ul>";
        cuerpoCorreo += "<li>Producto: " + detalleCompraDTO.getCodigoPublicacion() + "</li>";
        cuerpoCorreo += "<li>Unidades: " + detalleCompraDTO.getUnidades() + "</li>";
        cuerpoCorreo += "<li>Precio por unidad: $" + detalleCompraDTO.getPrecioUnidad() + "</li>";
        cuerpoCorreo += "</ul>";


        emailServicio.enviarEmail(new EmailDTO("Has vendido un producto.", cuerpoCorreo, cuentaServicio.buscarCuenta(publicacionServicio.obtenerPublicacionDTO(detalleCompraDTO.getCodigoPublicacion()).getCodigoCuenta()).getEmail()));
        return detalleCompraRepo.save(detalleCompra).getCodigo();
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleCompra buscarDetalleCompra(int codigoDetalleCompra) throws Exception {
        Optional<DetalleCompra> detalleCompra = detalleCompraRepo.findById(codigoDetalleCompra);
        if (detalleCompra.isEmpty()) {
            throw new Exception("El detalle compra con el id " + codigoDetalleCompra + " no existe");
        }
        return detalleCompra.get();
    }

    @Override
    @Transactional(readOnly = true)
    public DetalleCompraDTO buscarDetalleCompraDTO(int codigoDetalleCompra) throws Exception {
        Optional<DetalleCompra> detalleCompra = detalleCompraRepo.findById(codigoDetalleCompra);
        if (detalleCompra.isEmpty()) {
            throw new Exception("El detalle compra con el id " + codigoDetalleCompra + " no existe");
        }
        return convertir(detalleCompra.get());
    }

    private DetalleCompra convertir(DetalleCompraDTO detalleCompraDTO) throws Exception {
        DetalleCompra detalleCompra = new DetalleCompra();
        detalleCompra.setUnidades(detalleCompraDTO.getUnidades());
        detalleCompra.setPrecioUnidad(detalleCompraDTO.getPrecioUnidad());
        detalleCompra.setPublicacion(publicacionServicio.obtenerPublicacion(detalleCompraDTO.getCodigoPublicacion()));
        return detalleCompra;
    }

    private DetalleCompraDTO convertir(DetalleCompra detalleCompra) {
        return new DetalleCompraDTO(
                detalleCompra.getPublicacion().getCodigo(),
                detalleCompra.getUnidades(),
                detalleCompra.getPrecioUnidad()
        );
    }
}
