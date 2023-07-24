package proyectofinal.domain;

public enum TipoEvento {
    GOL("Gol"),
    FALTA("Falta"),
    TARJETA("Tarjeta"),
    ATAJADA("Atajada"),
    TIRO_ESQUINA("Tiro de esquina");
    
    private String nombre;

    private TipoEvento(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    
}
