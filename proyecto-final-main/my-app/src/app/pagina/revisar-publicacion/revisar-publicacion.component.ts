import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { MensajeDTO } from 'src/app/modelo/mensaje-dto';
import { ModeradorService } from 'src/app/servicios/moderador.service';

@Component({
  selector: 'app-revisar-publicacion',
  templateUrl: './revisar-publicacion.component.html',
  styleUrls: ['./revisar-publicacion.component.css']
})
export class RevisarPublicacionComponent {
  constructor(private moderadorService: ModeradorService) {}

  public autorizarProducto(codigo: number): Observable<MensajeDTO> {
    return this.moderadorService.autorizarProducto(codigo);
  }

  public prohibirProducto(codigo: number): Observable<MensajeDTO> {
    return this.moderadorService.prohibirProducto(codigo);
  }

  public listarProductosPorEstado(): Observable<MensajeDTO> {
    return this.moderadorService.listarProductosPorEstado();
  }
}

