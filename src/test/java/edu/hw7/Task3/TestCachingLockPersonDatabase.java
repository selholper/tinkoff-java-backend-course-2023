package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestCachingLockPersonDatabase {
    @Test
    @SneakyThrows
    void testCachingLockPersonDatabase_shouldWorkCorrectly() {
        PersonDatabase personDatabase = new CachingLockPersonDatabase();

        Person person = new Person(1, "Ivan", "St. Petersburg", "+79876543210");

        assertThat(person.name()).isEqualTo("Ivan");
        assertThat(person.address()).isEqualTo("St. Petersburg");
        assertThat(person.phoneNumber()).isEqualTo("+79876543210");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> personDatabase.add(person));

        List<Future<List<Person>>> results =  new ArrayList<>();
        Future<List<Person>> listFuture = executorService.submit(
            () -> personDatabase.findByName("Ivan")
        );
        results.add(listFuture);
        listFuture = executorService.submit(
            () -> personDatabase.findByAddress("St. Petersburg")
        );
        results.add(listFuture);
        listFuture = executorService.submit(
            () -> personDatabase.findByPhoneNumber("+79876543210")
        );
        results.add(listFuture);

        executorService.shutdown();
        Assertions.assertAll(
            () -> assertThat(results.get(0).get()).contains(person),
            () -> assertThat(results.get(1).get()).contains(person),
            () -> assertThat(results.get(2).get()).contains(person)
        );
    }
}
