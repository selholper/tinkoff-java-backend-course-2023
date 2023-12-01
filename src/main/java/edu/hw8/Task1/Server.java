package edu.hw8.Task1;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import lombok.SneakyThrows;

public class Server {
    private static final int THREADS_NUMBER = 8;
    private static final int MAX_CONNECTIONS_NUMBER = 3;
    private final ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
    private final Semaphore semaphore = new Semaphore(MAX_CONNECTIONS_NUMBER, true);
    private final InetSocketAddress inetSocketAddress;

    public Server(String host, int port) {
        inetSocketAddress = new InetSocketAddress(host, port);
    }

    @SneakyThrows
    public void execute() {
        try (Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(inetSocketAddress);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (serverSocketChannel.isOpen()) {
                selector.select();
                for (SelectionKey selectionKey : selector.keys()) {
                    if (semaphore.tryAcquire()) {
                        if (semaphore.tryAcquire() && selectionKey.isAcceptable()) {
                            startExecution(serverSocketChannel.accept());
                        } else if (semaphore.tryAcquire()) {
                            selector.selectedKeys().remove(selectionKey);
                        }
                    }
                }
            }
        }
    }

    public void startExecution(SocketChannel socketChannel) {
        executorService.execute(new RequestManagerResponse(socketChannel, semaphore));
    }
}
