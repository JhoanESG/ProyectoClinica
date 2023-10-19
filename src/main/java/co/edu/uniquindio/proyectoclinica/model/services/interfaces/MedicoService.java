package co.edu.uniquindio.proyectoclinica.model.services.interfaces;
import co.edu.uniquindio.proyectoclinica.model.dto.Medico.*;
import co.edu.uniquindio.proyectoclinica.model.dto.DetalleCitaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.*;
import java.util.List;

public interface MedicoService {
    List<CitaMedicoDTo> verCitasPendientes(String codigoMedico) throws Exception;
    List<CitasMedicoDto> listarCitasMedico(String codigoMedico)throws Exception;
    DetalleCitaDto obtenerCita(int codigoCita) throws Exception;
    void radicarConsulta(AtenderCitaDto atenderCitaDto) throws Exception;
    List<HistorialPacientesAtendidosDto> listarCitasAtendidas (String idMedico) throws Exception;
    int asignarDiaLibre(DiaLibreDto diaLibreDto) throws Exception;
    List<DiaLibreDto> listaDiasLibresMedico(String idMedico) throws Exception;

}
