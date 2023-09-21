package co.edu.uniquindio.proyectoclinica.model.services;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoCita;

import java.time.LocalDateTime;
import java.util.List;

public interface MedicoService {

    List<CitaMedicoDTo> verCitasPendientes(String codigoMedico);
    DetalleCitaDto obtenerCita(String codigoCita);
    atenderCitaDto atencerCita(String codigoCita);

    DatosVistaPrevia vistaPreviaCita(String id);

    List<HistorialPacientesAtendidosDto> listarCitasAtendidas ();

    void listarCitasPaciente(); //Historial medico

    void agendarDiaLibre();

    List<CitasMedicoDto> listarCitasMedico();


}
