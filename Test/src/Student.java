import java.util.*;
import java.time.*;

public class Student {

    private String name;
    private int id;
    public List<Course> schedule = new ArrayList<>();

    Student(String name, int id) {
        this.name = name;
        this.id = id;

    }

    public void addCourse(String subject, int number) {

        if (CoursesList.checkCourseInList(number)) {

            Boolean conflitHoraire = true;
            Course wantedCourse = null;
            for (Course course : CoursesList.coursesList) {
                if (course.getCourseNumber() == number) {
                    wantedCourse = course;
                    break;
                }
            }

            if (schedule.size() == 0 && !schedule.contains(wantedCourse)) {
                conflitHoraire = false;
            } else {

                List<List<Object>> wantedCourseHours = wantedCourse.courseHours;

                for (Course myCourse : schedule) {

                    List<List<Object>> myCourseHours = myCourse.courseHours;

                    for (List<Object> myCourseHour : myCourseHours) {

                        for (List<Object> wantedCourseHour : wantedCourseHours) {

                            if (myCourseHour.get(0) == wantedCourseHour.get(0)) {

                                LocalTime hStart1 = (LocalTime) myCourseHour.get(1);
                                LocalTime hEnd1 = (LocalTime) myCourseHour.get(2);

                                LocalTime hStart2 = (LocalTime) wantedCourseHour.get(1);
                                LocalTime hEnd2 = (LocalTime) wantedCourseHour.get(2);

                                if (hStart2.isAfter(hStart1) && hStart2.isBefore(hEnd1)
                                        || hEnd2.isAfter(hStart1) && hEnd2.isBefore(hEnd1)) {
                                    System.out.println("Conflit horaire avec " + myCourse);
                                    break;
                                } else {
                                    conflitHoraire = false;
                                }
                            }
                        }
                    }
                }
            }

            if (!conflitHoraire) {
                schedule.add(wantedCourse);
            }
        } else {
            System.out.println("There's no such course in the system!");
        }

    }

    public void printSchedule() {
        System.out.println(schedule);
    }

}