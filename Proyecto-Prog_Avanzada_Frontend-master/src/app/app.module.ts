import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Buffer } from "buffer";
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InicioComponent } from './pagina/inicio/inicio.component';
import { LoginComponent } from './pagina/login/login.component';
import { RegistroComponent } from './pagina/registro/registro.component';
import { CarritoComponent } from './pagina/carrito/carrito.component';
import { RevisarPublicacionComponent } from './pagina/revisar-publicacion/revisar-publicacion.component';
import { FavoritosComponent } from './pagina/favoritos/favoritos.component';
import { OfertasComponent } from './pagina/ofertas/ofertas.component';
import { PagoComponent } from './pagina/pago/pago.component';
import { PerfilComponent } from './pagina/perfil/perfil.component';
import { PublicacionesComponent } from './pagina/publicaciones/publicaciones.component';
import { PublicarProductoComponent } from './pagina/publicar-producto/publicar-producto.component';
import { CambiarContraseniaComponent } from './pagina/cambiar-contrasenia/cambiar-contrasenia.component';
import { ResultadosBusquedaComponent } from './pagina/resultados-busqueda/resultados-busqueda.component';
import { HistorialComprasComponent } from './pagina/historial-compras/historial-compras.component';
import { DetalleProductoComponent } from './pagina/detalle-producto/detalle-producto.component';
import { ContactoComponent } from './pagina/contacto/contacto.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BusquedaComponent } from './pagina/busqueda/busqueda.component';
import { GestionPublicacionesComponent } from './pagina/gestion-publicaciones/gestion-publicaciones.component';
import { AlertaComponent } from './pagina/alerta/alerta.component';
import { UsuarioInterceptor } from './interceptor/usuario.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    LoginComponent,
    RegistroComponent,
    CarritoComponent,
    RevisarPublicacionComponent,
    FavoritosComponent,
    OfertasComponent,
    PagoComponent,
    PerfilComponent,
    PublicacionesComponent,
    PublicarProductoComponent,
    CambiarContraseniaComponent,
    ResultadosBusquedaComponent,
    HistorialComprasComponent,
    DetalleProductoComponent,
    ContactoComponent,
    BusquedaComponent,
    GestionPublicacionesComponent,
    AlertaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: UsuarioInterceptor, multi: true }
  ],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
