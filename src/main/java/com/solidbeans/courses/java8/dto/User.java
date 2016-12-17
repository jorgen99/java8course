package com.solidbeans.courses.java8.dto;

public class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", name='" + name + '\'' + '}';
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}
