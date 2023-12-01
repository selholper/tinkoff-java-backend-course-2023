package edu.hw8.Task1;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.Semaphore;
import lombok.SneakyThrows;

public class RequestManagerResponse implements Runnable {
    private static final int BUFFER_SIZE = 256;
    private static final String CLIENT_PATTERN = "Клиент: ";
    private static final String SERVER_PATTERN = "Сервер: ";
    private static final Map<String, String> RESPONSES = Map.of(
        "личности",
        "Не переходи на личности там, где их нет.",
        "оскорбления",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами.",
        "глупый",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "интеллект",
        "Чем ниже интеллект, тем громче оскорбления."
    );
    private final SocketChannel socketChannel;
    private final Semaphore semaphore;

    public RequestManagerResponse(SocketChannel socketChannel, Semaphore semaphore) {
        this.socketChannel = socketChannel;
        this.semaphore = semaphore;
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @Override
    @SneakyThrows
    public void run() {
        try (Selector selector = Selector.open()) {
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            while (socketChannel.isOpen()) {
                selector.select();
                for (SelectionKey selectionKey : selector.keys()) {
                    if (selectionKey.isReadable()) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
                        int bytes = socketChannel.read(byteBuffer);
                        if (bytes > 0) {
                            String request = new String(byteBuffer.array(), StandardCharsets.UTF_8);
                            System.out.println(CLIENT_PATTERN + request);
                            String responseMessage;
                            responseMessage = RESPONSES.getOrDefault(
                                request.trim(),
                                "Ответа нет."
                            );
                            ByteBuffer response =
                                ByteBuffer.wrap((SERVER_PATTERN
                                    + responseMessage).getBytes(StandardCharsets.UTF_8));
                            while (response.hasRemaining()) {
                                socketChannel.write(response);
                            }
                            byteBuffer.flip();
                            socketChannel.close();
                        }
                    }
                }
            }
            semaphore.release();
        }
    }
}
