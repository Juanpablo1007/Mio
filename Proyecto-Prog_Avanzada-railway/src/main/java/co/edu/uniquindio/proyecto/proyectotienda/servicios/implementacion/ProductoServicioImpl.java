package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.dto.ProductoDTO;
import co.edu.uniquindio.proyecto.proyectotienda.dto.ProductoGetDTO;
import co.edu.uniquindio.proyecto.proyectotienda.modelo.Producto;
import co.edu.uniquindio.proyecto.proyectotienda.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.CloudinaryServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.ProductoServicio;
import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.PublicacionServicio;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private ProductoRepo productoRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
    }

    @Override
    public int crearProducto(ProductoDTO productoDTO) throws Exception {
        Producto producto = convertir(productoDTO);
        return productoRepo.save(producto).getCodigo();
    }

    @Override
    public ProductoGetDTO actualizarProducto(int codigoProducto, ProductoDTO productoDTO) throws Exception {
        validarProducto(codigoProducto);
        Producto producto = convertir(productoDTO);
        producto.setCodigo(codigoProducto);
        return convertir(productoRepo.save(producto));
    }

    @Override
    public int eliminarProducto(int codigoProducto) throws Exception {
        validarProducto(codigoProducto);
        productoRepo.deleteById(codigoProducto);
        return codigoProducto;
    }

    @Override
    public ProductoGetDTO obtenerProductoDTO(int codigoProducto) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigoProducto);
        if (producto.isEmpty()) {
            throw new Exception("El producto con el id " + codigoProducto + " no existe");
        }
        return convertir(producto.get());
    }

    @Override
    public Producto obtenerProducto(int codigoProducto) throws Exception {
        Optional<Producto> producto = productoRepo.findById(codigoProducto);
        if (producto.isEmpty()) {
            throw new Exception("El producto con el id " + codigoProducto + " no existe");
        }
        return producto.get();
    }

    @Override
    public ProductoGetDTO actualizarUnidades(int codigoProducto, int unidadesDescontar) throws Exception {
        Producto producto = obtenerProducto(codigoProducto);
        producto.setUnidadesDisponibles(producto.getUnidadesDisponibles() - unidadesDescontar);
        return convertir(productoRepo.save(producto));
    }


    private Producto convertir(ProductoDTO productoDTO) throws Exception {
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setUnidadesDisponibles(productoDTO.getUnidades());
        producto.setImagen(productoDTO.getImagenes());
        producto.setCategorias(productoDTO.getCategorias());
        return producto;
    }

    private ProductoGetDTO convertir(Producto producto) {
        return new ProductoGetDTO(
                producto.getCodigo(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getUnidadesDisponibles(),
                producto.getPrecio(),
                producto.getImagen(),
                producto.getCategorias()
        );
    }

    private void validarProducto(int codigoProducto) throws Exception {
        boolean existe = productoRepo.existsById(codigoProducto);
        if (!existe) {
            throw new Exception("El producto con el id " + codigoProducto + " no existe");
        }

    }
}
