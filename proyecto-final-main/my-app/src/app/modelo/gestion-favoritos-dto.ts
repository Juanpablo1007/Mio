export class GestionFavoritosDTO {

    usuarioCedula:string = "";

    productoCodigo: number = 0;

    constructor(usuarioCedula: string = '', productoCodigo: number = 0) {
        this.usuarioCedula = usuarioCedula;
        this.productoCodigo = productoCodigo;
      }
}
