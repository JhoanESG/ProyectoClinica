package co.edu.uniquindio.proyectoclinica;

import co.edu.uniquindio.proyectoclinica.model.dto.*;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoCrearDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoDto;
import co.edu.uniquindio.proyectoclinica.model.entities.Mensaje;
import co.edu.uniquindio.proyectoclinica.model.entities.Usuario;
import co.edu.uniquindio.proyectoclinica.model.enums.EstadoPQRS;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.AdministradorService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
@Sql("classpath:dataset.sql")
public class AdministradorTest {

    @Autowired
    private AdministradorService administradorService;

    //@Test
    public void crearMedicoTest () throws Exception{

        MedicoCrearDto medicoDTo = new MedicoCrearDto("12344","Juan", "Suarez", "36548534",
                "Calarca", "jhoan@gmail.com", "123456", 0,
                "hunijmhuinj", LocalDateTime.of(2023,10,2,10,34),
                LocalDateTime.of(2023,10,2,10,34));

        String i = administradorService.crearMedico(medicoDTo);
        Assertions.assertNotEquals("0",i);

    }

    //@Test
    //@Sql("classpath:dataset.sql")
    public void actualizarMedicoTest () throws Exception {

        //Obtenemos el paciente
        MedicoDto guardado = administradorService.obtenerMedico("2345678901");

        //Modificamos un dato
        MedicoDto modificado = new MedicoDto(
                guardado.cedula(),
                guardado.nombre(),
                guardado.apellido(),
                "32512842",
                guardado.ciudad(),
                guardado.especialidad(),
                guardado.email(),
                guardado.foto(),
                guardado.inicioJornada(),
                guardado.finJornada()
        );

        //Se invoca el metodo para actualizar los datos
        administradorService.actualizarMedico(modificado);

        //Se obtiene de nuevo el paciente para revisar el cambio
        MedicoDto objetoModificado = administradorService.obtenerMedico("2345678901");

        //Se comprueba que el dato se haya cambiado
        Assertions.assertEquals("32512842",objetoModificado.telefono());
    }
    //@Test
    //@Sql("classpath:dataset.sql")
    public void eliminarMedicoTest () throws Exception{

        //Se elimina el medico con
        administradorService.eliminarMedico("2345678901");

        Assertions.assertThrows(Exception.class, () -> administradorService.obtenerMedico("2345678901"));

    }

    //@Test
    //@Sql("classpath:dataset.sql")
    public void listarMedicosTest() throws Exception{
        List<MedicoListarDto> medicos= administradorService.listarMedicos();
        medicos.forEach(System.out::println);

        Assertions.assertEquals(1, medicos.size());
    }

    //@Test
    public void listarPQRSTest() throws Exception{
        List<PQRSAdminDto> listaPQRS = administradorService.listarPQRS();
        listaPQRS.forEach(System.out::println);

        Assertions.assertEquals(1,listaPQRS.size());
    }

    //@Test
    public void verDetallePQRS() throws Exception{

        DetallePQRSdto pqrs= administradorService.verDetallePQRS(1);

        Assertions.assertEquals(1,pqrs.idPQRS());
    }

    //@Test
    public void convertirRespuestasDtoTest()throws Exception{


    }

    //@Test
    public void responderPQRSTest() throws Exception{
        RespuestaPQRSDto respuestaPQRSDto = new RespuestaPQRSDto(
                1,"La respuesta a la solicitud",
                "3456789012"
        );
        int i = administradorService.responderPQRS(respuestaPQRSDto);
        Assertions.assertEquals(3,i);
    }

    //@Test
    public void cambiarEstadoPqrsTest()throws Exception{
        int i = administradorService.cambiarEstadoPqrs(1, EstadoPQRS.Activo);
        Assertions.assertEquals(1, i);
    }

    @Test
    public void listarCitasMedicoTest () throws Exception{
        List<CitasMedicoDto> citas= administradorService.listarCitasMedico();
        citas.forEach(System.out::println);

        Assertions.assertEquals(1,citas.size());
    }





}
