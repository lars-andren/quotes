package com.angular.domain;

public class Employee {
    private long id;
    private String firstName;
    private String lastName;
    
    public Employee(long id, String fName, String lastName) {
        this.id = id;
        this.firstName = fName;
        this.lastName = lastName;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
