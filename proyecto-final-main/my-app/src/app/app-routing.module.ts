import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

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
import { RolesGuard } from './guards/roles.guard';

const routes: Routes = [
  { path: 'alerta', component: AlertaComponent },
  { path: 'busqueda', component: BusquedaComponent },
  { path: 'cambiarContrasenia', component: CambiarContraseniaComponent },
  { path: 'carrito', component: CarritoComponent },
  { path: 'contacto', component: ContactoComponent },
  { path: 'producto', component: ProductoComponent },
  { path: 'productoFavoritos', component: ProductoFavoritosComponent },
  {
    path: 'productoSubir',
    component: ProductoSubirComponent,
    canActivate: [RolesGuard],
    data: {
      expectedRole: ['USUARIO'],
    },
  },
  { path: 'historialCompras', component: HistorialComprasComponent },
  { path: 'historialVentas', component: HistorialVentasComponent },
  { path: 'inicio', component: InicioComponent },
  { path: 'login', component: LoginComponent },
  { path: 'transacciones', component: TransaccionesComponent, canActivate: [RolesGuard],
  data: { expectedRole: ['USUARIO'] }, },
  { path: 'registro', component: RegistroComponent },
  { path: 'resultadoBusqueda', component: ResultadoBusquedaComponent },
  { path: 'revisarPublicacion', component: RevisarPublicacionComponent, canActivate: [RolesGuard],
  data: { expectedRole: ["MODERADOR"] } },
  { path: 'buscarPorPrecio', component: BuscarPorPrecioComponent },
  {
    path: 'resultadoBusquedaPorPrecio',
    component: ResultadoBusquedaPorPrecioComponent,
  },
  { path: 'productoActualizar', component: ProductoActualizarComponent, canActivate: [RolesGuard],
  data: { expectedRole: ['USUARIO'] },
 },
  { path: 'usuarioActualizar', component: UsuarioActualizarComponent,canActivate: [RolesGuard],
  data: { expectedRole: ['USUARIO'] }, },
  { path: 'productoListarEstado', component: ProductoListarEstadoComponent },
  {
    path: 'productoListarVendedor',
    component: ProductoListarVendedorComponent,
  },
  { path: '**', pathMatch: 'full', redirectTo: '' },
];

@NgModule({
  imports: [BrowserModule, FormsModule, RouterModule.forRoot(routes)],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppRoutingModule {}
