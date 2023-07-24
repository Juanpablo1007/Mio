import { Component } from '@angular/core';
import { Alerta } from 'src/app/modelo/alerta';
import { GestionFavoritosDTO } from 'src/app/modelo/gestion-favoritos-dto';
import { MensajeDTO } from 'src/app/modelo/mensaje-dto';
import { ProductoService } from 'src/app/servicios/producto.service';

@Component({
  selector: 'app-producto-favoritos',
  templateUrl: './producto-favoritos.component.html',
  styleUrls: ['./producto-favoritos.component.css']
})
export class ProductoFavoritosComponent {
  gestionFavoritos: GestionFavoritosDTO;
  alerta!: Alerta;
  productosFavoritos: any[];

  constructor(private productoService: ProductoService) {
    this.gestionFavoritos = new GestionFavoritosDTO();
    this.productosFavoritos = [];
  }

  ngOnInit() {
    this.listarProductosFavoritos();
  }

  public quitarProductoFavorito() {
    const objeto = this;
    this.productoService.quitarProductoFavorito(this.gestionFavoritos).subscribe({
      next: (data: MensajeDTO) => {
        objeto.alerta = new Alerta(data.respuesta, 'success');
        // Actualizar la lista de productos favoritos
        this.listarProductosFavoritos();
      },
      error: (error) => {
        objeto.alerta = new Alerta(error.error.respuesta, 'danger');
      },
    });
  }

  private listarProductosFavoritos() {
    const cedula = ''; // Ingresa la cédula del usuario aquí
    this.productoService.listarProductosFavoritos(cedula).subscribe({
      next: (data: MensajeDTO) => {
        this.productosFavoritos = data.respuesta;
      },
      error: (error) => {
        this.alerta = new Alerta(error.error.respuesta, 'danger');
      },
    });
  }
}

