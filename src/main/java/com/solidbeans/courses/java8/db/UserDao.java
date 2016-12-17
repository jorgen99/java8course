package com.solidbeans.courses.java8.db;

import java.util.Optional;
import java.util.Random;

import com.solidbeans.courses.java8.dto.User;

@SuppressWarnings("Duplicates")
public class UserDao {
    private static final String[] names = new String[] {"Anna",
                                                        "Britta",
                                                        "Kalle",
                                                        "Johan",
                                                        "Nisse",
                                                        "Lotta",
                                                        "Stina",
                                                        "Ulla",
                                                        "Gösta",
                                                        "Gunnar",
                                                        "Sven",
                                                        "Göran"};

    private final Random random = new Random();

    public Optional<User> findById(int id) {
        return Optional.of(new User(id, randomPick(names)));
    }

    public Optional<User> findByIdPossibleError(int id) {
        int randomUserId = this.random.nextInt(100);
        if (randomUserId > 80) {
            throw new RuntimeException("User with id " + id + " not found");
        } else if (randomUserId > 70) {
            return Optional.empty();
        } else {
            return findById(id);
        }
    }

    private <T> T randomPick(T[] array) {
        return array[random.nextInt(array.length)];
    }
}
