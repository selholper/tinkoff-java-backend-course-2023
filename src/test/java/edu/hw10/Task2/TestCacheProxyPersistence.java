package edu.hw10.Task2;

import java.io.File;
import java.nio.file.Files;
import java.util.Properties;
import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class TestCacheProxyPersistence {
    @SneakyThrows
    @Test
    public void testCreateMethod_shouldReturnProxyObject(@TempDir File tempDir) {
        FibonacciCalculator proxy = new FibonacciImplementation();
        proxy = CacheProxy.create(proxy, FibonacciCalculator.class, tempDir.toPath());

        long num = proxy.fib(12);
        long cachedNum = proxy.fib(12);
        Properties properties = new Properties();
        properties.load(Files.newBufferedReader(tempDir.toPath().resolve("fib_long")));

        Assertions.assertThat(cachedNum).isEqualTo(144);
        Assertions.assertThat(properties.getProperty("12;")).isEqualTo(String.valueOf(num));
    }

    @SneakyThrows
    @Test
    public void testCreateMethod_shouldReturnProxyObjectWithoutParameters(@TempDir File tempDir) {
        SomeInterface proxy = new SomeImplementation();
        proxy = CacheProxy.create(proxy, SomeInterface.class, tempDir.toPath());

        long cachedNum = proxy.get();

        Assertions.assertThat(cachedNum).isEqualTo(-1);
        Assertions.assertThat(Files.readString(tempDir.toPath().resolve("get")))
            .isEqualTo("-1");
    }

    interface SomeInterface {
        @Cache(persist = true)
        long get();
    }

    static class SomeImplementation implements SomeInterface {
        @Override
        public long get() {
            return -1;
        }
    }

    static class FibonacciImplementation implements FibonacciCalculator {
        @Override
        @SneakyThrows
        public long fib(long number) {
            if (number < 2) {
                return number;
            }
            return fib(number - 1) + fib(number - 2);
        }
    }
}
