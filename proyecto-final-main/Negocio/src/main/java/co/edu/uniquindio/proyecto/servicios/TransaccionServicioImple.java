package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.TransaccionGetDTO;
import co.edu.uniquindio.proyecto.dto.TransaccionPostDTO;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Transaccion;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServicioImple implements TransaccionServicio {

    private final UsuarioRepo usuarioRepo;
    private final ProductoRepo productoRepo;
    private final TransaccionRepo transaccionRepo;


    public TransaccionServicioImple(UsuarioRepo usuarioRepo, ProductoRepo productoRepo, TransaccionRepo transaccionRepo) {
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
        this.transaccionRepo = transaccionRepo;
    }

    @Override
    public void realizarTransaccion(TransaccionPostDTO transaccionPostDTO) throws Exception {

        Optional<Usuario> usuarioComprador = usuarioRepo.findById(transaccionPostDTO.getUsuarioCompradorCedula());
        Optional<Usuario> usuarioVendedor = usuarioRepo.findById(transaccionPostDTO.getUsuarioVendedorCedula());
        Optional<Producto> producto = productoRepo.findById(transaccionPostDTO.getProductoCodigo());
        if (usuarioComprador.isEmpty()) {
            throw new Exception("El cliente no existe");
        }
        if (usuarioVendedor.isEmpty()) {
            throw new Exception("El Vendedor no existe");
        }
        if (producto.isEmpty()) {
            throw new Exception("El producto no existe");
        }
        if (!usuarioComprador.get().getIsCuentaActiva()) {
            throw new Exception("El cliente no puede comprar producto si su cuenta no esta activa");
        }
        if (producto.get().getUnidades() < transaccionPostDTO.getUnidadesCompradas()) {
            throw new Exception("No hay suficientes unidades");
        }
        Transaccion transaccion = new Transaccion(LocalDateTime.now(),0.0,usuarioComprador.get(),usuarioVendedor.get(),transaccionPostDTO.getMetodoDePago(),producto.get(),transaccionPostDTO.getUnidadesCompradas());
        Transaccion transaccionGuardada = transaccionRepo.save(transaccion);
        Double total = calcularTotalCompra(transaccionGuardada.getCodigo());
        transaccionGuardada.setTotal(total);
        Transaccion transaccionGuardadaTotal = transaccionRepo.save(transaccionGuardada);
        List<Transaccion> comprasUsuarioComprador = transaccionRepo.findAllByUsuarioCompra_Cedula(usuarioComprador.get().getCedula());
        List<Transaccion> ventasUsuarioVendedor = transaccionRepo.findAllByUsuarioVenta_Cedula(usuarioVendedor.get().getCedula());
        List<Transaccion> comprasProducto = transaccionRepo.findAllByProducto_Codigo(producto.get().getCodigo());

        usuarioComprador.get().setCompras(comprasUsuarioComprador);
        usuarioRepo.save(usuarioComprador.get());

        usuarioVendedor.get().setVentas(ventasUsuarioVendedor);
        usuarioRepo.save(usuarioVendedor.get());


        producto.get().setCompras(comprasProducto);
        producto.get().setUnidades(producto.get().getUnidades() - transaccionPostDTO.getUnidadesCompradas());
        productoRepo.save(producto.get());

    }


    @Override
    public Double calcularTotalCompra(Integer codigo) {
        return transaccionRepo.calcularTotalCompras(codigo);
    }



}
