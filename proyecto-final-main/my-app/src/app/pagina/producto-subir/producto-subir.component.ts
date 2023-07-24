import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Alerta } from 'src/app/modelo/alerta';
import { ProductoPost } from 'src/app/modelo/producto-post';
import { ImagenService } from 'src/app/servicios/imagen.service';
import { ProductoService } from 'src/app/servicios/producto.service';

@Component({
selector: 'app-producto-subir',
templateUrl: './producto-subir.component.html',
styleUrls: ['./producto-subir.component.css']
})
export class ProductoSubirComponent {
producto: ProductoPost;
productoForm!: FormGroup;
alerta!: Alerta;
imagen:string = "";


constructor(private formBuilder: FormBuilder, private productoService: ProductoService,private imagenService:ImagenService) {
this.producto = new ProductoPost();
}

private crearFormulario() {
this.productoForm = this.formBuilder.group({
usuarioCedula: new FormControl('', [Validators.required]),
isActivo: new FormControl(true, [Validators.required]),
UrlImagen: new FormControl('', [Validators.required]),
nombre: new FormControl('', [Validators.required]),
descripcion: new FormControl('', [Validators.required]),
precio: new FormControl(0, [Validators.required]),
isDisponible: new FormControl(true, [Validators.required]),
categoria: new FormControl('', [Validators.required]),
unidades: new FormControl(0, [Validators.required]),
});
}
onFileChange(event:any){
  if (event.target.files.length > 0) {
  const file = event.target.files[0];
  console.log(file);
  }
  }
ngOnInit(): void {
this.crearFormulario();
}

public publicarProducto() {
const objeto = this;
const formData = new FormData();
formData.append('file', this.productoForm.get('imagen')!.value);
const producto = this.convertirObjeto(this.productoForm.value);
if(this.imagen == null){
  this.imagenService.subir(formData).subscribe({
    next(mensaje){
      objeto.imagen = mensaje.respuesta.url;
      producto.UrlImagen = mensaje.respuesta.url;
      objeto.enviarInformacion(producto);
    },
    error(err) {
      console.log(err);
      },
  }
    
  );
}else{
  producto.UrlImagen = this.imagen;
  this.enviarInformacion(producto);
  }

}
private convertirObjeto(objeto:any):ProductoPost{
  const producto = new ProductoPost;
  producto.nombre = objeto["nombre"];
  producto.categoria = objeto["categoria"];
  producto.descripcion=objeto["descipcion"];
  producto.isActivo = objeto["isActivo"];
  producto.isDisponible = objeto["isDisponible"];
  producto.precio = objeto["precio"];
  producto.unidades = objeto["unidades"];
  producto.usuarioCedula = objeto ["usuarioCedula"];
  return producto;
  }
  private enviarInformacion(producto:ProductoPost){
    const objeto = this;
    this.productoService.publicarProducto( producto ).subscribe({
    next(value) {
    console.log(value);
    },
    error(err) {
    console.log(err);
    },
    });
    }
}

