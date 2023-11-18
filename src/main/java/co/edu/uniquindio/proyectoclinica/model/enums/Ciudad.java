package co.edu.uniquindio.proyectoclinica.model.enums;

public enum Ciudad {
    PEREIRA("Pereira"),
    ARMENIA("Armenia"),
    MANIZALES("Manizales"),
    CALARCA("Calarca");

    private final String nombre;

    Ciudad(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
