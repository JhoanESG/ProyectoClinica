package co.edu.uniquindio.proyectoclinica.controllers;

import co.edu.uniquindio.proyectoclinica.model.dto.ImagenDto;
import co.edu.uniquindio.proyectoclinica.model.dto.MensajeDto;
import co.edu.uniquindio.proyectoclinica.model.services.interfaces.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/imagenes")
@RequiredArgsConstructor
public class ImagenesController {

    private final ImageService imageService;

    @PostMapping("/subir")
    public ResponseEntity<MensajeDto<Map>> subir(@RequestParam("file") MultipartFile imagen) throws Exception {
        Map respuesta = imageService.subirImagen(imagen);
        return ResponseEntity.ok().body(new MensajeDto<>(false, respuesta));
    }

    @DeleteMapping("/eliminar")
    public ResponseEntity<MensajeDto<Map>> eliminar (@RequestBody ImagenDto imagenDto) throws Exception{

        Map respuesta = imageService.eliminarImagen(imagenDto.id());
        return ResponseEntity.ok().body(new MensajeDto<>(false,respuesta));
    }


}
