package com.example.assistant;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shaniadir on 1/8/18.
 */

public class Task implements Serializable {
    String taskName;
    Course course;
    String deadline;
    String timeRemaining;
    int grade;
    double difficult;
    int estimatedTime;
    boolean isSubmitted;
    int amountOfFinish;

    public Task(String taskName, Course course, String deadline, String timeRemaining){
        this.taskName = taskName;
        this.course = course;
        this.deadline = deadline;
        this.timeRemaining = timeRemaining;
        this.grade = 0;
        this.difficult = 0;
        this.estimatedTime = 0;
        this.isSubmitted = false;
        this.amountOfFinish = 0;
    }

    public Task(String taskName, Course course, String deadline, String timeRemaining,
                int grade, double difficult, int estimatedTime, boolean isSubmitted, int amountFinished){
        this.taskName = taskName;
        this.course = course;
        this.deadline = deadline;
        this.timeRemaining = timeRemaining;
        this.grade = 0;
        this.difficult = 0;
        this.estimatedTime = estimatedTime;
        this.isSubmitted = false;
        this.amountOfFinish = amountFinished;
    }

    public Task(String taskName, String courseName, int numOfStudents, String deadline, String timeRemaining) {
        this.taskName = taskName;
        this.course = new Course(courseName, numOfStudents);
        this.deadline = deadline;
        this.timeRemaining = timeRemaining;
    }



    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public double getDifficult() {
        return difficult;
    }

    public void setDifficult(double difficult) {
        this.difficult = difficult;
    }

    public int getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public int getAmountOfFinish() {
        return amountOfFinish;
    }

    public void setAmountOfFinish(int amountOfFinish) {
        this.amountOfFinish = amountOfFinish;
    }


    /**
     * get '2/01/2018, 23:55' and convert to: 'Tuesday, 2 January 2018, 11:55 PM'
     *
     * @param deadline
     * @return
     */
    public Date parseToDate(String deadline){
        //TODO: implement
        return null;
    }

    public String parseToTimeRemainingFormat(String timeRemaining){
        //TODO: implement
        return null;
    }
}
