import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Alerta } from 'src/app/modelo/alerta';
import { MensajeDTO } from 'src/app/modelo/mensaje-dto';
import { ProductoPost } from 'src/app/modelo/producto-post';
import { ProductoService } from 'src/app/servicios/producto.service';

@Component({
  selector: 'app-producto-actualizar',
  templateUrl: './producto-actualizar.component.html',
  styleUrls: ['./producto-actualizar.component.css']
})
export class ProductoActualizarComponent {
  productoPost: ProductoPost;
  productoForm!: FormGroup;
  alerta!: Alerta;

  constructor(private formBuilder: FormBuilder, private productoService: ProductoService) {
    this.productoPost = new ProductoPost();
    this.crearProductoFormulario();
  }

  private crearProductoFormulario() {
    this.productoForm = this.formBuilder.group({
      usuarioCedula: new FormControl('', [Validators.required]),
      isActivo: new FormControl(true, [Validators.required]),
      UrlImagen: new FormControl('', [Validators.required]),
      nombre: new FormControl('', [Validators.required]),
      descripcion: new FormControl('', [Validators.required]),
      precio: new FormControl(0, [Validators.required]),
      isDisponible: new FormControl(true, [Validators.required]),
      categoria: new FormControl('', [Validators.required]),
      unidades: new FormControl(0, [Validators.required])
    });
  }

  public actualizarProducto() {
    const codigo = this.productoForm.value.codigo;
    const objeto = this;
    this.productoService.actualizarProducto(codigo, this.productoForm.value).subscribe({
      next: (data: MensajeDTO) => {
        objeto.alerta = new Alerta(data.respuesta, 'success');
        console.log('Producto actualizado correctamente');
      },
      error: (error) => {
        objeto.alerta = new Alerta(error.error.respuesta, 'danger');
        console.error('Error al actualizar el producto:', error);
      },
    });
  }

  public eliminarProducto(codigo: number) {
    const objeto = this;
    this.productoService.eliminarProducto(codigo).subscribe({
      next: (data: MensajeDTO) => {
        objeto.alerta = new Alerta(data.respuesta, 'success');
        console.log('Producto eliminado correctamente');
      },
      error: (error) => {
        objeto.alerta = new Alerta(error.error.respuesta, 'danger');
        console.error('Error al eliminar el producto:', error);
      },
    });
  }
}

