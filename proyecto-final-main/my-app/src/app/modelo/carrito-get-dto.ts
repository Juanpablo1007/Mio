import { CarritoProductosGetDTO } from "./carrito-productos-get-dto";

export class CarritoGetDTO {
    usuarioCedula : String = "";
    productos : CarritoProductosGetDTO [];
    total: number = 0;

    constructor(usuarioCedula : String, productos : CarritoProductosGetDTO [],  total: number  ) {
       this.usuarioCedula = usuarioCedula;
       this.productos = productos;
       this.total = total;


    }
}
