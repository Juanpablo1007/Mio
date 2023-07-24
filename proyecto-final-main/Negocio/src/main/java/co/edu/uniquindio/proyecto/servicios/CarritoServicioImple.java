package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.*;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoServicioImple implements CarritoServicio{

    private final UsuarioRepo usuarioRepo;
    private final ProductoRepo productoRepo;
    private final CarritoRepo carritoRepo;
    private final CarritoProductosRepo carritoProductosRepo;
    private final Mapeador mapeador;

    public CarritoServicioImple(UsuarioRepo usuarioRepo, ProductoRepo productoRepo, CarritoRepo carritoRepo, CarritoProductosRepo carritoProductosRepo, Mapeador mapeador) {
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
        this.carritoRepo = carritoRepo;
        this.carritoProductosRepo = carritoProductosRepo;
        this.mapeador=mapeador;
    }
    @Override
    public void asignarCarrito(String usuarioCedula) throws Exception {

        Optional<Usuario> usuario = usuarioRepo.findById(usuarioCedula);

        if(usuario.isEmpty()){
            throw new Exception("No existe un usuario con esa id");
        }

        Carrito carrito = new Carrito(usuario.get());
        Carrito carritoGuardado = carritoRepo.save(carrito);
        usuario.get().setCarrito(carritoGuardado);
        usuarioRepo.save(usuario.get());


    }

    @Override
    public void agregarProducto(CarritoProductosPostDTO carritoProductosPostDTO) throws Exception {

        Optional<Carrito> carrito = carritoRepo.findByUsuario_Cedula(carritoProductosPostDTO.getUsuarioCedulaCarrito());
        Optional<Producto> producto= productoRepo.findById(carritoProductosPostDTO.getProductoCodigo());

        if(carrito.isEmpty()){
            throw new Exception("No existe un carrito asignado a ese usuario");
        }

        if(producto.isEmpty()){
            throw new Exception("No existe un producto con ese codigo");
        }

        if(carritoProductosPostDTO.getUnidades() > producto.get().getUnidades()){
            throw new Exception("Sin stock");
        }

        CarritoProductosLlave idCarritoProductos = new CarritoProductosLlave(carrito.get().getCodigo(), producto.get().getCodigo());
        Optional<CarritoProductos> infoCarritoProductoEncontrado = carritoProductosRepo.findById(idCarritoProductos);

        CarritoProductos productoInfoBuscado = null;

        if(infoCarritoProductoEncontrado.isPresent()){
            Integer unidadesAnteriores= infoCarritoProductoEncontrado.get().getUnidades();
            infoCarritoProductoEncontrado.get().setUnidades(carritoProductosPostDTO.getUnidades()+unidadesAnteriores);
            productoInfoBuscado = carritoProductosRepo.save(infoCarritoProductoEncontrado.get());
            carrito.get().getProductos().remove(productoInfoBuscado);
            producto.get().getCarritos().remove(productoInfoBuscado);
        }else{
            CarritoProductos carritoProductos = new CarritoProductos(carrito.get(),producto.get(), carritoProductosPostDTO.getUnidades());
             productoInfoBuscado = carritoProductosRepo.save(carritoProductos);
        }

        if(carrito.get().getProductos() == null) {
            List<CarritoProductos> productos = new ArrayList<CarritoProductos>();
            carrito.get().setProductos(productos);
        }
        if(producto.get().getCarritos() == null) {
            List<CarritoProductos> carritos = new ArrayList<CarritoProductos>();
            producto.get().setCarritos(carritos);
        }
        carrito.get().getProductos().add(productoInfoBuscado);
        producto.get().getCarritos().add(productoInfoBuscado);

        Carrito carritoFinal = carritoRepo.save(carrito.get());
        productoRepo.save(producto.get());


    }

    @Override
    public void eliminarProducto(EliminarProductoCarritoDTO eliminarProductoCarritoDTO) throws Exception {

        Optional<Carrito> carritoEncontrado = carritoRepo.findByUsuario_Cedula(eliminarProductoCarritoDTO.getUsuarioCedulaCarrito());
        Optional<Producto> productoEncontrado = productoRepo.findById(eliminarProductoCarritoDTO.getProductoCodigo());

        if(carritoEncontrado.isEmpty()){
            throw new Exception("No existe un carrito asignado a ese usuario");
        }
        if (productoEncontrado.isEmpty()){
            throw new Exception("El producto con esa id no existe");
        }

        Carrito carrito = carritoEncontrado.get();
        Producto producto= productoEncontrado.get();

        CarritoProductosLlave idCarritoProductos = new CarritoProductosLlave(carrito.getCodigo(),producto.getCodigo());

        Optional<CarritoProductos> carritoProductos = carritoProductosRepo.findById(idCarritoProductos);

        if(carritoProductos.isEmpty()){
            throw new Exception("El producto con esa id no se encuentra en el carrito con esa id");
        }

        carrito.getProductos().remove(carritoProductos.get());
        producto.getCarritos().remove(carritoProductos.get());
        carritoRepo.save(carrito);
        productoRepo.save(producto);
        carritoProductosRepo.deleteById(idCarritoProductos);

    }

    @Override
    public Double calcularTotalCarrito(String usuarioCedulaCarrito) {

        return carritoProductosRepo.calcularTotalCarrito(usuarioCedulaCarrito);
    }

    @Override
    public CarritoGetDTO obtenerCarrito(String usuarioCedulaCarrito) throws Exception {

        Optional<Carrito> carritoEncontrado = carritoRepo.findByUsuario_Cedula(usuarioCedulaCarrito);
        if(carritoEncontrado.isEmpty()){
            throw new Exception("No existe un carrito asignado a ese usuario");
        }
        Carrito carrito =carritoEncontrado.get();

        return mapeador.carritoACarritoGetDTO(carrito);
    }

    @Override
    public List<CarritoGetDTO> obtenerCarritos() {
        List<CarritoGetDTO> carritoGetDTOS = new ArrayList<>();

        List<Carrito> carritosEntidad = carritoRepo.findAll();

        for (Carrito carrito: carritosEntidad
             ) {
            CarritoGetDTO carritoGetDTO = mapeador.carritoACarritoGetDTO(carrito);
            carritoGetDTOS.add(carritoGetDTO);
        }

        return carritoGetDTOS;
    }

}
