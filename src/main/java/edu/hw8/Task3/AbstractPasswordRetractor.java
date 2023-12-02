package edu.hw8.Task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public abstract class AbstractPasswordRetractor {
    private static final int MIN_PASSWORD_LENGTH = 4;
    private static final int MAX_PASSWORD_LENGTH = 6;
    protected static final byte[] ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789".getBytes(StandardCharsets.UTF_8);
    protected Map<ByteArray, String> users;
    protected final List<User> decodedUsers = new CopyOnWriteArrayList<>();

    public AbstractPasswordRetractor(Map<String, String> users) {
        this.users = users
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                user -> {
                    try {
                        return new ByteArray(Hex.decodeHex(user.getValue().toCharArray()));
                    } catch (DecoderException exception) {
                        throw new RuntimeException(exception);
                    }
                },
                Map.Entry::getKey
            )
        );
    }

    public abstract List<User> decode();

    @SneakyThrows
    protected void decodeInRange(int min, int max) {
        boolean containsSmallPasswords = min != -1;
        int[] indexes =
            createIndexes(
                containsSmallPasswords
                ? MIN_PASSWORD_LENGTH
                : MAX_PASSWORD_LENGTH
            );
        indexes[indexes.length - 1] = min;
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        while (indexes[indexes.length - 1] < max) {
            if (decodedUsers.size() == users.size()) {
                break;
            }
            byte[] bytes = nextPassword(indexes);
            ByteArray encodedPassword = encodeMd5(bytes, messageDigest);
            if (users.containsKey(encodedPassword)) {
                decodedUsers.add(new User(users.get(encodedPassword), new String(bytes)));
            }
        }
    }

    private int[] createIndexes(int minimumLength) {
        int[] indexes = new int[MAX_PASSWORD_LENGTH];
        for (int i = 0; i < indexes.length; ++i) {
            indexes[i] = (i >= minimumLength) ? -1 : 0;
        }

        return indexes;
    }

    private byte[] nextPassword(int[] indexes) {
        byte[] bytes = makeByteString(indexes);
        addOneToIndexes(indexes, ALPHABET.length);
        return bytes;
    }

    private void addOneToIndexes(int[] indexes, int max) {
        ++indexes[0];
        for (int i = 0; i < indexes.length - 1; ++i) {
            if (indexes[i] >= max) {
                indexes[i] = 0;
                ++indexes[i + 1];
            } else {
                break;
            }
        }
    }

    @SneakyThrows
    private ByteArray encodeMd5(byte[] password, MessageDigest messageDigest) {
        messageDigest.update(password);
        return new ByteArray(messageDigest.digest());
    }

    private byte[] makeByteString(int[] indexes) {
        byte[] bytes = new byte[indexes.length];
        int realCount = indexes.length;
        for (int i = 0; i < indexes.length; ++i) {
            if (indexes[i] < 0) {
                realCount = i;
                break;
            }
            bytes[i] = ALPHABET[indexes[i]];
        }
        bytes = Arrays.copyOf(bytes, realCount);
        return bytes;
    }

    protected record ByteArray(byte[] array) {
        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }

            if (object == null || getClass() != object.getClass()) {
                return false;
            }

            ByteArray byteArray = (ByteArray) object;

            return Arrays.equals(array, byteArray.array);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(array);
        }
    }
}
