package co.edu.uniquindio.proyectoclinica;

import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoCrearDto;
import co.edu.uniquindio.proyectoclinica.model.dto.admin.MedicoDto;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.AdministradorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

@SpringBootTest
public class AdministradorTest {

    @Autowired
    private AdministradorService administradorService;

    //@Test
    @Sql("classpath:dataset.sql")
    public void crearMedicoTest (){

        MedicoCrearDto medicoDTo = new MedicoCrearDto("12344","Juan", "Suarez", "36548534",
                "Calarca", "jhoan@gmail.com", "123456", 0,
                "hunijmhuinj", LocalDateTime.of(2023,10,2,10,34),
                LocalDateTime.of(2023,10,2,10,34));

        try {
            administradorService.crearMedico(medicoDTo);
        }catch (Exception exception){

        }
    }

    //@Test
    public void actualizarMedicoTest () throws Exception {
        //Obtener medico
        MedicoDto guardado = administradorService.obtenerMedico("2345678901");

        //Se le modifica el numero de telefono
        MedicoDto modificado = new MedicoDto(
                guardado.codigo(),
                guardado.nombre(), guardado.apellido(),
                "32542155",
                guardado.ciudad(),
                guardado.especialidad(),
                guardado.inicioJornada(),
                guardado.finJornada()
        );
        administradorService.actualizarMedico(modificado);
    }
    //@Test
    public void eliminarMedicoTest (){
        try {
            administradorService.eliminarMedico("12344");

        }catch (Exception e){

        }
    }

    @Test
    public void listarMedicosTest(){

    }


}
