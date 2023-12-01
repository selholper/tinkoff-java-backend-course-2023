package edu.hw8.Task1;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTask1 {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    private static Stream<Arguments> testRequestManagerResponseRunMethod_shouldProcessRequestFromClient() {
        return Stream.of(
            Arguments.of(
                "личности",
                "Клиент: личностиСервер: Не переходи на личности там, где их нет."
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void testRequestManagerResponseRunMethod_shouldProcessRequestFromClient(
        String request,
        String response
    ) {
        Client client = new Client(HOST, PORT);
        startServerExecution();
        client.connect(request);
        assertEquals(
            byteArrayOutputStream
            .toString()
            .replaceAll("[\u0000\\n\\r]", ""),
            response
        );
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(byteArrayOutputStream));
    }

    @SneakyThrows
    private void startServerExecution() {
        Server server = new Server(HOST, PORT);
        Thread thread = new Thread(server::execute);
        thread.start();
        Thread.sleep(1000);
    }
}
