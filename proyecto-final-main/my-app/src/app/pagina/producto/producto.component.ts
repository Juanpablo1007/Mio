import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Alerta } from 'src/app/modelo/alerta';
import { CarritoProductosDTO } from 'src/app/modelo/carrito-productos-dto';
import { ComentarioPost } from 'src/app/modelo/comentario-post';
import { GestionFavoritosDTO } from 'src/app/modelo/gestion-favoritos-dto';
import { MensajeDTO } from 'src/app/modelo/mensaje-dto';
import { CarritoService } from 'src/app/servicios/carrito.service';
import { ProductoService } from 'src/app/servicios/producto.service';

@Component({
  selector: 'app-producto',
  templateUrl: './producto.component.html',
  styleUrls: ['./producto.component.css']
})
export class ProductoComponent {
  
  gestionFavoritos: GestionFavoritosDTO;
  comentarioForm!: FormGroup;
  gestionFavoritosForm!: FormGroup;
  alerta!: Alerta;


  constructor(private comentario: ComentarioPost,  private formBuilder: FormBuilder, private productoService: ProductoService, private carritoProductosPostDTO: CarritoProductosDTO, private   carritoService:  CarritoService) {
    
    this.gestionFavoritos = new GestionFavoritosDTO();
  }

  private crearComentarioFormulario() {
    this.comentarioForm = this.formBuilder.group({
      idProducto: new FormControl('', [Validators.required]),
      usuarioCedula: new FormControl('', [Validators.required]),
      comentario: new FormControl('', [Validators.required]),
    });
  }

  private crearGestionFavoritosFormulario() {
    this.gestionFavoritosForm = this.formBuilder.group({
      idProducto: new FormControl('', [Validators.required]),
      usuarioCedula: new FormControl('', [Validators.required]),
      isFavorito: new FormControl(true, [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.crearComentarioFormulario();
    this.crearGestionFavoritosFormulario();
  }

  public comentarProducto() {
    const objeto = this;
    this.productoService.comentarProducto(this.comentario).subscribe({
      next: (data: MensajeDTO) => {
        objeto.alerta = new Alerta(data.respuesta, 'success');
      },
      error: (error) => {
        objeto.alerta = new Alerta(error.error.respuesta, 'danger');
      },
    });
  }

  public guardarProductoFavorito() {
    const objeto = this;
    this.productoService.guardarProductoFavorito(this.gestionFavoritos).subscribe({
      next: (data: MensajeDTO) => {
        objeto.alerta = new Alerta(data.respuesta, 'success');
      },
      error: (error) => {
        objeto.alerta = new Alerta(error.error.respuesta, 'danger');
      },
    });
  }

  public agregarProducto(carritoProductosPostDTO: CarritoProductosDTO): Observable<MensajeDTO> {
    return this.carritoService.agregarProducto(carritoProductosPostDTO);
  }
}
