export class ModeradorGetDTO {
    nombre:string = "";
    correo:string = "";
    cedula:string = "";
    constructor(nombre: string , correo: string , cedula: string ) {
        this.nombre = nombre;
        this.correo = correo;
        this.cedula = cedula;
      }
}
