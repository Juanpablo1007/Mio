import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MensajeDTO } from '../modelo/mensaje-dto';
import { TransaccionPost } from '../modelo/transaccion-post';
@Injectable({
  providedIn: 'root'
})
export class TransaccionService {
  transaccionURL : string = "http://localhost:8080/api/transacciones";

  constructor(private http: HttpClient) { }

  public realizarTransaccion(transaccionPostDTO: TransaccionPost): Observable<MensajeDTO> {
    return this.http.post<MensajeDTO>(`${this.transaccionURL}/realizarTransaccion`, transaccionPostDTO);
  }
}
