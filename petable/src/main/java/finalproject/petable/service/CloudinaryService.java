package finalproject.petable.service;

import finalproject.petable.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {
    Image upload(MultipartFile multipartFile) throws IOException;
}
