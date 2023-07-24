import { ProductoGetDTO } from './producto-get-dto';

export class TransaccionGetDTO {
  fecha: Date = new Date();
  total: number = 0;
  usuarioCompradorCedula: string = '';
  usuarioVendedorCedula: string = '';
  metodoDePago: String = "";
  producto: ProductoGetDTO;
  unidadesCompradas: number = 0;

  constructor(
    fecha: Date,
    total: number ,
    usuarioCompradorCedula: string,
    usuarioVendedorCedula: string,
    metodoDePago: string,
    producto: ProductoGetDTO,
    unidadesCompradas: number,
  ) {
    this.fecha = fecha;
    this.total = total;
    this.usuarioCompradorCedula = usuarioCompradorCedula;
    this.usuarioVendedorCedula = usuarioVendedorCedula;
    this.metodoDePago = metodoDePago;
    this.producto = producto;
    this.unidadesCompradas = unidadesCompradas;
  }
}
