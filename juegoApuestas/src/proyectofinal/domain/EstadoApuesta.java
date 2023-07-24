package proyectofinal.domain;

public enum EstadoApuesta {
    COLOCADA("Colocada"), ACTIVA("Activa"), FINALIZADA("Finalizada");
    
    private String tipo;

    private EstadoApuesta(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
