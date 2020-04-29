package com.royforthewin;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Person> people = getPeople();

        // Imperative approach ❌

    /*
    List<Person> females = new ArrayList<>();
    for (Person person : people) {
      if (person.getGender().equals(Gender.FEMALE)) {
        females.add(person);
      }
    }
    females.forEach(System.out::println);
    */

        // Declarative approach ✅

        // filter
        List<Person> femaleList = people.stream().filter(person -> Gender.FEMALE.equals(person.getGender()))
                .collect(Collectors.toList());

        // femaleList.forEach(System.out::println);

        // sort
        List<Person> sortedList = people.stream().sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList());

        // sortedList.forEach(System.out::println);

        // all match
        boolean allMatch = people.stream().allMatch(person -> person.getAge() > 5);
        // System.out.println(allMatch);

        // any match
        boolean anyMatch = people.stream().anyMatch(person -> person.getAge() > 5);
        // System.out.println(anyMatch);

        // none match
        boolean noneMatch = people.stream().noneMatch(person -> "Roy".equals(person.getName()));
        //System.out.println(noneMatch);

        // max
        people.stream().max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        // min
        people.stream().min(Comparator.comparing(Person::getAge))
                .ifPresent(person -> System.out.println(person.getName()));

        // group
        Map<Gender, List<Person>> mapByGender = people.stream().collect(Collectors.groupingBy(Person::getGender));

        mapByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
        });

        Optional<String> oldestFemaleAge = people.stream().filter(person -> Gender.FEMALE.equals(person.getGender()))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);

        oldestFemaleAge.ifPresent(System.out::println);


    }

    private static List<Person> getPeople() {
        return Stream.of(
                new Person("Antonio", 20, Gender.MALE),
                new Person("Alina Smith", 33, Gender.FEMALE),
                new Person("Helen White", 57, Gender.FEMALE),
                new Person("Alex Boz", 14, Gender.MALE),
                new Person("Jamie Goa", 99, Gender.MALE),
                new Person("Anna Cook", 7, Gender.FEMALE),
                new Person("Zelda Brown", 120, Gender.FEMALE)
        ).collect(Collectors.toList());
    }

}