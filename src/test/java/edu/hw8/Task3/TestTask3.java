package edu.hw8.Task3;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class TestTask3 {
    private final Map<String, String> users = Map.of(
        "a.v.petrov", "18e84fa2f549f87a4a3ee3a46e63c3e2",
        "a.s.ivanov", "0765c0f7c8e3df239f846c4f78ed1da6",
        "k.p.maslov", "2354b5f68a4ce8a030eee955639fdd16"
    );
    private final List<User> decodedUsers = List.of(
        new User("a.v.petrov", "abbba"),
        new User("a.s.ivanov", "gvvvg"),
        new User("k.p.maslov", "zbbbz")
    );

    @Test
    void testDecodeMethod_shouldWorkCorrectlyForSingleThreadPasswordRetractor() {
        SingleThreadPasswordRetractor retractor = new SingleThreadPasswordRetractor(users);
        Assertions.assertThat(retractor.decode()).containsExactlyInAnyOrderElementsOf(decodedUsers);
    }

    @Test
    void testDecodeMethod_shouldWorkCorrectlyForMultiThreadPasswordRetractor() {
        MultiThreadPasswordRetractor retractor = new MultiThreadPasswordRetractor(
            users, Runtime.getRuntime().availableProcessors()
        );
        Assertions.assertThat(retractor.decode()).containsExactlyInAnyOrder(
            decodedUsers.get(0),
            decodedUsers.get(1),
            decodedUsers.get(2)
        );
    }
}
