
public class App {
    public static void main(String[] args) {
        Course course1 = new Course("IFT", 1025);
        course1.setStartDate(2022, 5, 1);
        course1.setEndDate(2022, 7, 26);

        course1.addCourseDay(2);
        course1.addCourseDay(4);
        
        // System.out.println(course1.getCourseInfo());
        System.out.println(course1.getCourseHours());
    }
}
