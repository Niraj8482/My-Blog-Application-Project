package com.blogapp01;

import lombok.Data;

@Data
public class Employee {
    private long id;
    private String name;
    private int salary;

    public Employee(long id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    //    public long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getSalary() {
//        return salary;
//    }


}
