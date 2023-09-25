package com.labs.java.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MethodRefDemo {


    public static void main(String[] args) {
        List<String> skills = List.of("Java", "Python", "Node JS");
//        System.out.println(skills);
//
//        for(String skill: skills) {
//            System.out.println(skill);
//        }

//        skills.forEach(item -> System.out.println(item));

//        Consumer<String> consumer = item -> {String item1 = item.toUpperCase(); System.out.println(item1);};
//
//        skills.forEach(item -> {String item1 = item.toUpperCase(); System.out.println(item1);});

        // Method Reference
//        skills.forEach(System.out::println);

        // Named Method Reference - calling static method
        skills.forEach(MethodRefDemo::printInUpperCase);

        // Named Method Reference - calling instance method
        MethodRefDemo obj = new MethodRefDemo();
        skills.forEach(obj::printInLowerCaseInstance);

//        Supplier empList = () -> {return new ArrayList(); };
        // Method Reference - with constructor
        Supplier emptyList = ArrayList::new;
        System.out.println(emptyList.get());
    }

    public static void printInUpperCase(String item) {
        String item1 = item.toUpperCase();
        System.out.println(item1);
    }

    public void printInLowerCaseInstance(String item) {
        String item1 = item.toLowerCase();
        System.out.println(item1);
    }

}
