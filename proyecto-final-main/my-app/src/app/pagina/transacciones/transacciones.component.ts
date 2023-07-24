import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { Alerta } from 'src/app/modelo/alerta';
import { MensajeDTO } from 'src/app/modelo/mensaje-dto';
import { TransaccionPost } from 'src/app/modelo/transaccion-post';
import { TransaccionService } from 'src/app/servicios/transaccion.service';

@Component({
  selector: 'app-transacciones',
  templateUrl: './transacciones.component.html',
  styleUrls: ['./transacciones.component.css']
})
export class TransaccionesComponent {
  transaccionPost: TransaccionPost;
  transaccionForm!: FormGroup;
  alerta!: Alerta;

  constructor(private formBuilder: FormBuilder, private transaccionService: TransaccionService) {
    this.transaccionPost = new TransaccionPost();
    this.crearTransaccionFormulario();
  }

  private crearTransaccionFormulario() {
    this.transaccionForm = this.formBuilder.group({
      usuarioCompradorCedula: new FormControl('', [Validators.required]),
      usuarioVendedorCedula: new FormControl('', [Validators.required]),
      productoCodigo: new FormControl('', [Validators.required]),
      metodoDePago: new FormControl('', [Validators.required]),
      unidadesCompradas: new FormControl('', [Validators.required])
    });
  }

  public realizarTransaccion() {
    const objeto = this;
    this.transaccionService.realizarTransaccion(this.transaccionForm.value).subscribe({
      next: (data: MensajeDTO) => {
        objeto.alerta = new Alerta(data.respuesta, 'success');
        console.log('Transacción realizada correctamente');
      },
      error: (error) => {
        objeto.alerta = new Alerta(error.error.respuesta, 'danger');
        console.error('Error al realizar la transacción:', error);
      },
    });
  }
}

