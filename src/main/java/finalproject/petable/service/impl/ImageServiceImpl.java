package finalproject.petable.service.impl;

import finalproject.petable.model.entity.BaseUser;
import finalproject.petable.model.entity.Image;
import finalproject.petable.model.entity.Pet;
import finalproject.petable.repository.ImageRepository;
import finalproject.petable.repository.PetRepository;
import finalproject.petable.repository.UserRepository;
import finalproject.petable.service.ImageService;
import finalproject.petable.service.exception.PetNotFoundException;
import finalproject.petable.service.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;
    public ImageServiceImpl(ImageRepository imageRepository, UserRepository userRepository, PetRepository petRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.petRepository = petRepository;
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void assignImageToUser(String imageUrl, String username) {
        Optional<BaseUser> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User " + username + " not found");
        }
        BaseUser user = optionalUser.get();
        user.setProfileImageUrl(imageUrl);
        userRepository.save(user);
    }

    @Override
    public void assignImageToPet(Image image, Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException("Pet with id " + id + " not found", id);
        }
        Pet pet = optionalPet.get();
        pet.getImages().add(image);
        petRepository.save(pet);
    }

    @Override
    public Image getByName(String name) {
        return imageRepository.findByName(name).orElse(null);
    }
}
