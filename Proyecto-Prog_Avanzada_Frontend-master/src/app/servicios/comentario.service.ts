import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MensajeDTO } from '../modelo/mensaje-dto';
import { ComentarioDTO } from '../modelo/comentario-dto';

@Injectable({
  providedIn: 'root'
})
export class ComentarioService {

  private comentarioUrl = "http://localhost:8080/api/comentario"

  constructor(private http: HttpClient) { }

  public publicarComentario(comentario: ComentarioDTO): Observable<MensajeDTO> {
    return this.http.post<MensajeDTO>(`${this.comentarioUrl}/crear`, comentario);
  }
}
