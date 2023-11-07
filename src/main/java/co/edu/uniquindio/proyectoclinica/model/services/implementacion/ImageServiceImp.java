package co.edu.uniquindio.proyectoclinica.model.services.implementacion;

import co.edu.uniquindio.proyectoclinica.model.services.interfaces.ImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ImageServiceImp implements ImageService {

    private final Cloudinary cloudinary;

    public ImageServiceImp(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name","dk93isgmk");
        config.put("api_key","295948749218316");
        config.put("api_secret","mROWZegH_ioyM3JkRo0-A3-gkII");

        cloudinary = new Cloudinary(config);
    }

    private File convertir(MultipartFile imagen) throws IOException, IOException {
        File file = File.createTempFile(Objects.requireNonNull(imagen.getOriginalFilename()), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }

    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder","uniquindio/proyectoclinica/fotos"));
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }


}
