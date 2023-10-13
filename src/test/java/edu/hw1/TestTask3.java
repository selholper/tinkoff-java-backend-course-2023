package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestTask3 {
    @Test
    @DisplayName("Проверка вложенности первого массива во второй для корректных массивов")
    void testIsNestableCorrectArrays() {
        // given
        int[] array1 = {1, 2, 3};
        int[] array2 = {1};
        // when
        boolean isArray1NestedInArray2 = Task3.isNestable(array1, array2);
        // then
        assertThat(isArray1NestedInArray2).isFalse();

        // given
        array1 = new int[]{2};
        array2 = new int[]{1, 2, 3};
        // when
        isArray1NestedInArray2 = Task3.isNestable(array1, array2);
        // then
        assertThat(isArray1NestedInArray2).isTrue();

        // given
        array1 = new int[]{1, 2, 3, 4};
        array2 = new int[]{0, 6};
        // when
        isArray1NestedInArray2 = Task3.isNestable(array1, array2);
        // then
        assertThat(isArray1NestedInArray2).isTrue();

        // given
        array1 = new int[]{3, 1};
        array2 = new int[]{4, 0};
        // when
        isArray1NestedInArray2 = Task3.isNestable(array1, array2);
        // then
        assertThat(isArray1NestedInArray2).isTrue();

        // given
        array1 = new int[]{9, 9, 8};
        array2 = new int[]{8, 9};
        // when
        isArray1NestedInArray2 = Task3.isNestable(array1, array2);
        // then
        assertThat(isArray1NestedInArray2).isFalse();

        // given
        array1 = new int[]{1, 2, 3, 4};
        array2 = new int[]{2, 3};
        // when
        isArray1NestedInArray2 = Task3.isNestable(array1, array2);
        // then
        assertThat(isArray1NestedInArray2).isFalse();
    }

    @Test
    @DisplayName("Проверка исключений для некорректно заданных массивов")
    void testIsNestableIncorrectArrays() {
        assertThatThrownBy(
            () -> Task3.isNestable(null, null)
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task3.isNestable(null, new int[]{})
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task3.isNestable(new int[]{}, null)
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task3.isNestable(new int[]{1, 2, 3}, null)
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task3.isNestable(null, new int[]{1, 2, 3})
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> Task3.isNestable(new int[]{}, new int[]{})
        ).isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(
            () -> Task3.isNestable(new int[]{}, new int[]{1, 2, 3})
        ).isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(
            () -> Task3.isNestable(new int[]{1, 2, 3}, new int[]{})
        ).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
