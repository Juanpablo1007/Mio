package proyectofinal.domain;

public enum Posicion {
    
    DELANTERO("Delantero"), MEDIOCAMPISTA("Mediocampista"), DEFENSA("Defensa"), PORTERO("Portero");

    private String tipo;

    private Posicion(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return this.tipo;
    }
}
