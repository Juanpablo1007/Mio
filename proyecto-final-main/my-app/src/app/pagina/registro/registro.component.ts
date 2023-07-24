import { Component } from '@angular/core';
import { UsuarioPost } from 'src/app/modelo/usuario-post';
import {FormBuilder,FormControl,FormGroup,Validators,} from '@angular/forms';
import { Alerta } from 'src/app/modelo/alerta';
import { AuthService } from 'src/app/servicios/auth.service';
@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent {
usuario!:UsuarioPost;
registroForm!: FormGroup;
alerta!:Alerta;

constructor(private formBuilder: FormBuilder, private authService: AuthService){
  this.usuario = new UsuarioPost();
}
private crearFormulario() {
  this.registroForm = this.formBuilder.group({
    nombre: new FormControl('', [Validators.required]),
    correo: new FormControl('', [Validators.required, Validators.email]),
    direccion: new FormControl('', [Validators.required]),
    telefono: new FormControl('', [
      Validators.required,
      Validators.maxLength(10),
    ]),
    contrasenia: new FormControl('', [
      Validators.required,
      Validators.maxLength(50),
    ]),
    confirmaPassword: new FormControl('', [Validators.required]),
  });
}
ngOnInit(): void {
  this.crearFormulario();
}
public registrar() {

  const objeto = this;

  this.authService.registrar(this.usuario).subscribe({
    next: (data) => {
      objeto.alerta = new Alerta(data.respuesta, "success")
    },
    error: (error) => {
      objeto.alerta = new Alerta(error.error.respuesta, "danger")
    },
  });
}


}
