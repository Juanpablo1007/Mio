export class ComentarioGetDTO {
  texto: String = '';
  fecha: Date = new Date();
  usuarioCedula: string = '';
  productoCodigo: number = 0;
  calificacion: number = 0;
  constructor(
    texto: string,
    fecha: Date,
    usuarioCedula: string,
    productoCodigo: number,
    calificacion: number
  ) {
    this.texto = texto;
    this.fecha = fecha;
    this.usuarioCedula = usuarioCedula;
    this.productoCodigo = productoCodigo;
    this.calificacion = calificacion;
  }
}
