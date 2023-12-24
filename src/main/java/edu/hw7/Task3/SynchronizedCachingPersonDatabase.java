package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynchronizedCachingPersonDatabase implements PersonDatabase {
    private final Map<String, List<Person>> nameCache = new HashMap<>();
    private final Map<String, List<Person>> addressCache = new HashMap<>();
    private final Map<String, List<Person>> phoneNumberCache = new HashMap<>();
    private final Map<Integer, Person> personDictionary = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        personDictionary.put(person.id(), person);
        nameCache.computeIfAbsent(person.name(), id -> new ArrayList<>()).add(person);
        addressCache.computeIfAbsent(person.address(), key -> new ArrayList<>()).add(person);
        phoneNumberCache.computeIfAbsent(person.phoneNumber(), key -> new ArrayList<>()).add(person);
    }

    @Override
    public synchronized void delete(int id) {
        nameCache.remove(personDictionary.get(id).name());
        addressCache.remove(personDictionary.get(id).address());
        phoneNumberCache.remove(personDictionary.get(id).phoneNumber());
        personDictionary.remove(id);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameCache.get(name);
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return addressCache.get(address);
    }

    @Override
    public synchronized List<Person> findByPhoneNumber(String phoneNumber) {
        return phoneNumberCache.get(phoneNumber);
    }
}
