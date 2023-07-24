import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsuarioPost } from '../modelo/usuario-post';
import { MensajeDTO } from '../modelo/mensaje-dto';
import { Observable } from 'rxjs';
import { SesionDTO } from '../modelo/sesion-dto';
@Injectable({
providedIn: 'root'
})
export class AuthService {
private authURL = "http://localhost:8080/api/auth";
constructor(private http:HttpClient) { 


}

public registrar(usuario:UsuarioPost):Observable<MensajeDTO>{
  return this.http.post<MensajeDTO>(`${this.authURL}/crear_usuario`, usuario);
  }
  public login(loginUser: SesionDTO):Observable<MensajeDTO>{
  return this.http.post<MensajeDTO>(`${this.authURL}/login`, loginUser);
  }

  public recuperarCuenta(email: string): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.authURL}/recuperar_contraseña?=${email}`);

    
  }

  public obtenerProducto(codigo: number): Observable<MensajeDTO> {
    return this.http.get<MensajeDTO>(`${this.authURL}/obtener_producto?=${codigo}`);

    
  }

  public obtenerProductos(): Observable<MensajeDTO> {
    const url = `${this.authURL}/obtener_productos`;
    return this.http.get<MensajeDTO>(url);
  }


  
}
