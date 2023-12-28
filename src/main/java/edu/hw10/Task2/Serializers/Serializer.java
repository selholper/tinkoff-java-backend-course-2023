package edu.hw10.Task2.Serializers;

public sealed interface Serializer<T> permits SerializerLong {
    String serialize(Object object);

    T deserialize(String string);
}
