package co.edu.uniquindio.proyectoclinica;


import co.edu.uniquindio.proyectoclinica.model.dto.AtenderCitaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.DetalleCitaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.MedicamentosDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.CitaMedicoDTo;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.CitasMedicoDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.DiaLibreDto;
import co.edu.uniquindio.proyectoclinica.model.dto.medico.HistorialPacientesAtendidosDto;
import co.edu.uniquindio.proyectoclinica.model.entities.Consulta;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.MedicoService;
import co.edu.uniquindio.proyectoclinica.repositorios.ConsultaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
@Sql("classpath:dataset.sql")
public class MedicoTest {

    @Autowired
    private MedicoService medicoService;

    //@Test
    public void verCitasPendientesTest()throws Exception{

        List<CitaMedicoDTo> citas = medicoService.verCitasPendientes("2345678901");

        citas.forEach(System.out::println);
        Assertions.assertEquals(1,citas.size());

    }

    //@Test
    public void listarCitasMedico() throws Exception{
        List<CitasMedicoDto> lista = medicoService.listarCitasMedico("2345678901");

        lista.forEach(System.out::println);
        Assertions.assertEquals(1,lista.size());
    }

    //@Test
    public void obtenerCita () throws Exception{

        DetalleCitaDto cita = medicoService.obtenerCita(1);
        Assertions.assertEquals("Consulta de rutina",cita.motivo());

    }

    //@Test
    public void radicarConsultaTest() throws Exception{

        List<MedicamentosDto> medicamentos = new ArrayList<>();

        medicamentos.add(new MedicamentosDto("Medicamento1", "Posología1"));

        AtenderCitaDto citaDto = new AtenderCitaDto(
                5,"Dolor de barriga",
                "Leve infeccion Intestinal","tomar el medicamento",
                "",medicamentos);
        int i= medicoService.radicarConsulta(citaDto);
        Assertions.assertEquals(6,i);
    }

    //@Test
    public void listarCitasAtendidasTest () throws Exception{
        List<HistorialPacientesAtendidosDto> lista = medicoService.listarCitasAtendidas("2345678901");
        lista.forEach(System.out::println);
        Assertions.assertEquals(1,lista.size());
    }

    @Test
    public void asignarDiaLibre() throws Exception{
        DiaLibreDto diaLibre = new DiaLibreDto(
                LocalDate.of(2023,11,2),
                "2345678901");
        int i =medicoService.asignarDiaLibre(diaLibre);
        Assertions.assertEquals(2,i);

        DiaLibreDto diaLibre2 = new DiaLibreDto(
                LocalDate.of(2023,10,31),
                "2345678901");

        Assertions.assertThrows(Exception.class, () -> medicoService.asignarDiaLibre(diaLibre2));

    }

    @Test
    public void listaDiasLibreTest () throws Exception{
        List<DiaLibreDto> diasLibres = medicoService.listaDiasLibresMedico("2345678901");

        diasLibres.forEach(System.out::println);
        Assertions.assertEquals(1,diasLibres.size());
    }


}
