package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.ContactListParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask5 {
    private static Stream<Arguments> testParseContacts_shouldReturnIllegalArgumentException() {
        return Stream.of(
            Arguments.of(Arrays.asList("123", "312"), "ASC"),
            Arguments.of(Arrays.asList("Ilya", "Egor"), "AMD"),
            Arguments.of(Arrays.asList("Ilya", "123"), "DESC"),
            Arguments.of(Arrays.asList("Ilya Egorov", ""), "ASC"),
            Arguments.of(Arrays.asList("Ilya Egorov", "Egor Conor"), "")
        );
    }

    private static Stream<Arguments> testParseContacts_shouldReturnSortedContactList() {
        return Stream.of(
            Arguments.of(
                Arrays.asList("Ivan", "Egor", "Vova"),
                "ASC",
                List.of(
                    new Contact("Egor"),
                    new Contact("Ivan"),
                    new Contact("Vova")
                )
            ),

            Arguments.of(
                Arrays.asList("Ivan", "Egor", "Vova"),
                "DESC",
                List.of(
                    new Contact("Vova"),
                    new Contact("Ivan"),
                    new Contact("Egor")
                )
            ),

            Arguments.of(
                Arrays.asList("Ivan Ivanov", "Egor", "Vova"),
                "ASC",
                List.of(
                    new Contact("Egor"),
                    new Contact("Vova"),
                    new Contact("Ivan", "Ivanov")
                )
            ),

            Arguments.of(
                Arrays.asList("Ivan Ivanov", "Egor", "Vova"),
                "DESC",
                List.of(
                    new Contact("Ivan", "Ivanov"),
                    new Contact("Vova"),
                    new Contact("Egor")
                )
            ),

            Arguments.of(
                Arrays.asList("Ivan Ivanov", "Egor Egorov", "Danil Danilov"),
                "ASC",
                List.of(
                    new Contact("Danil", "Danilov"),
                    new Contact("Egor", "Egorov"),
                    new Contact("Ivan", "Ivanov")
                )
            ),

            Arguments.of(
                Arrays.asList("Ivan Ivanov", "Egor Egorov", "Danil Danilov"),
                "DESC",
                List.of(
                    new Contact("Ivan", "Ivanov"),
                    new Contact("Egor", "Egorov"),
                    new Contact("Danil", "Danilov")
                )
            )
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы метода сортировки контактов для null строки")
    void testParseContacts_shouldReturnNullPointerException(String string) {
        assertThatThrownBy(
            () -> ContactListParser.parseContacts(new ArrayList<>(), string)
        ).isInstanceOf(NullPointerException.class);

        ArrayList <String> list = new ArrayList<>();
        list.add("kjasdflh)");
        list.add("lkskha");
        list.add("akjsdhfkj");

        assertThatThrownBy(
            () -> ContactListParser.parseContacts(list, string)
        ).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("Тестирование работы метода сортировки контактов для null списка")
    void testParseContacts_shouldReturnEmptyContactListForNullOriginList(ArrayList <String> list) {
        assertEquals(ContactListParser.parseContacts(list, ""), new ArrayList<Contact>());
        assertEquals(ContactListParser.parseContacts(list, "ASC"), new ArrayList<Contact>());
        assertEquals(ContactListParser.parseContacts(list, "khgag"), new ArrayList<Contact>());
    }

    @ParameterizedTest
    @EmptySource
    @DisplayName("Тестирование работы метода сортировки контактов для пустого списка")
    void testParseContacts_shouldReturnEmptyContactsListForEmptyOriginalList(ArrayList <String> list){
        assertEquals(ContactListParser.parseContacts(list, ""), new ArrayList<Contact>());
        assertEquals(ContactListParser.parseContacts(list, "ASC"), new ArrayList<Contact>());
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тестирование работы метода сортировки контактов при неправильных входных данных")
    void testParseContacts_shouldReturnIllegalArgumentException(List<String> list,
        String sortMode) {
        assertThatThrownBy(
            () -> ContactListParser.parseContacts(list, sortMode)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Тестирование работы метода сортировки контактов при корректных входных данных")
    void testParseContacts_shouldReturnSortedContactList(List<String> list, String sortMode,
        List<Contact> sortedList) {
        assertEquals(ContactListParser.parseContacts(list, sortMode), sortedList);
    }
}
