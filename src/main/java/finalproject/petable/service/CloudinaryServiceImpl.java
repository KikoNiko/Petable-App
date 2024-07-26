package finalproject.petable.service;

import com.cloudinary.Cloudinary;
import finalproject.petable.model.entity.Image;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;
    private final ImageService imageService;

    public CloudinaryServiceImpl(Cloudinary cloudinary, ImageService imageService) {
        this.cloudinary = cloudinary;
        this.imageService = imageService;
    }

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String fileName = Objects.requireNonNull(multipartFile.getOriginalFilename());
        String resultUrl = cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Map.of("public_id", fileName))
                .get("url")
                .toString();
        Image image = new Image(resultUrl, fileName);
        imageService.save(image);
        return resultUrl;
    }
}
