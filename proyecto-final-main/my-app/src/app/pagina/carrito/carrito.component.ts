import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { MensajeDTO } from 'src/app/modelo/mensaje-dto';
import { CarritoProductosDTO } from 'src/app/modelo/carrito-productos-dto';
import { EliminarProductoCarritoDTO } from 'src/app/modelo/eliminar-producto-carrito-dto';
import { CarritoService } from 'src/app/servicios/carrito.service';

@Component({
  selector: 'app-carrito',
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent {
  constructor(private carritoService: CarritoService) {}

  public eliminarProducto(eliminarProductoCarritoDTO: EliminarProductoCarritoDTO): Observable<MensajeDTO> {
    return this.carritoService.eliminarProducto(eliminarProductoCarritoDTO);
  }

  
}

