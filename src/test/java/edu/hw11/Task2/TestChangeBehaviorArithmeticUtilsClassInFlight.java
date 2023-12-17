package edu.hw11.Task2;

import java.util.stream.Stream;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestChangeBehaviorArithmeticUtilsClassInFlight {
    private static Stream<Arguments> testMethodSum_shouldDoProductInstead() {
        return Stream.of(
            Arguments.of(1, 2, 2),
            Arguments.of(2, 3, 6),
            Arguments.of(11, 4999, 54989)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testMethodSum_shouldDoProductInstead(int a, int b, int res) {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(ArithmeticUtilsSubstitution.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
            .getLoaded();

        int actual = ArithmeticUtils.sum(a, b);
        assertThat(actual).isEqualTo(res);
    }

    private static final class ArithmeticUtilsSubstitution {
        public static int product(int a, int b) {
            return a * b;
        }
    }
}
