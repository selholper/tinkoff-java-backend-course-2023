package edu.hw8.Task3;

import java.util.List;
import java.util.Map;

public class SingleThreadPasswordRetractor extends AbstractPasswordRetractor {
    public SingleThreadPasswordRetractor(Map<String, String> users) {
        super(users);
    }

    @Override
    public List<User> decode() {
        decodeInRange(-1, ALPHABET.length);

        return decodedUsers;
    }
}
