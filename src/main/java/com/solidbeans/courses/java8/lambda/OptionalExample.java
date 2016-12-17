package com.solidbeans.courses.java8.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.solidbeans.courses.java8.db.UserDao;
import com.solidbeans.courses.java8.dto.User;

public class OptionalExample {

    public static void main(String[] args) {
        OptionalExample exmple = new OptionalExample();
        List<Integer> userIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<User> users = exmple.findUsers(userIds);
        System.out.println("users.size() = " + users.size());
        users.forEach(System.out::println);

        userIds.stream()
            .map(userId -> userId % 2 == 0 ? Optional.of(userId) : Optional.empty())
            .flatMap(o -> o.isPresent() ? Stream.of(o.get()) : Stream.empty())
            .forEach(System.out::println);
    }


    private List<User> findUsers(List<Integer> userIds) {
        UserDao userDao = new UserDao();

        return userIds.stream()
                .map(userDao::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
