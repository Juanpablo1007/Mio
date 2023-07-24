import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MensajeDTO } from 'src/app/modelo/mensaje-dto';
import { Alerta } from 'src/app/modelo/alerta';
import { AuthService } from 'src/app/servicios/auth.service';

@Component({
  selector: 'app-cambiar-contrasenia',
  templateUrl: './cambiar-contrasenia.component.html',
  styleUrls: ['./cambiar-contrasenia.component.css']
})
export class CambiarContraseniaComponent {
  recuperarForm!: FormGroup;
  alerta!: Alerta;

  constructor(private formBuilder: FormBuilder, private authService: AuthService) { }

  private crearFormulario() {
    this.recuperarForm = this.formBuilder.group({
      email: new FormControl('', [Validators.required, Validators.email]),
    });
  }

  ngOnInit(): void {
    this.crearFormulario();
  }

  public recuperarCuenta() {
    const email = this.recuperarForm.get('email')?.value;

    this.authService.recuperarCuenta(email).subscribe({
      next: (data: MensajeDTO) => {
        this.alerta = new Alerta(data.respuesta, "success");
      },
      error: (error) => {
        this.alerta = new Alerta(error.error.respuesta, "danger");
      },
    });
  }
}
