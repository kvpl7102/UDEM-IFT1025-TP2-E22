import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // CoursesList coursesList = new CoursesList();

        // Course course1 = new Course("IFT", 1025, 3);
        // course1.addCourseHour();
        // course1.setCourseDate();
        // coursesList.addToCoursesList(course1);

        // Course course2 = new Course("MAT", 1905, 4);
        // course2.addCourseHour();
        // course2.setCourseDate();
        // coursesList.addToCoursesList(course2);

        // Student stu1 = new Student("kv", 123);
        // stu1.addCourse("IFT", 1025);
        // stu1.addCourse("MAT", 1905);

        // stu1.printSchedule();

        String courseHourType;
        do {
            System.out.print(" course's hour of:\n T - Theory course; P - Practical course; L - LAB");
            while (!scanner.hasNext()) {
                System.out.println("Not a valid input! Try again");
                scanner.next();
            }
            courseHourType = scanner.next();

        } while (!courseHourType.equalsIgnoreCase("T") && !courseHourType.equalsIgnoreCase("P") && !courseHourType
                .equalsIgnoreCase("L"));

        System.out.println(courseHourType);
    }
}
