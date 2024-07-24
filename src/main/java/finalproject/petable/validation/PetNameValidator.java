package finalproject.petable.validation;

import finalproject.petable.repository.PetRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PetNameValidator implements ConstraintValidator<ValidPetName, String> {

    private final PetRepository petRepository;

    public PetNameValidator(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return petRepository.findByName(name).isPresent();
    }
}
