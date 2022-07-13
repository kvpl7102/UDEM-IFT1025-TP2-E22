
/**
 * IFT 1025 - E22 - TP1
 * Date: 12 Juillet 2022
 * 
 * @author Le Kinh Vi Phung - 20178538
 * @author Belhachmi Adam - 20160421
 * 
 */

public class TP1 {
    public static void main(String[] args) {

        /**
         * Create a courses list to add all the created courses
         */
        CoursesList coursesList = new CoursesList();

        /**
         * Create a course
         * Set the course hours (end,start) using addCourseHour() and date(end, start
         * and exams) using setCourseDate()
         * Then after everything is set add the course to the coursesList with
         * addToCoursesList(@param course1)
         */
        Course course1 = new Course("IFT", 1025, 3);
        course1.addCourseHour();
        course1.setCourseDate();
        coursesList.addToCoursesList(course1);

        /**
         * Create another course (same process as the first)
         */
        Course course2 = new Course("MAT", 1905, 4);
        course2.addCourseHour();
        course2.setCourseDate();
        coursesList.addToCoursesList(course2);

        /**
         * Create a Student
         * Add the different courses to the student schedule using addCourse
         */
        Student stu1 = new Student("kv", 123);
        stu1.addCourse("IFT", 1025);
        stu1.addCourse("MAT", 1905);

        stu1.printSchedule();
        coursesList.displayCourses();
        course1.displayCourseInfos();

    }
}
