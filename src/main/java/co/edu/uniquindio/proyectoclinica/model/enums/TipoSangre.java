package co.edu.uniquindio.proyectoclinica.model.enums;

public enum TipoSangre {
    Ap("A+"),
    An("A-"),
    Bp("B+"),
    Bn("B-"),
    ABp("AB+"),
    ABn("AB-"),
    Op("O+"),
    On("O-");

    private final String nombre;

    TipoSangre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
