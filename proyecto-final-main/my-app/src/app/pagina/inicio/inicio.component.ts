import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent {
  categorias: string[] = [ "TECNOLOGIA",
    "VEHICULOS",
    "SUPERMERCADO",
    "ELECTRODOMESTICOS",
    "HOGAR_Y_MUEBLES",
    "BELLEZA_Y_CUIDADO_PERSONAL",
    "ACCERSORIOS_PARA_VEHICULOS",
    "HERRAMIENTAS",
    "CONSTRUCCION",
    "INMUEBLES",
    "MODA",
    "JUEGOS_Y_JUEGUETES",
    "BEBES"];
    constructor( private router: Router) {
    
       
    }
    public iraBusqueda(categoria: string) {
      if (categoria) {
        this.router.navigate(['/busquedaCategoria', categoria]);
      }
    }
}
