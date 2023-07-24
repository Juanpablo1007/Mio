import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EliminarProductoCarritoDTO } from '../modelo/eliminar-producto-carrito-dto';
import { CarritoProductosDTO } from '../modelo/carrito-productos-dto';
import { MensajeDTO } from '../modelo/mensaje-dto';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private carritoURL = "http://localhost:8080/api/carritos";
  constructor(private http: HttpClient) { }
  
  public eliminarProducto(eliminarProductoCarritoDTO: EliminarProductoCarritoDTO): Observable<MensajeDTO> {
    return this.http.delete<MensajeDTO>(`${this.carritoURL}/eliminar_producto`, { body: eliminarProductoCarritoDTO });
  }
  

  public agregarProducto(carritoProductosPostDTO: CarritoProductosDTO): Observable<MensajeDTO> {
    return this.http.post<MensajeDTO>(`${this.carritoURL}/agregar_producto`, carritoProductosPostDTO);
  }
}
