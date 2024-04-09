package com.blogapp01;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;

public class MainClass {

    public static void main(String[] args) {

//        Group by based on the salary

        List<Employee> list = Arrays.asList(
                new Employee(1, "Hemant", 9000),
                new Employee(2, "Kumar", 8000),
                new Employee(3, "Bhaskar", 9000)
        );

        //  groupingBy will implement Function Functional interface

        Map<Integer, List<Employee>> collect = list.stream().collect(Collectors.groupingBy(e -> e.getSalary()));
        System.out.println(collect);

        for(Map.Entry<Integer,List<Employee>> entry : collect.entrySet()){
            System.out.println(entry);
        }

        PasswordEncoder e = new BCryptPasswordEncoder();
        System.out.println(e.encode("testing"));
    }


}
