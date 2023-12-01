package edu.hw8.Task1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;

public class Client {
    private static final int BUFFER_SIZE = 256;
    private final InetSocketAddress inetSocketAddress;

    public Client(String host, int port) {
        inetSocketAddress = new InetSocketAddress(host, port);
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    @SneakyThrows
    public void connect(String message) {
        try (SocketChannel socketChannel = SocketChannel.open(inetSocketAddress)) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));

            while (byteBuffer.hasRemaining()) {
                socketChannel.write(byteBuffer);
            }

            byteBuffer.flip();
            byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
            int currentByte;
            while ((currentByte = socketChannel.read(byteBuffer)) != -1) {
                if (currentByte != 0) {
                    System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8));
                    break;
                }
            }
        }
    }
}
