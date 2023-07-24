import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MensajeDTO } from '../modelo/mensaje-dto';
import { UsuarioPost } from '../modelo/usuario-post';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private userUrl = "http://localhost:8080/api/usuarios";

  constructor(private http: HttpClient) { }

  public actualizarUsuario(usuario: UsuarioPost): Observable<MensajeDTO> {
    return this.http.put<MensajeDTO>(`${this.userUrl}/actualizar`, usuario);
  }

  public eliminarUsuario(cedula: string): Observable<MensajeDTO> {
    return this.http.delete<MensajeDTO>(`${this.userUrl}/eliminar/${cedula}`);
  }

  public obtenerUsuario(cedula: string): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.userUrl}/obtener/${cedula}`);
  }

  public listarUsuarios(): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.userUrl}/listar`);
  }

  public obtenerUsuariosEmail(correo: string): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.userUrl}/obtener/${correo}`);
  }
}

