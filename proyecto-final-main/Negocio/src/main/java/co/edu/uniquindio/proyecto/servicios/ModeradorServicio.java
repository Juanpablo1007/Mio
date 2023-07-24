package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.dto.*;

import java.util.List;

public interface ModeradorServicio {

   void registrarModerador (ModeradorPostDTO moderadorPostDTO) ;
   void prohibirProducto (Integer productoCodigo) throws Exception;

    void autorizarProducto (Integer productoCodigo) throws Exception;

    List<ProductoGetDTO> listarProductosPorEstado();

    ModeradorGetDTO obtenerModeradorPorCedula(String cedula) throws Exception;

    List<ModeradorGetDTO> listarModeradores();

}
