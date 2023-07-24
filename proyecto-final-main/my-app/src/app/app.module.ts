import { NgModule } from '@angular/core'; 
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AlertaComponent } from './pagina/alerta/alerta.component';
import { BusquedaComponent } from './pagina/busqueda/busqueda.component';
import { CambiarContraseniaComponent } from './pagina/cambiar-contrasenia/cambiar-contrasenia.component';
import { CarritoComponent } from './pagina/carrito/carrito.component';
import { ContactoComponent } from './pagina/contacto/contacto.component';
import { ProductoComponent } from './pagina/producto/producto.component';
import { ProductoFavoritosComponent } from './pagina/producto-favoritos/producto-favoritos.component';
import { ProductoSubirComponent } from './pagina/producto-subir/producto-subir.component';
import { HistorialComprasComponent } from './pagina/historial-compras/historial-compras.component';
import { HistorialVentasComponent } from './pagina/historial-ventas/historial-ventas.component';
import { InicioComponent } from './pagina/inicio/inicio.component';
import { LoginComponent } from './pagina/login/login.component';
import { TransaccionesComponent } from './pagina/transacciones/transacciones.component';
import { RegistroComponent } from './pagina/registro/registro.component';
import { ResultadoBusquedaComponent } from './pagina/resultado-busqueda/resultado-busqueda.component';
import { RevisarPublicacionComponent } from './pagina/revisar-publicacion/revisar-publicacion.component';
import { BuscarPorPrecioComponent } from './pagina/buscar-por-precio/buscar-por-precio.component';
import { ResultadoBusquedaPorPrecioComponent } from './pagina/resultado-busqueda-por-precio/resultado-busqueda-por-precio.component';
import { ProductoActualizarComponent } from './pagina/producto-actualizar/producto-actualizar.component';
import { UsuarioActualizarComponent } from './pagina/usuario-actualizar/usuario-actualizar.component';
import { ProductoListarEstadoComponent } from './pagina/producto-listar-estado/producto-listar-estado.component';
import { ProductoListarVendedorComponent } from './pagina/producto-listar-vendedor/producto-listar-vendedor.component';
import { RouterModule } from '@angular/router';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { UsuarioInterceptor } from './interceptor/usuario.interceptor';




@NgModule({
  declarations: [
   AppComponent,
   AlertaComponent,
   BusquedaComponent,
   CambiarContraseniaComponent,
   CarritoComponent,
   ContactoComponent,
   ProductoComponent,
   ProductoFavoritosComponent,
   ProductoSubirComponent,
   HistorialComprasComponent,
   HistorialVentasComponent,
   InicioComponent,
   LoginComponent,
   TransaccionesComponent,
   RegistroComponent,
   ResultadoBusquedaComponent,
   RevisarPublicacionComponent,
   BuscarPorPrecioComponent,
   ResultadoBusquedaPorPrecioComponent,
   ProductoActualizarComponent,
   UsuarioActualizarComponent,
   ProductoListarEstadoComponent,
   ProductoListarVendedorComponent

 
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
    ,FormsModule,RouterModule,HttpClientModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: UsuarioInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
