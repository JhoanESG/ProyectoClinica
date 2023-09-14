package co.edu.uniquindio.proyectoclinica.model.services;

public interface MedicoService {

    void verCitasPendientes();

    void atencerCita();

    void listarCitasPaciente(); //Historial medico

    void agendarDiaLibre();

    void listarCitasMedico();


}
