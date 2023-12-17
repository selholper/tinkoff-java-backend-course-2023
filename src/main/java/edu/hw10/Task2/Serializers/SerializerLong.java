package edu.hw10.Task2.Serializers;

public final class SerializerLong implements Serializer<Long> {
    @Override
    public String serialize(Object object) {
        return object.toString();
    }

    @Override
    public Long deserialize(String string) {
        return Long.parseLong(string);
    }
}
