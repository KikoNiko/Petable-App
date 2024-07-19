package finalproject.petable.web.controller;

import finalproject.petable.model.entity.Image;
import finalproject.petable.service.CloudinaryService;
import finalproject.petable.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cloudinary")
public class ImageController {
    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    public ImageController(CloudinaryService cloudinaryService, ImageService imageService) {
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Image>> allImagesList() {
        List<Image> allImagesList = imageService.getAllImages();
        return new ResponseEntity<>(allImagesList, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>("File is not a valid image!", HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Image image = new Image((String) result.get("original_filename"),
                (String) result.get("url"),
                (String) result.get("public_id"));

        imageService.save(image);

        return new ResponseEntity<>("Image saved successfully! ", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Optional<Image> optionalImage = imageService.findImageById(id);
        if (optionalImage.isEmpty()) {
            return new ResponseEntity<>("Image not found!", HttpStatus.NOT_FOUND);
        }
        Image image = optionalImage.get();
        String cloudinaryImageId = image.getImageId();
        try {
            cloudinaryService.delete(cloudinaryImageId);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to delete image!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        imageService.delete(id);
        return new ResponseEntity<>("Image deleted!", HttpStatus.OK);
    }
}
