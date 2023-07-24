import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { SesionService } from "./servicios/sesion.service";
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Unimarket';
  fecha = 'Abril de 2023';
  isLogged = false;
  email: string = '';

  constructor(
    private router: Router,
    
    private sesionServicio: SesionService
  ) {}

  ngOnInit(): void {
    
  }
  private actualizarSesion(estado: boolean) {
   
  }
  public logout() {
   
  }

  
  

  public iraBusqueda(valor: string) {
    if (valor) {
      this.router.navigate(['/busqueda', valor]);
    }
  }
}
