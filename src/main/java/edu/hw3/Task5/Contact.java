package edu.hw3.Task5;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record Contact(@NotNull String name, @Nullable String surname) implements Comparable<Contact> {
    public Contact(String name) {
        this(name, null);
    }

    @Override
    public int compareTo(@NotNull Contact anotherContact) {
        if (this.surname == null && anotherContact.surname == null) {
            return this.name.compareTo(anotherContact.name);
        } else if (this.surname == null) {
            return -1;
        } else if (anotherContact.surname == null) {
            return 1;
        }

        return this.surname.compareTo(anotherContact.surname);
    }
}

