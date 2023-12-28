package edu.hw10.Task1;

import edu.hw10.Task1.CustomAnnotations.NotNull;
import edu.hw10.Task1.Generators.CustomGenerators;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class RandomObjectGenerator {
    private final Random random;
    private final Mode mode;
    private final Map<Class<?>, FieldGenerator<?>> generators;

    public RandomObjectGenerator(Random random, Mode mode) {
        this.random = random;
        this.generators = new HashMap<>(CustomGenerators.defaultGenerators());
        this.mode = mode;
    }

    public <T> T nextObject(Class<T> clazz) {
        Constructor<?> constructor = ReflectionUtils.getConstructorWithMaxParams(clazz);
        return nextObject(clazz, constructor);
    }

    public <T> T nextObjectWithSpecifiedConstructor(Class<T> clazz, Class<?>... params) {
        try {
            Constructor<?> constructor = clazz.getConstructor(params);
            return nextObject(clazz, constructor);
        } catch (Exception exception) {
            throw new IllegalArgumentException(
                "Failed to create an instance of with specified constructor " + clazz.getName(), exception);
        }
    }

    public <T> T nextObject(Class<T> clazz, Constructor<?> constructor) {
        Object[] params = generateParams(constructor.getParameters());
        try {
            return clazz.cast(constructor.newInstance(params));
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to create an instance of " + clazz.getName(), exception);
        }
    }

    public <T> T nextObject(Class<T> clazz, String factoryMethod) {
        Method method = ReflectionUtils.getMethod(clazz, factoryMethod);
        Object[] params = generateParams(method.getParameters());
        try {
            return clazz.cast(method.invoke(null, params));
        } catch (Exception exception) {
            throw new IllegalStateException(
                "Failed to create an instance with method " + factoryMethod + " of " + clazz.getName(), exception);
        }
    }

    private Object[] generateParams(Parameter[] parameters) {
        Object[] params = new Object[parameters.length];
        for (int i = 0; i < params.length; i++) {
            FieldGenerator<?> generator = generators.get(parameters[i].getType());
            if (generator == null) {
                if (mode == Mode.GENERATE_NESTED
                    || (mode == Mode.SKIP_NESTED_IF_CAN
                    && parameters[i].isAnnotationPresent(NotNull.class))) {
                    params[i] = nextObject(parameters[i].getType());
                }
            } else {
                params[i] = generator.generate(random, parameters[i].getAnnotations());
            }
        }
        return params;
    }

    public enum Mode {
        SKIP_NESTED_IF_CAN,
        GENERATE_NESTED
    }
}

