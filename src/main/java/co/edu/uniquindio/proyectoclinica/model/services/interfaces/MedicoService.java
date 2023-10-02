package co.edu.uniquindio.proyectoclinica.model.services.interfaces;
import co.edu.uniquindio.proyectoclinica.model.dto.CitaMedicoDTo;
import co.edu.uniquindio.proyectoclinica.model.dto.CitasMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.DetalleCitaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.HistorialPacientesAtendidosDto;
import co.edu.uniquindio.proyectoclinica.model.dto.*;
import java.util.List;

public interface MedicoService {
    List<CitaMedicoDTo> verCitasPendientes(String codigoMedico) throws Exception;
    DetalleCitaDto obtenerCita(String codigoCita) throws Exception;
    AtenderCitaDto atencerCita(String codigoCita) throws Exception;
    DatosVistaPrevia vistaPreviaCita(String id) throws Exception;
    List<HistorialPacientesAtendidosDto> listarCitasAtendidas () throws Exception;
    List<CitasMedicoDto> listarCitasMedico() throws Exception;

}
