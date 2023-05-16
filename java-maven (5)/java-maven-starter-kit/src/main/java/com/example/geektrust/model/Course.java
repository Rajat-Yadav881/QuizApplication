package com.example.geektrust.model;

import java.util.Comparator;
import java.util.Date;
import java.util.Map;

public class Course implements Comparable<Course> {
    private final String courseID;
    private final String courseName;
    private final String instructor;
    private final Date date;
    private final int minCapacity;
    private final int maxCapacity;
    private boolean isAllotted;
    private boolean isCancelled;


    private final Map<String,Employee>registerEmployee;

    public Course(String courseID, String courseName, String instructor, Date date, int minCapacity, int maxCapacity, boolean isAllotted, boolean isCancelled, Map<String, Employee> registerEmployee) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.instructor = instructor;
        this.date = date;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.isAllotted = isAllotted;
        this.isCancelled = isCancelled;
        this.registerEmployee = registerEmployee;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getInstructor() {
        return instructor;
    }

    public Date getDate() {
        return date;
    }

    public int getMinCapacity() {
        return minCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public boolean isAllotted() {
        return isAllotted;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public Map<String, Employee> getRegisterEmployee() {
        return registerEmployee;
    }

    public String addEmployee(Employee employee){
        String registrationId = "REG-COURSE-"+employee.getName()+"-"+this.courseName;
        this.registerEmployee.put(registrationId,employee);
        return registrationId;
    }


    @Override
    public int compareTo(Course o) {
        return this.courseName.compareTo(o.courseName);
    }
}
