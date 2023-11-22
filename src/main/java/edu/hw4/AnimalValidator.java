package edu.hw4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnimalValidator {
    private static final Pattern PATTERN = Pattern.compile("^[A-Z][a-z]+( [A-Z][a-z]+)*$");
    private final List<Function<Animal, Optional<AnimalValidationError>>> validations = new ArrayList<>();

    public AnimalValidator() {
        registerValidation(BasicAnimalValidations::validateName);
        registerValidation(BasicAnimalValidations::validateType);
        registerValidation(BasicAnimalValidations::validateSex);
        registerValidation(BasicAnimalValidations::validateAge);
        registerValidation(BasicAnimalValidations::validateHeight);
        registerValidation(BasicAnimalValidations::validateWeight);
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

        private static Optional<AnimalValidationError> validateName(Animal animal) {
            final String name = animal.name();

            if (animal.name().isBlank()) {
                return Optional.of(new AnimalValidationError(
                    name,
                    "Name must be not blank"
                ));
            }

            Matcher matcher = PATTERN.matcher(animal.name());
            if (!matcher.matches()) {
                return Optional.of(new AnimalValidationError(
                    name,
                    "Name must match the pattern \"^[A-Z][a-z]+( [A-Z][a-z]+)*$\""
                ));
            }

            return Optional.empty();
        }

        private static Optional<AnimalValidationError> validateType(Animal animal) {
            if (animal.type() == null) {
                return Optional.of(new AnimalValidationError("Type", "Type must be not null"));
            }

            return Optional.empty();
        }

        private static Optional<AnimalValidationError> validateSex(Animal animal) {
            if (animal.sex() == null) {
                return Optional.of(new AnimalValidationError("Sex", "Sex must be not null"));
            }

            return Optional.empty();
        }

        private static Optional<AnimalValidationError> validateAge(Animal animal) {
            if (animal.age() < 0) {
                return Optional.of(new AnimalValidationError("Age", "Age cannot be negative"));
            }

            return Optional.empty();
        }

        public static Optional<AnimalValidationError> validateHeight(Animal animal) {
            if (animal.height() < 0) {
                return Optional.of(new AnimalValidationError("Height", "Height cannot be negative"));
            }

            return Optional.empty();
        }

        private static Optional<AnimalValidationError> validateWeight(Animal animal) {
            if (animal.weight() < 0) {
                return Optional.of(new AnimalValidationError("Weight", "Weight cannot be negative"));
            }

            return Optional.empty();
        }
    }
}
