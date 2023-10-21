package edu.hw2.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class TestTask3 {
    @Test
    @DisplayName("Тестирование метода getConnection класса FaultyConnectionManager")
    void testGetConnectionOfFaultyConnectionManager() {
        Connection unknownConnection = new FaultyConnectionManager().getConnection();
        assertThat(unknownConnection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Тестирование метода getConnection класса DefaultConnectionManager, "
        + "когда он возвращает StableConnection")
    void testGetStableConnectionOfDefaultConnectionManager() {
        int seed = -1;
        Random random = new Random(seed);
        Connection stableConnection = new DefaultConnectionManager(random).getConnection();
        assertThat(stableConnection).isInstanceOf(StableConnection.class);
    }

    @Test
    @DisplayName("Тестирование метода getConnection класса DefaultConnectionManager, "
        + "когда он возвращает FaultyConnection")
    void testGetFaultyConnectionOfDefaultConnectionManager() {
        int seed = 0;
        Random random = new Random(seed);
        Connection stableConnection = new DefaultConnectionManager(random).getConnection();
        assertThat(stableConnection).isInstanceOf(FaultyConnection.class);
    }

    @Test
    @DisplayName("Тестирование конструктора по умолчанию класса DefaultConnectionManager")
    void testDefaultConstructorOfDefaultConnectionManager() {
        assertDoesNotThrow(
            () -> new DefaultConnectionManager()
        );
    }

    @Test
    @DisplayName("Тестирование метода close для StableConnection")
    void testCloseStableConnection() {
        StableConnection stableConnection = new StableConnection();
        assertDoesNotThrow(
            () -> stableConnection.execute("command")
        );
        stableConnection.close();
    }

    @Test
    @DisplayName("Тестирование метода close для FaultyConnection")
    void testCloseFaultyConnection() {
        FaultyConnection faultyConnection = new FaultyConnection();
        try {
            faultyConnection.execute("");
            faultyConnection.close();
        } catch (ConnectionException exception) {
            faultyConnection.close();
        }
    }

    @Test
    @DisplayName("Тестирование конструктора класса PopularCommandExecutor при неверных аргументах")
    void testInvalidArgumentInConstructorOfPopularCommandExecutor() {
        assertThatThrownBy(
            () -> new PopularCommandExecutor(null, 0)
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> new PopularCommandExecutor(null, 1)
        ).isInstanceOf(NullPointerException.class);

        assertThatThrownBy(
            () -> new PopularCommandExecutor(new DefaultConnectionManager(), 0)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
            () -> new PopularCommandExecutor(new FaultyConnectionManager(), 0)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Тестирование метода tryExecute класса PopularCommandExecutor, "
        + "когда он возвращает исключение ConnectionException"
        + "при использовании FaultyConnectionManager")
    void testTryExecuteWithFaultyConnectionManager() {
        int seed = 0;
        Random random = new Random(seed);
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new FaultyConnectionManager(random), 1);
        assertThatThrownBy(popularCommandExecutor::updatePackages).isInstanceOf(ConnectionException.class);
    }

    @Test
    @DisplayName("Тестирование метода tryExecute класса PopularCommandExecutor, "
        + "когда он не возвращает исключение ConnectionException")
    void testTryExecuteWithoutException() {
        int seed = 1;
        Random random = new Random(seed);
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(random), 100);
        assertDoesNotThrow(popularCommandExecutor::updatePackages);
    }

    @Test
    @DisplayName("Тестирование метода tryExecute класса PopularCommandExecutor,"
        + " когда он возвращает исключение ConnectionException"
        + "при использовании DefaultConnectionManager")
    void testTryExecuteWithDefaultConnectionManager() {
        int seed = 3;
        Random random = new Random(seed);
        PopularCommandExecutor popularCommandExecutor =
            new PopularCommandExecutor(new DefaultConnectionManager(random), 1);
        assertThatThrownBy(popularCommandExecutor::updatePackages).isInstanceOf(ConnectionException.class);
    }

    @Test
    @DisplayName("Тестирование метода execute класса FaultyConnection")
    void testExecuteOfFaultyConnection() {
        FaultyConnection faultyConnection = new FaultyConnection(new Random(-1));

        assertDoesNotThrow(
            () -> faultyConnection.execute("")
        );
        faultyConnection.close();
    }
}
