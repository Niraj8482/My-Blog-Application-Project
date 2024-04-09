package com.blogapp01;

import org.hibernate.engine.internal.Collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TestClass {
    public static void main(String[] args) {

//                      Predicate Functional Interface      ---->>>       gives boolean o/p

        Predicate<Integer> val = n->n%2==0;
        boolean test = val.test(101);
        System.out.println(test);

//        Finding even numbers from the collection
        List<Integer> data = Arrays.asList(10, 20, 13, 30);
        List<Integer> newData = data.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println(newData);

//        Finding the word which starts with letter 's' from the collection
        List<String> value = Arrays.asList("mike", "adam", "sam", "smith");
        List<String> newVal = value.stream().filter(n->n.startsWith("s")).collect(Collectors.toList());
        System.out.println(newVal);

//        Finding similar words in the collection
        List<String> stream = Arrays.asList("mike", "adam", "sam", "adam");
        List<String> newStream = stream.stream().filter(n->n.equalsIgnoreCase("adam")).collect(Collectors.toList());
        System.out.println(newStream);
        System.out.println(newStream.size());

//        Finding the unique words from the collection
        List<String> val1 = Arrays.asList("mike", "adam", "sam", "adam");
        List<String> newVal1 = val1.stream().distinct().collect(Collectors.toList());
        System.out.println(newVal1);

//                          Function Functional Interface    ---->>>       transforms the input data

        Function<Integer, Integer> game = n->n+10;
        Integer newGame = game.apply(10);
        System.out.println(newGame);

//        Finding squares of every number in the given data-structure
        List<Integer> list = Arrays.asList(10, 20, 30, 40);     // modify
        List<Integer> newList = list.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println(newList);

//        Convert every word to the Upper Case of given data-structure
        List<String> list1 = Arrays.asList("mike", "adam", "niraj", "mike");
        List<String> collect = list1.stream().map(n -> n.toUpperCase()).collect(Collectors.toList());
        System.out.println(collect);
        

//        find words whose length is greater than 5 and all must be unique
        List<String> list2 = Arrays.asList("akshay", "sahil", "niraj", "devansh", "devansh");
        List<String> collect1 = list2.stream().filter(n -> n.length()>5).distinct().collect(Collectors.toList());
        System.out.println(collect1);
        System.out.println(collect1.size());

//        find words in which 3rd character must be 'a'
        List<String> list3 = Arrays.asList("akash", "niarj", "jack");
        List<String> collect3 = list3.stream().filter(n -> n.substring(2,3).equalsIgnoreCase("a")).distinct().collect(Collectors.toList());
        System.out.println(collect3);
        System.out.println(collect3.size());

//        make every even number odd in the collection
        List<Integer> list4 = Arrays.asList(10,13,9,12);
        List<Integer> collect4 = list4.stream().filter(n -> n%2==0).map(m->m+1).collect(Collectors.toList());
        System.out.println(collect4);
        System.out.println(collect4.size());

//        remove 1st and last character of every word in the collection
        List<String> list5 = Arrays.asList("akash", "niarj", "jack");
        List<String> collect5 = list5.stream().map(n -> n.substring(1,n.length()-1)).collect(Collectors.toList());
        System.out.println(collect5);
        System.out.println(collect5.size());


//                     Consumer Functional Interface     --->>>     Takes Single I/p and returns NO O/p

        Consumer<Integer> val2 = n-> System.out.println(n);
        val2.accept(10);

        Consumer<String> val4 = n-> System.out.println(n);
        val4.accept("Niraj");

//                      Supplier Functional Interface    ---->>>    Takes no I/p and only produces O/p

        Supplier<Double> val3 = ()-> Math.random();
        Double i = val3.get();
        System.out.println(i);



//        Give the name and id of Employee whose salary is greater than 5000

        List<Employee> employees = Arrays.asList(
                new Employee(1, "Niraj", 2500),
                new Employee(4, "Sahil", 5500),
                new Employee(8, "Akshay", 7000),
                new Employee(9, "Devansh", 3000)
        );

        List<Employee> newD = employees.stream().filter(n -> n.getSalary() > 5000).collect(Collectors.toList());

        for (Employee emp:newD){
            System.out.println(emp.getId() + " " + emp.getName());
        }

        List<Integer> list6 = Arrays.asList(1, 3, 4, 6);
        list6.stream().forEach(e-> System.out.println(e));



//        reduce the salary of each employee by 500

        List<Integer> reduce = employees.stream().map(e -> e.getSalary() - 500).collect(Collectors.toList());
        System.out.println(reduce);


//                          METHOD REFERENCE        ---->>>   To call the method present inside the class

        List<String> collect2 = employees.stream().map(Employee::getName).collect(Collectors.toList());
        System.out.println(collect2);
    }


}
