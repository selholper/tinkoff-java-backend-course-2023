package edu.hw11.Task2;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public final class ClassBehaviorReloader {
    private ClassBehaviorReloader() {
    }

    public static void reload() {
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(ArithmeticUtilsDelegate.class))
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
    }

    private final static class ArithmeticUtilsDelegate {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}
