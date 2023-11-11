package edu.hw3.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;

public final class Task3 {
    private Task3() {
    }

    @NotNull
    public static <T> Map<T, Integer> freqDict(List<T> list) {
        Objects.requireNonNull(list);

        HashMap<T, Integer> hashMap = new HashMap<>();

        for (var element : list) {
            hashMap.merge(element, 1, Integer::sum);
        }

        return hashMap;
    }
}

