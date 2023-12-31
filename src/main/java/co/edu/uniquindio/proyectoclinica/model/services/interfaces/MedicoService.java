package co.edu.uniquindio.proyectoclinica.model.services.interfaces;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.ItemMedicamentoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.*;
import co.edu.uniquindio.proyectoclinica.model.dto.DetalleCitaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.DetalleConsultaDto;
import co.edu.uniquindio.proyectoclinica.model.entities.Medicamento;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoDiaLibre;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MedicoService {
    List<CitaMedicoDTo> verCitasPendientes(String codigoMedico) throws Exception;
    List<CitasMedicoDto> listarCitasMedico(String codigoMedico)throws Exception;
    DetalleCitaDto obtenerCita(int codigoCita) throws Exception;
    DetalleConsultaMedicoDto detalleConsulta(int codigoCita) throws Exception;
    int radicarConsulta(AtenderCitaDto atenderCitaDto) throws Exception;
    List<HistorialPacientesAtendidosDto> listarCitasAtendidas (String idMedico) throws Exception;
    int asignarDiaLibre(DiaLibreDto diaLibreDto) throws Exception;
    List<ItemDiaLibre> listarDiasLibresMedico(String idMedico) throws Exception;
    int cambiarEstadoDiaLibre(int diaLibre, String estadoDiaLibre)throws Exception;
    List<ItemMedicamentoDto> listarMedicamentos()throws Exception;
    ItemMedicamentoDto obtenerMedicamento(int codigo)throws Exception;


}
