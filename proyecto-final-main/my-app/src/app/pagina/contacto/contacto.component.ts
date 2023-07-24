import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators,  } from '@angular/forms';
import { Observable } from 'rxjs';
import { Alerta } from 'src/app/modelo/alerta';
import { MensajeDTO } from 'src/app/modelo/mensaje-dto';
import { ProductoService } from 'src/app/servicios/producto.service';
import { UsuarioService } from 'src/app/servicios/usuario.service';

@Component({
  selector: 'app-contacto',
  templateUrl: './contacto.component.html',
  styleUrls: ['./contacto.component.css']
})
export class ContactoComponent {
  usuarioForm!: FormGroup;
  alerta: Alerta | undefined;
 
  constructor(private formBuilder: FormBuilder,private usuarioService : UsuarioService,private productoService: ProductoService) {this.crearUsuarioFormulario();}

  public listarProductosPublicadosPorVendedor(cedula: string): Observable<MensajeDTO> {
    return this.productoService.listarProductosPublicadosPorVendedor(cedula);
  }

  public listarProductosFavoritos(cedula: string): Observable<MensajeDTO> {
    return this.productoService.listarProductosFavoritos(cedula);
  }
  private crearUsuarioFormulario() {
    this.usuarioForm = this.formBuilder.group({
      nombre: new FormControl('', [Validators.required]),
      correo: new FormControl('', [Validators.required, Validators.email]),
      contrasenia: new FormControl('', [Validators.required, Validators.minLength(6)]),
      direccion: new FormControl('', [Validators.required]),
      telefono: new FormControl('', [Validators.required, Validators.pattern(/^\d{10}$/)]),
      isCuentaActiva: new FormControl(true, [Validators.required])
    });
  }
  public actualizarUsuario() {
    const objeto = this;
    this.usuarioService.actualizarUsuario(this.usuarioForm.value).subscribe({
      next: (data: MensajeDTO) => {
        objeto.alerta = new Alerta(data.respuesta, 'success');
        console.log('Usuario actualizado correctamente');
      },
      error: (error) => {
        objeto.alerta = new Alerta(error.error.respuesta, 'danger');
        console.error('Error al actualizar el usuario:', error);
      },
    });
  }

  public eliminarUsuario(cedula: string) {
    const objeto = this;
    this.usuarioService.eliminarUsuario(cedula).subscribe({
      next: (data: MensajeDTO) => {
        objeto.alerta = new Alerta(data.respuesta, 'success');
        console.log('Usuario eliminado correctamente');
      },
      error: (error) => {
        objeto.alerta = new Alerta(error.error.respuesta, 'danger');
        console.error('Error al eliminar el usuario:', error);
      },
    });
  }
}


