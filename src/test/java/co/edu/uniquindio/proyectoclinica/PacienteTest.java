package co.edu.uniquindio.proyectoclinica;

import co.edu.uniquindio.proyectoclinica.model.dto.CambiarContrasenaDto;
import co.edu.uniquindio.proyectoclinica.model.dto.paciente.*;
import co.edu.uniquindio.proyectoclinica.model.enums.*;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@Sql("classpath:dataset.sql")
public class PacienteTest {
    @Autowired
    private PacienteService pacienteService;

    //@Test
    public void registrarTest()throws Exception{
        CrearPacienteDto crearPacienteDto = new CrearPacienteDto(
                "1080360654","Santiago","Ramon",
                "32501248", Ciudad.ARMENIA,"santiago@gmail.com",
                "25365","No","http", TipoSangre.Op,
                Eps.Cafesalud, LocalDateTime.of(2023,10,2,1,0)
        );
        String id= pacienteService.registrar(crearPacienteDto);
        Assertions.assertEquals("1080360654",id);
    }

    @Test
    public void editarPerfilTest()throws Exception{
        ActualizarPacienteDto guardado = pacienteService.obtenerPaciente("3456789012");

        ActualizarPacienteDto modificado = new ActualizarPacienteDto(
                guardado.cedula(),
                guardado.nombre(),
                guardado.apellido(),
                guardado.telefono(),
                guardado.ciudad(),
                guardado.email(),

                "Polvo",
                guardado.tipoSangre(),
                guardado.eps(),
                guardado.foto()
        );

        pacienteService.editarPerfil(modificado);
        ActualizarPacienteDto objetoModificado = pacienteService.obtenerPaciente("3456789012");

        Assertions.assertEquals("Polvo",objetoModificado.alergias());
    }

    //@Test
    public void eliminarCuentaTest () throws Exception{

        pacienteService.eliminarCuenta("3456789012");

        Assertions.assertThrows(Exception.class, () -> pacienteService.obtenerPaciente("3456789012"));
    }

    //@Test
    public void enviarLinkRecuperacionTest()throws Exception{

    }

    //@Test
    public void confirmarCodigo()throws Exception{

    }

    //@Test
    public void cambiarContrasenaTest() throws Exception {
        CambiarContrasenaDto cambiarContrasenaDto= new CambiarContrasenaDto(
                "3456789012"
        );
        pacienteService.cambiarContrasena("123456789",cambiarContrasenaDto);

        ActualizarPacienteDto paciente = pacienteService.obtenerPaciente(cambiarContrasenaDto.cc());


        //Assertions.assertEquals("123456789",paciente.contrasena());
    }

    @Test
    public void agendarCitaTest()throws Exception{
        RegistroCitaDto registroCitaDto = new RegistroCitaDto(
                Especialidad.CARDIOLOGIA,
                LocalDateTime.of(2023, 11, 12, 10, 0),
                "Examen de rutina",
                "2345678901", // CC del médico
                "3456789012"  // CC del paciente
        );

        pacienteService.agendarCita(registroCitaDto);

        DetalleCitaPacienteDto detalleCitaPacienteDto= pacienteService.detalleCita(6);
        Assertions.assertEquals("Examen de rutina",detalleCitaPacienteDto.motivo());

    }

    //@Test
    public void crearPQRSTest()throws Exception{
        // Datos de prueba para crear una PQRS
        CrearPQRSdto crearPQRSdto = new CrearPQRSdto(
                "Reclamo por demora en atención",
                1, //
                TipoPQRS.Reclamo,
                "Demora en la atención médica"
        );
        // Crear la PQRS
        pacienteService.crearPQRS(crearPQRSdto);

        DetallePQRSdto detallePQRSdto= pacienteService.detallePQRS(6);
        Assertions.assertEquals(6,detallePQRSdto.idPQRS());
        Assertions.assertEquals("Reclamo por demora en atención",detallePQRSdto.asunto());

    }

    //@Test
    public void DetalleCitaTest()throws Exception{
        DetalleCitaPacienteDto detalleCitaPacienteDto= pacienteService.detalleCita(1);
        Assertions.assertEquals("Consulta de rutina",detalleCitaPacienteDto.motivo());
    }

    //@Test
    public void listarPQRSpacienteTest()throws Exception{
        List<PQRSpacienteDto> lista= pacienteService.listarPQRSpaciente("3456789012");
        lista.forEach(System.out::println);
        Assertions.assertEquals(1,lista.size());

    }

    //@Test
    public void listarCitasPacienteTest()throws Exception{
        List<CitaPacienteDto> lista = pacienteService.listarCitasPaciente("3456789012");
        lista.forEach(System.out::println);
        Assertions.assertEquals(1,lista.size());
    }





}
