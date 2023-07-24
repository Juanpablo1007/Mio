import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MensajeDTO } from '../modelo/mensaje-dto';
import { ModeradorPost } from '../modelo/moderador-post';
@Injectable({
  providedIn: 'root'
})
export class ModeradorService {
  private moderadorURL = "http://localhost:8080/api/moderadores";
  constructor(private http: HttpClient) { }

  

  public autorizarProducto(codigo: number): Observable<MensajeDTO> {
    return this.http.put<MensajeDTO>(`${this.moderadorURL}/autorizar/${codigo}`, null);
  }
   

  public prohibirProducto(codigo: number): Observable<MensajeDTO> {
    return this.http.put<MensajeDTO>(`${this.moderadorURL}/prohibir/${codigo}`, null);
  }

  public listarProductosPorEstado(): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.moderadorURL}/productos_estado`);
  }

  

  

}
