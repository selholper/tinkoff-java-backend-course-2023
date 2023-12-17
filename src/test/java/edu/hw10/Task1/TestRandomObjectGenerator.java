package edu.hw10.Task1;

import edu.hw10.Task1.CustomAnnotations.Max;
import edu.hw10.Task1.CustomAnnotations.Min;
import edu.hw10.Task1.CustomAnnotations.NotNull;
import java.util.Random;
import lombok.Getter;
import lombok.ToString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestRandomObjectGenerator {
    @Test
    public void nextObject_shouldReturnObjectWithRandomValuesForMixedClasses() {
        RandomObjectGenerator generator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );

        MixedClasses mixedClasses = generator.nextObject(MixedClasses.class);
        Assertions.assertThat(mixedClasses.valueInteger).isNotNull();
        Assertions.assertThat(mixedClasses.valueLong).isNotNull();
        Assertions.assertThat(mixedClasses.valueDouble).isNotNull();
        Assertions.assertThat(mixedClasses.valueString).isNotNull();
        Assertions.assertThat(mixedClasses.valueBoolean).isNotNull();
        Assertions.assertThat(mixedClasses.valueByte).isNotNull();
        Assertions.assertThat(mixedClasses.valueFloat).isNotNull();
        Assertions.assertThat(mixedClasses.valueDoubleTwo).isNotNull();
    }

    @Test
    public void nextObjectWithSpecifiedConstructor_shouldReturnObjectWithRandomValues() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );
        User user = randomObjectGenerator.nextObjectWithSpecifiedConstructor(User.class, Person.class);
        Assertions.assertThat(user.person).isNotNull();
        Assertions.assertThat(user.login).isEqualTo("help");
    }

    @Test
    public void nextObject_shouldReturnObjectWithRandomValues() {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );
        User user = randomObjectGenerator.nextObject(User.class, "createSomeUser");
        Assertions.assertThat(user.login).isNotNull();
        Assertions.assertThat(user.person).isNotNull();
        Assertions.assertThat(user.person.age).isGreaterThan(0).isLessThan(100);
    }

    @Test
    public void nextObjectWithSpecifiedConstructor_shouldThrowIllegalArgumentException_whenConstructorIsNotFound() {
        RandomObjectGenerator generator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );
        Assertions.assertThatThrownBy(
            () -> generator.nextObjectWithSpecifiedConstructor(User.class, String.class)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void nextObject_shouldThrowIllegalArgumentException_whenFactoryMethodIsdNotFound() {
        RandomObjectGenerator generator = new RandomObjectGenerator(
            new Random(),
            RandomObjectGenerator.Mode.SKIP_NESTED_IF_CAN
        );

        Assertions.assertThatThrownBy(
            () -> generator.nextObject(User.class, "inMyMindMethod")
        ).isInstanceOf(IllegalArgumentException.class);
    }


    @Getter
    @ToString
    public static class User {

        private final Person person;
        private final String login;

        private User(Person person, String login) {
            this.person = person;
            this.login = login;
        }

        public User(@NotNull Person person) {
            this(person, "help");
        }

        public static User createSomeUser(@NotNull Person person, String login) {
            return new User(person, login);
        }
    }

    public record Person(String name, @Min(1) @Max(99) Integer age) {
    }

    public record MixedClasses(
        Integer valueInteger,
        Long valueLong,
        Double valueDouble,
        String valueString,
        Boolean valueBoolean,
        Byte valueByte,
        Float valueFloat,
        Double valueDoubleTwo) {
    }
}
