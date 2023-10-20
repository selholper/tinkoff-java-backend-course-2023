package edu.hw2;

import edu.hw2.Task1.Expression;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask1 {
    @ParameterizedTest
    @DisplayName("Тестирование работы класса (записи) константы")
    @ValueSource(doubles = {0.01, 0.1, -45612.21, 0, 541})
    void testConstantRecord(double number) {
        var constant = new Expression.Constant(number);
        Assertions.assertEquals(number, constant.evaluate());
    }

    @ParameterizedTest
    @DisplayName("Тестирование работы класса (записи) унарного отрицания на корректных данных")
    @ValueSource(doubles = {0.01, 0.1, -45612.21, 0, 541})
    void testCorrectNegateRecord(double number) {
        var constant = new Expression.Constant(number);
        var negate = new Expression.Negate(constant);

        Assertions.assertEquals(-number, negate.evaluate());
    }

    @Test
    @DisplayName("Тестирование работы класса (записи) унарного отрицания на некорректных данных")
    void testInCorrectNegateRecord() {
        assertThatThrownBy(
            () -> new Expression.Negate(null).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @DisplayName("Тестирование работы класса (записи) возведения в степень на корректных данных")
    @ValueSource(doubles = {0.01, 0.1, -45612.21, 0, 541})
    void testCorrectExponentRecord(double number) {
        var constant = new Expression.Constant(number);

        for (int power = -10; power < 11; ++power) {
            var exponent = new Expression.Exponent(constant, power);
            Assertions.assertEquals(Math.pow(number, power), exponent.evaluate());
        }
    }

    @Test
    @DisplayName("Тестирование работы класса (записи) возведения в степень на некорректных данных")
    void testInCorrectExponentRecord() {
        assertThatThrownBy(
            () -> new Expression.Exponent(null, 0.1).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @DisplayName("Тестирование работы класса (записи) сложения на корректных данных")
    @ValueSource(doubles = {0.01, 0.1, -45612.21, 0, 541})
    void testCorrectAdditionRecord(double number) {
        Random random = new Random(100);
        // Псевдослучайное вещественное число от 0 до 100
        double numberRandom = random.nextDouble();
        var constant1 = new Expression.Constant(number);
        var constant2 = new Expression.Constant(numberRandom);
        var addition = new Expression.Addition(constant1, constant2);

        Assertions.assertEquals(number + numberRandom, addition.evaluate());
    }

    @Test
    @DisplayName("Тестирование работы класса (записи) сложения на некорректных данных")
    void testInCorrectAdditionRecord() {
        Random random = new Random(100);
        // Псевдослучайное вещественное число от 0 до 100
        var constant = new Expression.Constant(random.nextDouble());

        assertThatThrownBy(
            () -> new Expression.Addition(null, constant).evaluate()
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> new Expression.Addition(constant, null).evaluate()
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> new Expression.Addition(null, null).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @DisplayName("Тестирование работы класса (записи) сложения на корректных данных")
    @ValueSource(doubles = {0.01, 0.1, -45612.21, 0, 541})
    void testCorrectMultiplicationRecord(double number) {
        Random random = new Random(100);
        // Псевдослучайное вещественное число от 0 до 100
        double numberRandom = random.nextDouble();
        var constant1 = new Expression.Constant(number);
        var constant2 = new Expression.Constant(numberRandom);
        var multiplication = new Expression.Multiplication(constant1, constant2);

        Assertions.assertEquals(number * numberRandom, multiplication.evaluate());
    }

    @Test
    @DisplayName("Тестирование работы класса (записи) сложения на некорректных данных")
    void testInCorrectMultiplicationRecord() {
        Random random = new Random(100);
        // Псевдослучайное вещественное число от 0 до 100
        var constant = new Expression.Constant(random.nextDouble());

        assertThatThrownBy(
            () -> new Expression.Multiplication(null, constant).evaluate()
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> new Expression.Multiplication(constant, null).evaluate()
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> new Expression.Multiplication(null, null).evaluate()
        ).isInstanceOf(NullPointerException.class);
    }
}
