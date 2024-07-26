package finalproject.petable.service;

import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.Image;
import finalproject.petable.repository.ImageRepository;
import finalproject.petable.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService{
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    public ImageServiceImpl(ImageRepository imageRepository, UserRepository userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void assignImageToUser(String imageUrl, String username) {
        BaseUser user = userRepository.findByUsername(username).orElse(null);
        user.setProfileImageUrl(imageUrl);
        userRepository.save(user);
    }
}
