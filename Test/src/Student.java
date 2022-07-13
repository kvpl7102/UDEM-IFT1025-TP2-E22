
/**
 * IFT 1025 - E22 - TP1
 * Date: 12 Juillet 2022
 * 
 * @author Le Kinh Vi Phung - 20178538
 * @author Belhachmi Adam  - 20160421
 * 
 */

import java.util.*;
import java.time.*;

/**
 * Create an instance of Student that has a name and an ID.
 * Each student has a schedule containing all enrolled courses.
 * Student can remove a course from schedule, or add a new course from
 * the courses list to the schedule as long as they don't have time conflicts.
 * 
 */
public class Student {

    public String name;
    public int id;
    public List<Course> schedule = new ArrayList<>();

    /**
     * Contructor of Student class
     * 
     * @param name: name of the student
     * @param id:   student ID
     */
    Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Enroll to a course in the system if the conditions are met
     * 
     * @param courseSubject: course's subject to add to schedule
     * @param courseNumber:  course's number to add to schedule
     * 
     */
    public void addCourse(String courseSubject, int courseNumber) {

        // If the course student wants to enroll is in the system
        if (CoursesList.checkCourseInList(courseNumber)) {

            // Boolean variable to see if there is a conflict in course's schedule
            Boolean conflitHoraire = true;

            Course wantedCourse = null;
            for (Course course : CoursesList.coursesList) {
                if (course.getCourseNumber() == courseNumber) {
                    wantedCourse = course;
                    break;
                }
            }

            if (schedule.size() == 0) { // If student's schedule is empty...
                conflitHoraire = false;
            } else if (schedule.contains(wantedCourse)) { // If student's schedule already contains the course...
                System.out.println("You've  already enrolled to " + wantedCourse.toString());

            } else {
                List<List<Object>> wantedCourseHours = wantedCourse.getCourseHours();

                for (Course myCourse : schedule) {
                    // Verify if the exams date are set or not
                    if (wantedCourse.getIntraDate() == null || wantedCourse.getFinalDate() == null) {
                        System.out.println("You need to set the exams date for " + wantedCourse.toString());
                        break;
                    } else if (myCourse.getIntraDate() == null || myCourse.getFinalDate() == null) {
                        System.out.println("You need to set the exams date for " + myCourse.toString());
                        break;
                    } else { // Verify if the exams date of the courses are too close to each other

                        if (wantedCourse.getIntraDate().isEqual(myCourse.getIntraDate())
                                || wantedCourse.getIntraDate().isEqual(myCourse.getFinalDate())
                                || wantedCourse.getFinalDate().isEqual(myCourse.getIntraDate())
                                || wantedCourse.getFinalDate().isEqual(myCourse.getFinalDate())) {
                            System.out.println("\n" + wantedCourse + " has conflicted exams date with " + myCourse);
                            break;

                        } else {
                            List<List<Object>> myCourseHours = myCourse.getCourseHours();
                            for (List<Object> myCourseHour : myCourseHours) {

                                for (List<Object> wantedCourseHour : wantedCourseHours) {
                                    // If on the same day...
                                    if (myCourseHour.get(0) == wantedCourseHour.get(0)) {

                                        LocalTime hStart1 = (LocalTime) myCourseHour.get(1);
                                        LocalTime hEnd1 = (LocalTime) myCourseHour.get(2);

                                        LocalTime hStart2 = (LocalTime) wantedCourseHour.get(1);
                                        LocalTime hEnd2 = (LocalTime) wantedCourseHour.get(2);

                                        // Verify if there exists any overlapping courses' hours...
                                        if (hStart2.isAfter(hEnd1) || hEnd2.isBefore(hStart1)) {
                                            conflitHoraire = false;
                                        } else {
                                            System.out.println(
                                                    "\n" + wantedCourse + " has conflicted hours with " + myCourse
                                                            + " on " + myCourseHour.get(0));
                                            break;
                                        }
                                    } else {
                                        conflitHoraire = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (!conflitHoraire) { // If all conditions are met, add the course to student's schedule!
                System.out.println(wantedCourse.toString() + " has been added to your schedule!");
                schedule.add(wantedCourse);
            }
        } else { // If the course student wants to enroll is NOT in the system
            System.out.println("Course is not found in the system!");
        }

    }

    /**
     * Remove a course in the schedule
     * 
     * @param courseSubject: course's subject to remove
     * @param courseNumber:  course's number to remove
     * 
     */
    public void removeCourse(String courseSubject, int courseNumber) {

        Course removingCourse = null;
        // If the schedule doesn't contain any course...
        if (schedule.size() == 0) {
            System.out.println("You are not enrolled to any course!");
        } else { // Verify if the course is in student's schedule or not

            for (Course myCourse : schedule) {
                if (myCourse.getCourseNumber() == courseNumber) {
                    removingCourse = myCourse;
                    break;
                }
            }

            if (removingCourse == null) { // If the course is NOT in the schedule...
                System.out.println("You have not enrolled to this course in your schedule!");
            } else { // If the course is in the schedule...
                System.out.println(removingCourse.toString() + " has been removed from your schedule!");
                schedule.remove(removingCourse);
            }
        }
    }

    /**
     * Print the student's schedule
     * 
     */
    public void printSchedule() {
        System.out.println("\n" + this.name + "'s schedule: \n");
        for (Course myCourse : schedule) {
            System.out.println(myCourse.displayCourseInfos());
        }

    }

}