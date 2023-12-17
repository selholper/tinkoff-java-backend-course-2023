package edu.hw11.Task3;

import lombok.SneakyThrows;
import net.bytebuddy.jar.asm.Label;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestDynamicFibonacciCalculatorClass {
    private static Stream<Arguments> test_shouldCreateNewFibonacciCalculatorClass() {
        return Stream.of(
            Arguments.of(1, 1),
            Arguments.of(10, 55),
            Arguments.of(12, 144)
        );
    }

    @ParameterizedTest
    @MethodSource
    @SneakyThrows
    void test_shouldCreateNewFibonacciCalculatorClass(int n, int res) {
        Class<?> createdClass = createFibonacciCalculatorClass();
        long actual = (long) createdClass.getDeclaredMethod("getFib", int.class)
            .invoke(createdClass.getDeclaredConstructor().newInstance(), n);
        assertThat(actual).isEqualTo(res);
    }

    private Class<?> createFibonacciCalculatorClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("DynamicFibCalculator")
            .defineMethod("getFib", long.class, Modifier.PUBLIC)
            .withParameters(int.class)
            .intercept(new Implementation() {
                @Override
                public @NotNull ByteCodeAppender appender(@NotNull Target target) {
                    return (mv, context, methodDescription) -> {
                        Label l1 = new Label();

                        mv.visitCode();
                        // if (n < 2)
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitJumpInsn(Opcodes.IF_ICMPGE, l1);

                        //  return n;
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.I2L);
                        mv.visitInsn(Opcodes.LRETURN);

                        //return getFib(n - 1) + getFib(n - 2);
                        mv.visitLabel(l1);
                        mv.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                            "DynamicFibCalculator", "getFib", "(I)J");
                        mv.visitVarInsn(Opcodes.ALOAD, 0);
                        mv.visitVarInsn(Opcodes.ILOAD, 1);
                        mv.visitInsn(Opcodes.ICONST_2);
                        mv.visitInsn(Opcodes.ISUB);
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                            "DynamicFibCalculator", "getFib", "(I)J");
                        mv.visitInsn(Opcodes.LADD);
                        mv.visitInsn(Opcodes.LRETURN);
                        mv.visitEnd();
                        return new ByteCodeAppender.Size(5, 2);
                    };
                }

                @Override
                public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
                    return instrumentedType;
                }
            })
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();
    }
}
