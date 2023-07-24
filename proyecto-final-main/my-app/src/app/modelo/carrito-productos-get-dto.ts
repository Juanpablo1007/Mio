import { ProductoGetDTO } from "./producto-get-dto";

export class CarritoProductosGetDTO {
    
    productoGetDTO:ProductoGetDTO = new ProductoGetDTO;

    unidades: number = 0;
    
    constructor(productoGetDTO: ProductoGetDTO, unidades: number ) {
      this.productoGetDTO = productoGetDTO;
      this.unidades = unidades;
    }

}
