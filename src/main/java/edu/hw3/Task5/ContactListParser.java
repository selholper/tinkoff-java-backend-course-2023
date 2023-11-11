package edu.hw3.Task5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public final class ContactListParser {
    private static final Pattern PATTERN_ONE_NAME =
        Pattern.compile("^[A-Z][a-z]*$");
    private static final Pattern PATTERN_TWO_NAMES =
        Pattern.compile("^[A-Z][a-z]*\\s[A-Z][a-z]*$");

    private ContactListParser() {
    }

    @NotNull
    public static List<Contact> parseContacts(List<String> contacts, String sortMode) {
        Objects.requireNonNull(sortMode);

        List<Contact> sortedContacts = new ArrayList<>();

        if (contacts == null || contacts.isEmpty()) {
            return sortedContacts;
        }

        for (String fullName : contacts) {
            Matcher matcherOneName = PATTERN_ONE_NAME.matcher(fullName);
            Matcher matcherTwoNames = PATTERN_TWO_NAMES.matcher(fullName);

            if (matcherOneName.matches()) {
                sortedContacts.add(new Contact(fullName));
            } else if (matcherTwoNames.matches()) {
                int spaceIndex = fullName.indexOf(' ');
                String name = fullName.substring(0, spaceIndex);
                String surname = fullName.substring(spaceIndex + 1);
                sortedContacts.add(new Contact(name, surname));
            } else {
                throw new IllegalArgumentException("Wrong contact pattern!");
            }
        }

        switch (sortMode) {
            case "ASC" -> sortedContacts.sort(Comparator.naturalOrder());
            case "DESC" -> sortedContacts.sort(Comparator.reverseOrder());
            default -> throw new IllegalArgumentException("Wrong sort mode!");
        }

        return sortedContacts;
    }
}
