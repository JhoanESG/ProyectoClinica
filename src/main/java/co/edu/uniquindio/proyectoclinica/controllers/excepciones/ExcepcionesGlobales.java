package co.edu.uniquindio.proyectoclinica.controllers.excepciones;

import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import co.edu.uniquindio.proyectoclinica.model.dto.ValidacionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExcepcionesGlobales {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDto<String>> generalException(Exception e){
        return ResponseEntity.internalServerError().body(new MensajeDto<>(true,e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensajeDto> validationException(MethodArgumentNotValidException ex){
        List<ValidacionDto>  errores = new ArrayList<>();
        BindingResult results = ex.getBindingResult();

        for (FieldError e: results.getFieldErrors()){
            errores.add(new ValidacionDto(e.getField(),e.getDefaultMessage()));
        }

        return ResponseEntity.badRequest().body(new MensajeDto<>(true,errores));
    }


}
