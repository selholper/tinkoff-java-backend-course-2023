package edu.hw11.Task1;

import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TestByteBuddyHelloWorldClass {
    @Test
    @SneakyThrows
    void testToStringMethod_shouldPrintHelloWorld() {
        Class<?> helloWorldClass = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.named("toString"))
            .intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        assertThat(helloWorldClass
            .getDeclaredConstructor()
            .newInstance()
            .toString()
        ).isEqualTo("Hello, ByteBuddy!");
    }
}
