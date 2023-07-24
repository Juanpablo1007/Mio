import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MensajeDTO } from '../modelo/mensaje-dto';

import { ProductoPost } from '../modelo/producto-post';

import { GestionFavoritosDTO } from '../modelo/gestion-favoritos-dto';
import { ComentarioPost } from '../modelo/comentario-post';
import { map } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ProductoService {
productoURL : string = "http://localhost:8080/api/productos";
  constructor(private http: HttpClient) {

   }
   public publicarProducto(productoPostDTO: ProductoPost): Observable<MensajeDTO> {
    return this.http.post<MensajeDTO>(`${this.productoURL}/publicar`, productoPostDTO);
  }

  public comentarProducto(comentarioPostDTO: ComentarioPost): Observable<MensajeDTO> {
    return this.http.post<MensajeDTO>(`${this.productoURL}/comentar`, comentarioPostDTO);
  }

  public guardarProductoFavorito(gestionFavoritosDTO: GestionFavoritosDTO): Observable<MensajeDTO> {
    return this.http.put<MensajeDTO>(`${this.productoURL}/agregar_favorito`, gestionFavoritosDTO);
  }

  public quitarProductoFavorito(gestionFavoritosDTO: GestionFavoritosDTO): Observable<MensajeDTO> {
    return this.http.put<MensajeDTO>(`${this.productoURL}/eliminar_favorito`, gestionFavoritosDTO);
  }

  

  public listarProductoNombre(nombre: string): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.productoURL}/listar_nombre/${nombre}`);
  }

  public listarProductosPublicadosPorVendedor(cedula: string): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.productoURL}/listar_productos_vendedor/${cedula}`);
  }

  public listarProductosFavoritos(cedula: string): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.productoURL}/listar_productos_favortitos_usuario/${cedula}`);
  }

  public actualizarProducto(codigo: number, productoPostDTO: ProductoPost): Observable<MensajeDTO> {
    return this.http.put<MensajeDTO>(`${this.productoURL}/actualizar/${codigo}`, productoPostDTO);
  }

  public eliminarProducto(codigo: number): Observable<MensajeDTO> {
    return this.http.delete<MensajeDTO>(`${this.productoURL}/eliminar/${codigo}`);
  }

  public obtenerProducto(codigo: number): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.productoURL}/obtener/${codigo}`);
  }

  public listarProductos(): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.productoURL}/listar_todos`);
  }

}
