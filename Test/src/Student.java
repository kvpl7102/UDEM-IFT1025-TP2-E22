import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Student {

    private String name;
    private int id;
    private List<List<Object>> schedule = new ArrayList<>();

    Student(String name, int id) {
        this.name = name;
        this.id = id;

    }

    public void addCourse(String subject, int number, List<List<Object>> courseHours) {

        for (List<Object> course : courseHours) {

            if (schedule.size() == 0) {

                schedule.add(course);

            } else {
                for (List<Object> listschedule : schedule) {

                    if (listschedule.get(0) == course.get(0)) {

                        LocalTime hStart1 = (LocalTime) course.get(1);
                        LocalTime hEnd1 = (LocalTime) course.get(2);
                        LocalTime hStart2 = (LocalTime) listschedule.get(1);
                        LocalTime hEnd2 = (LocalTime) listschedule.get(2);

                        if (hStart1.isAfter(hEnd2) || hEnd1.isBefore(hStart2)) {

                            listschedule.add(course.get(1));
                            listschedule.add(course.get(2));
                        } else {
                            System.out.println("Conflit horraire ");
                        }
                    } else {
                        schedule.add(course);
                    }
                }
            }
        }

        // if(courseHours == null){
        // schedule.add(courseHours);
        // }else if(courseHours.get(0))

    }

    public void print() {
        System.out.println(schedule);
    }

}
