package co.edu.uniquindio.proyectoclinica;

import co.edu.uniquindio.proyectoclinica.model.dto.MedicoCrearDto;
import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.AdministradorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
public class AdministradorTest {

    @Autowired
    private AdministradorService administradorService;

    @Test
    public void crearMedicoTest (){

        MedicoCrearDto medicoDTo = new MedicoCrearDto("12344","Juan", "Suarez", "36548534",
                "Calarca", "jhoan@gmail.com", "123456", 0,
                "hunijmhuinj", LocalDateTime.of(2023,10,2,10,34),
                LocalDateTime.of(2023,10,2,10,34));

        try {
            administradorService.crearMedico(medicoDTo);
        }catch (Exception ignored){

        }

    }
}
