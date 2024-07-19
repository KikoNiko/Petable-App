package finalproject.petable.service.impl;

import finalproject.petable.model.entity.Image;
import finalproject.petable.repository.ImageRepository;
import finalproject.petable.service.ImageService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findByOrderById();
    }

    @Override
    public Optional<Image> findImageById(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return imageRepository.existsById(id);
    }
}
