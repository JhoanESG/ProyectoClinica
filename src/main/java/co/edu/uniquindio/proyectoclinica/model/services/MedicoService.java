package co.edu.uniquindio.proyectoclinica.model.services;

import co.edu.uniquindio.proyectoclinica.model.dto.*;

import java.util.List;

public interface MedicoService {

    List<CitaMedicoDTo> verCitasPendientes(String codigoMedico) throws Exception;
    DetalleCitaDto obtenerCita(String codigoCita) throws Exception;
    AtenderCitaDto atencerCita(String codigoCita) throws Exception;
    DatosVistaPrevia vistaPreviaCita(String id) throws Exception;
    List<HistorialPacientesAtendidosDto> listarCitasAtendidas () throws Exception;

    void listarCitasPaciente(); //Historial medico

    void agendarDiaLibre();

    List<CitasMedicoDto> listarCitasMedico() throws Exception;


}
