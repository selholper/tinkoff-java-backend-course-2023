package edu.hw11.Task2;

import java.util.stream.Stream;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TestChangeBehaviorArithmeticUtilsClassInFlight {
    private static Stream<Arguments> testReloadM_shouldChangeSumMethodInFlight() {
        return Stream.of(
            Arguments.of(1, 2, 2),
            Arguments.of(2, 3, 6),
            Arguments.of(11, 17, 187)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testReloadM_shouldChangeSumMethodInFlight(int a, int b, int res) {
        ByteBuddyAgent.install();
        ClassBehaviorReloader.reload();
        Assertions.assertThat(ArithmeticUtils.sum(a, b)).isEqualTo(res);
    }
}
