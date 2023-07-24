export class EmailGetDTO {

    asunto:string = "";

    cuerpo:string = "";

    destinatario:string = "";

    constructor(asunto: string , cuerpo: string , destinatario: string ) {
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.destinatario = destinatario;
      }
}
