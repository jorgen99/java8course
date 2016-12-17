package com.solidbeans.courses.java8.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMap {

  public static void main(String[] args) {
    Coder backendGuy = new Coder("Nisse");
    backendGuy.addLang("Java");
    backendGuy.addLang("Scala");
    backendGuy.addLang("C++");

    Coder scriptKiddie = new Coder("Kevin");
    scriptKiddie.addLang("JavaScript");
    scriptKiddie.addLang("Ruby");

    List<Coder> coders = Arrays.asList(backendGuy, scriptKiddie);
    coders.stream()
        .flatMap(c -> c.languages.stream())
        .forEach(System.out::println);
  }

  static class Coder {
    String name;
    List<String> languages = new ArrayList<>();

    public Coder(String name) {
      this.name = name;
    }

    public void addLang(String lang) {
      languages.add(lang);
    }
  }
}
