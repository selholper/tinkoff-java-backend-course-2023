package edu.hw2.Task4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.function.Supplier;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestTask4 {
    private static CallingInfo method1() {
        return CallingInfo.callingInfo();
    }

    private static CallingInfo method2() {
        return method1();
    }

    private static CallingInfo method3() {
        return method2();
    }

    private static CallingInfo method4() {
        return method3();
    }

    private static CallingInfo method5() {
        return method4();
    }

    private static Stream <Supplier <CallingInfo>> methods() {
        return Stream.of(
            TestTask4::method1,
            TestTask4::method2,
            TestTask4::method3,
            TestTask4::method4,
            TestTask4::method5
        );
    }

    @ParameterizedTest
    @MethodSource("methods")
    void testCallingInfoMethods(Supplier <CallingInfo> supplier) {
        assertThat(supplier.get())
            .extracting("methodName", "className")
            .containsExactly("method1", "edu.hw2.Task4.TestTask4");

    }
}
