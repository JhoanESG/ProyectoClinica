package co.edu.uniquindio.proyectoclinica.model.dto;

import co.edu.uniquindio.proyectoclinica.model.enums.Especialidad;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record MedicoCrearDto(
        @NotNull @Length(max=10)
        String cedula,

        @NotNull @Length(max = 50)
        String nombre,

        @NotNull @Length(max = 50)
        String apellido,

        @NotNull @Length(max = 10)
        String telefono,

        @NotNull @Length(max = 20)
        String ciudad,
        @NotNull @Email
        String email,
        @NotNull
        String contrasena,
        @NotNull @Length(min = 0) @Length(max = 3)
        int especialidad,
        @NotNull
        String foto,
        @NotNull
        LocalDateTime horaInicio,
        @NotNull
        LocalDateTime horaFin
) {
}
