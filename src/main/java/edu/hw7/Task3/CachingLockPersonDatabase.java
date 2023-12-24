package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachingLockPersonDatabase implements PersonDatabase {
    private final Map<String, List<Person>> nameCache = new HashMap<>();
    private final Map<String, List<Person>> addressCache = new HashMap<>();
    private final Map<String, List<Person>> phoneNumberCache = new HashMap<>();
    private final Map<Integer, Person> personDictionary = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void add(Person person) {
        readWriteLock.writeLock().lock();
        try {
            personDictionary.put(person.id(), person);
            nameCache.computeIfAbsent(person.name(), key -> new ArrayList<>()).add(person);
            addressCache.computeIfAbsent(person.address(), key -> new ArrayList<>()).add(person);
            phoneNumberCache.computeIfAbsent(person.phoneNumber(), key -> new ArrayList<>()).add(person);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        readWriteLock.writeLock().lock();
        try {
            nameCache.remove(personDictionary.get(id).name());
            addressCache.remove(personDictionary.get(id).address());
            phoneNumberCache.remove(personDictionary.get(id).phoneNumber());
            personDictionary.remove(id);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        readWriteLock.writeLock().lock();
        try {
            return nameCache.get(name);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        readWriteLock.writeLock().lock();
        try {
            return addressCache.get(address);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhoneNumber(String phoneNumber) {
        readWriteLock.writeLock().lock();
        try {
            return phoneNumberCache.get(phoneNumber);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
