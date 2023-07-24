package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.TransaccionPostDTO;

public interface TransaccionServicio {

    void realizarTransaccion(TransaccionPostDTO compraPostDTO) throws Exception;

    Double calcularTotalCompra(Integer codigo);

    //List<CompraGetDTO> listarComprasDeUsuario (String usuarioCedula) throws Exception;

}
