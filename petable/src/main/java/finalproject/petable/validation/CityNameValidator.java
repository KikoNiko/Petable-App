package finalproject.petable.validation;

import finalproject.petable.repository.ShelterRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class CityNameValidator implements ConstraintValidator<ValidCityName, String> {
    private static final Set<String> CITY_LIST = Set.of(
            "Sofia", "Plovdiv", "Varna", "Burgas", "Stara Zagora", "Ruse", "Blagoevgrad"
    );
    private ShelterRepository shelterRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return CITY_LIST.contains(value);
    }
}
