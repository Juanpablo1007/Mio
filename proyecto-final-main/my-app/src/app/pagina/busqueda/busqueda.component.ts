import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MensajeDTO } from 'src/app/modelo/mensaje-dto';
import { Alerta } from 'src/app/modelo/alerta';
import { ProductoService } from 'src/app/servicios/producto.service';

@Component({
  selector: 'app-busqueda',
  templateUrl: './busqueda.component.html',
  styleUrls: ['./busqueda.component.css']
})
export class BusquedaComponent {
  busquedaForm!: FormGroup;
  alerta!: Alerta;

  constructor(private formBuilder: FormBuilder, private productoService: ProductoService) { }

  private crearFormulario() {
    this.busquedaForm = this.formBuilder.group({
      nombre: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
    this.crearFormulario();
  }

  public buscarProducto() {
    const nombre = this.busquedaForm.get('nombre')?.value;

    this.productoService.listarProductoNombre(nombre).subscribe({
      next: (data: MensajeDTO) => {
        // Manejar la respuesta aquí
      },
      error: (error) => {
        this.alerta = new Alerta(error.error.respuesta, "danger");
      },
    });
  }
}
