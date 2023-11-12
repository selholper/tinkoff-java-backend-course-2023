package edu.hw5.Task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public final class Task2 {
    private Task2() {
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @NotNull
    public static List<LocalDate> findAllFridayTheThirteenthInTheYear(int year) {
        return Arrays
            .stream(Month.values())
            .map(month -> LocalDate.of(year, month, 13))
            .filter(date -> date.getDayOfWeek() == DayOfWeek.FRIDAY)
            .toList();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @NotNull
    public static LocalDate findNextFridayTheThirteen(LocalDate date) {
        LocalDate nextFridayTheThirteen = date;

        do {
            nextFridayTheThirteen = nextFridayTheThirteen.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        } while (nextFridayTheThirteen.getDayOfMonth() != 13);

        return nextFridayTheThirteen;
    }
}
