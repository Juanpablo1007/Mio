import { CarritoGetDTO } from "./carrito-get-dto";
import { ComentarioGetDTO } from "./comentario-get-dto";
import { ProductoGetDTO } from "./producto-get-dto";
import { TransaccionGetDTO } from "./transaccion-get-dto";

export class UsuarioGetDTO {
cedula: string = "";
nombre: string = "";
email: string = "";
productos: ProductoGetDTO[] = [];
productosFavoritos: ProductoGetDTO[] = [];
carrito: CarritoGetDTO ;
isCuentaActiva: boolean = true;
comentarios: ComentarioGetDTO[] = [];
compras: TransaccionGetDTO[] = [];
ventas: TransaccionGetDTO[] = [];
telefono: string = "";
direccion: string = "";


constructor(
    carrito: CarritoGetDTO,
    cedula: string,
    nombre: string,
    email: string,
    productos: ProductoGetDTO[],
    productosFavoritos: ProductoGetDTO[],
    isCuentaActiva: boolean,
    comentarios: ComentarioGetDTO[] = [],
    compras: TransaccionGetDTO[] = [],
    ventas: TransaccionGetDTO[] = [],
    telefono: string = "",
    direccion: string = ""
  ) {
    this.carrito = carrito;
    this.cedula = cedula;
    this.nombre = nombre;
    this.email = email;
    this.productos = productos;
    this.productosFavoritos = productosFavoritos;
    this.isCuentaActiva = isCuentaActiva;
    this.comentarios = comentarios;
    this.compras = compras;
    this.ventas = ventas;
    this.telefono = telefono;
    this.direccion = direccion;
  }
}
