import java.util.*;

public class CoursesList {

    public static List<Course> coursesList = new ArrayList<>();

    // Add a course to the courses list
    public void addToCoursesList(Course course) {
        coursesList.add(course);
    }

    // Display all the course in the list
    public void displayCourses() {
        for (Course course : coursesList) {
            System.out.println(course);
        }
    }

    /**
     * Check whether a course is in the list or not
     * 
     * @param courseNumber number of the course needed to verify
     * @return the boolean value isFound
     * 
     */
    public static boolean checkCourseInList(int courseNumber) {
        boolean isFound = false;
        for (Course course : coursesList) {
            if (course.getCourseNumber() == courseNumber) {
                isFound = true;
            }
        }
        return isFound;
    }

    /**
     * Remove a course from the list
     * 
     * @param courseNumber number of the course needed to be removed
     * 
     */
    public void removeCourse(int courseNumber) {
        for (Course course : coursesList) {
            if (course.getCourseNumber() == courseNumber) {
                coursesList.remove(course);
            }
        }
    }

}
