import { ComentarioPost } from "./comentario-post";

export class ProductoGetDTO {
  codigo: number;
  usuarioCedula: string;
  usuarioNombre: string;
  numeroFavorito: number;
  isActivo: boolean;
  UrlImagen: string;
  nombre: string;
  descripcion: string;
  precio: number;
  unidades: number;
  estado_producto: string[];
  fechaLimite: Date;
  categoria: string[];
  isDisponible: boolean;
  comentarios: ComentarioPost[];

  constructor(
    codigo: number,
    usuarioCedula: string,
    usuarioNombre: string,
    numeroFavorito: number,
    isActivo: boolean,
    UrlImagen: string,
    nombre: string,
    descripcion: string,
    precio: number,
    unidades: number,
    estado_producto: string[],
    fechaLimite: Date,
    categoria: string[],
    isDisponible: boolean,
    comentarios: ComentarioPost[]
  ) {
    this.codigo = codigo;
    this.usuarioCedula = usuarioCedula;
    this.usuarioNombre = usuarioNombre;
    this.numeroFavorito = numeroFavorito;
    this.isActivo = isActivo;
    this.UrlImagen = UrlImagen;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.unidades = unidades;
    this.estado_producto = estado_producto;
    this.fechaLimite = fechaLimite;
    this.categoria = categoria;
    this.isDisponible = isDisponible;
    this.comentarios = comentarios;
  }
}
