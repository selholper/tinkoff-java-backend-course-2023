package edu.hw10.Task2.Serializers;

import java.util.HashMap;
import java.util.Map;

public final class Serializers {
    private Serializers() {
    }

    public static Map<Class<?>, Serializer<?>> defaultSerializers() {
        Map<Class<?>, Serializer<?>> serializers = new HashMap<>();
        serializers.put(Long.class, new SerializerLong());
        serializers.put(long.class, new SerializerLong());
        return serializers;
    }
}

