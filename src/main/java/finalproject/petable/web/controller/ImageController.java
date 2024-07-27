package finalproject.petable.web.controller;

import finalproject.petable.model.AppUserDetails;
import finalproject.petable.model.entity.Image;
import finalproject.petable.service.CloudinaryService;
import finalproject.petable.service.ImageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("images")
public class ImageController {
    private final CloudinaryService cloudinaryService;
    private final ImageService imageService;

    public ImageController(CloudinaryService cloudinaryService, ImageService imageService) {
        this.cloudinaryService = cloudinaryService;
        this.imageService = imageService;
    }

    @PostMapping( "/upload")
    public String upload(@RequestParam("profilePic")MultipartFile multipartFile,
                         @AuthenticationPrincipal AppUserDetails userDetails) throws IOException {
        Image image = cloudinaryService.upload(multipartFile);
        imageService.assignImageToUser(image.getUrl(), userDetails.getUsername());
        return "redirect:/home";
    }

}
