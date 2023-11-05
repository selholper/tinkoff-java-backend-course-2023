package edu.hw4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class AnimalValidator {
    static final int MIN_NAME_SIZE = 3;

    private final List<Function<Animal, Optional<AnimalValidationError>>> validations = new ArrayList<>();

    public AnimalValidator() {
        registerValidation(BasicAnimalValidations::validateAge);
        registerValidation(BasicAnimalValidations::validateWeight);
        registerValidation(BasicAnimalValidations::validateHeight);
        registerValidation(BasicAnimalValidations::validateSex);
        registerValidation(BasicAnimalValidations::validateType);
        registerValidation(BasicAnimalValidations::validateName);
    }

    private void registerValidation(Function<Animal, Optional<AnimalValidationError>> validation) {
        validations.add(validation);
    }

    public Set<AnimalValidationError> validateAll(Animal animal) {
        final Set<AnimalValidationError> animalValidationErrors = new HashSet<>();
        for (var validation : validations) {
            Optional<AnimalValidationError> animalValidationError = validation.apply(animal);
            animalValidationError.ifPresent(animalValidationErrors::add);
        }

        return animalValidationErrors;
    }

    private final static class BasicAnimalValidations {
        private BasicAnimalValidations() {
        }

        private static Optional<AnimalValidationError> validateAge(Animal animal) {
            if (animal.age() < 0) {
                return Optional.of(new AnimalValidationError("Age", "Age must be positive"));
            }

            return Optional.empty();
        }

        private static Optional<AnimalValidationError> validateWeight(Animal animal) {
            if (animal.weight() < 0) {
                return Optional.of(new AnimalValidationError("Weight", "Weight must be positive"));
            }

            return Optional.empty();
        }

        public static Optional<AnimalValidationError> validateHeight(Animal animal) {
            if (animal.height() < 0) {
                return Optional.of(new AnimalValidationError("Height", "Height must be positive"));
            }

            return Optional.empty();
        }

        private static Optional<AnimalValidationError> validateSex(Animal animal) {
            if (animal.sex() == null) {
                return Optional.of(new AnimalValidationError("Sex", "Sex must be not null"));
            }

            return Optional.empty();
        }

        private static Optional<AnimalValidationError> validateType(Animal animal) {
            if (animal.type() == null) {
                return Optional.of(new AnimalValidationError("Type", "Type must be not null"));
            }

            return Optional.empty();
        }

        private static Optional<AnimalValidationError> validateName(Animal animal) {
            if (animal.name().isBlank() || animal.name().length() < MIN_NAME_SIZE) {
                return Optional.of(new AnimalValidationError(
                    "name",
                    "Name must be not empty and contains minimum 3 symbols"
                ));
            }

            return Optional.empty();
        }
    }
}
