package co.edu.uniquindio.proyectoclinica.model.services;

import co.edu.uniquindio.proyectoclinica.model.dto.CitaMedicoDTo;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoService {

    List<CitaMedicoDTo> verCitasPendientes(String codigoMedico);

    void atencerCita();

    void listarCitasPaciente(); //Historial medico

    void agendarDiaLibre();

    void listarCitasMedico();


}
