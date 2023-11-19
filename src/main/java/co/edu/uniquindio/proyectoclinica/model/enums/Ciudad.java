package co.edu.uniquindio.proyectoclinica.model.enums;

public enum Ciudad {
    PEREIRA("PEREIRA"),
    ARMENIA("ARMENIA"),
    MANIZALES("MANIZALES"),
    CALARCA("CALARCA");

    private final String nombre;

    Ciudad(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
