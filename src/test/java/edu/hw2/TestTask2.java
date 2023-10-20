package edu.hw2;

import edu.hw2.Task2.Square;
import edu.hw2.Task2.Rectangle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask2 {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @DisplayName("Проверка выполнения принципа подстановки")
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect = rect.setHeight(10);
        rect = rect.setWidth(20);

        assertThat(rect.area()).isEqualTo(200);
    }

    @ParameterizedTest
    @DisplayName("Проверка метода класса Square")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void testSquareMethod(int side) {
        Square square = new Square();
        square = square.setSide(side);

        assertThat(square.area()).isEqualTo(side * side);
    }

    @Test
    @DisplayName("Проверка работы классов Rectangle и Square на некорректных данных")
    void testSquareMethodIncorrect() {
        assertThatThrownBy(
            () -> new Square(-1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new Rectangle(-1, 1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new Rectangle(1, -1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new Rectangle(-1, -1)
        ).isInstanceOf(IllegalArgumentException.class);

        Rectangle rectangle = new Rectangle();
        Square square = new Square();

        assertThatThrownBy(
            () -> rectangle.setHeight(-1)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> rectangle.setWidth(-3)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> square.setHeight(-5)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> square.setWidth(-3)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> square.setSide(-1)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}

