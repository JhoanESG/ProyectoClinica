package co.edu.uniquindio.proyectoclinica.model.services;

public interface PacienteService {

    void registrar();

    void editarPerfil();

    void eliminarCuenta();

    void enviarLinkRecuperacion();

    void cambiarContrasena();

    void agendarCita();

    void crearPQRS();

    void listarPQRSOaciente();

    void responderPQRS();

    //Los metodos filtrar se pueden tener como solo uno que se interpreta con distintos parametros
    void filtarCitasPorFecha();

    void filtrarCitasPorFecha();
}
