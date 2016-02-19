package com.solidbeans.courses.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class NonOptionalExample {

    public static void main(String[] args) {
        NonOptionalExample exmple = new NonOptionalExample();
        List<User> users = exmple.findUsers(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        users.forEach(System.out::println);
    }


    private List<User> findUsers(List<Integer> userIds) {
        UserDao userDao = new UserDao();

        return userIds.stream()
                .map(userDao::findById)
                .filter(user -> user.name.startsWith("G"))
                .collect(Collectors.toList());
    }


    @SuppressWarnings("Duplicates")
    private static class UserDao {
        private static final String[] names = new String[]{
                "Anna", "Britta", "Kalle", "Johan",
                "Nisse", "Lotta", "Stina", "Ulla",
                "Gösta", "Gunnar", "Sven", "Göran"
        };

        private final Random random = new Random();

        User findById(int id) {
            return getUserFromDb(id);
        }

        private User getUserFromDb(int id) {
            int randomUserId = this.random.nextInt(100);
            if (randomUserId > 80) {
                throw new RuntimeException("User with id " + id + " not found");
            } else if (randomUserId > 70) {
                return null;
            } else {
                return new User(id, randomPick(names));
            }
        }

        private <T> T randomPick(T[] array) {
            return array[random.nextInt(array.length)];
        }
    }

    private static class User {
        int userId;
        String name;

        User(int userId, String name) {
            this.userId = userId;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId=" + userId +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
