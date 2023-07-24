package co.edu.uniquindio.proyecto.dto;

import co.edu.uniquindio.proyecto.entidades.MetodoDePago;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransaccionGetDTO {

    private LocalDateTime fecha;
    private Double total;
    private String usuarioCompradorCedula;
    private String usuarioVendedorCedula;
    private MetodoDePago metodoDePago;
    private ProductoGetDTO producto;
    private Integer unidadesCompradas;


}
