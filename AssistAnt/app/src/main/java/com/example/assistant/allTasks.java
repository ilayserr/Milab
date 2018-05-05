package com.example.assistant;

/**
 * Created by shaniadir on 1/8/18.
 */

public class allTasks {

    public allTasks(){

    }
    Task firstTask = new Task("Exercise #9",
            "Automata And Formal Language", 120,
            "Tuesday, 9 January 2018, 12:00 AM",
            "4 hours 52 mins");
    Task secondTask = new Task("Exercise 4",
            "Computer Networks", 130,
            "Friday, 12 January 2018, 02:00 PM",
            "3 days and 4 hours");
    Task thirdTask = new Task("Assignment 7",
            "Algorithms", 150,
            "Sunday, 14 January 2018, 01:00 PM",
            "5 days and 3 hours");
    Task FourthTask = new Task("Assignment 6",
            "Algorithms", 150,
            "Sunday, 7 January 2018, 01:00 PM",
            "Assignment was submitted 4 hours 5 mins early");
    Task FifthTask = new Task("Assignment 6",
            "Algorithms", 150,
            "Sunday, 7 January 2018, 01:00 PM",
            "Assignment was submitted 4 hours 5 mins early");
//    Task FifthTask = new Task("Assignment 5",
//            new Course ("Algorithms", 150),
//            "Sunday, 1 January 2018, 01:00 PM",
//            "Assignment was submitted 4 hours 5 mins early",5, 10, true, 4, 10,000.0);
    Task SixthTask = new Task("Assignment 6",
            "Algorithms", 150,
            "Sunday, 7 January 2018, 01:00 PM",
            "Assignment was submitted 4 hours 5 mins early");
    Task SeventhTask = new Task("Assignment 6",
            "Algorithms", 150,
            "Sunday, 7 January 2018, 01:00 PM",
            "Assignment was submitted 4 hours 5 mins early");
    Task EightTask = new Task("Assignment 6",
            "Algorithms", 150,
            "Sunday, 7 January 2018, 01:00 PM",
            "Assignment was submitted 4 hours 5 mins early");
    Task NinethTask = new Task("Assignment 6",
            "Algorithms", 150,
            "Sunday, 7 January 2018, 01:00 PM",
            "Assignment was submitted 4 hours 5 mins early");
    Task TenthTask = new Task("Assignment 6",
            "Algorithms", 150,
            "Sunday, 7 January 2018, 01:00 PM",
            "Assignment was submitted 4 hours 5 mins early");

    Task[] allTasks = new Task[] {firstTask, secondTask, thirdTask, FourthTask,
                                  SixthTask, SeventhTask, EightTask, NinethTask, TenthTask};

    public Task[] getAllTasks(){
        return allTasks.clone();
    }
}
