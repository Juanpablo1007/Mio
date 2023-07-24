package co.edu.uniquindio.proyecto.proyectotienda.servicios.implementacion;

import co.edu.uniquindio.proyecto.proyectotienda.servicios.interfaces.CloudinaryServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicioImpl implements CloudinaryServicio {

    private Cloudinary cloudinary;
    private Map<String, String> config;


    public CloudinaryServicioImpl() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dpjvhrfgh");
        config.put("api_key", "962267125175615");
        config.put("api_secret", "ZvydjgC7N-PrjP7ZAMweOEFu_Cw");
        cloudinary = new Cloudinary(config);

    }

    @Override
    public Map subirImagen(File file, String carpeta) throws Exception {
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder",
                String.format("proyectotienda/%s", carpeta)));

    }

    @Override
    public Map eliminarImagen(String url) throws Exception {
        return cloudinary.uploader().destroy(obtenerId(url), ObjectUtils.emptyMap());
    }

    @Override
    public File convertir(MultipartFile imagen) throws Exception {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }

    private String obtenerId(String url) {
        int inicio = url.indexOf("proyectotienda");
        int fin = url.lastIndexOf(".");
        return url.substring(inicio, fin);
    }
}
