package com.example.assistant;

/**
 * Created by shaniadir on 1/15/18.
 */

public class Course {
    String name;
    int numOfStudents;

    public Course(String name, int numOfStudents){
        this.name = name;
        this.numOfStudents = numOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfStudents() {
        return numOfStudents;
    }

    public void setNumOfStudents(int numOfStudents) {
        this.numOfStudents = numOfStudents;
    }
}
