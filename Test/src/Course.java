import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Course {

    private String courseName;
    private int courseNumber;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate intraDate;
    private LocalDate finalDate;

    private ArrayList<ArrayList<Object>> courseHours = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);
    
    // Course constructor
    Course (String courseName, int courseNumber) {
        this.setCourseName(courseName);
        this.setCourseNumber(courseNumber);
    }


    /**
     * Getters and Setters methods for course name and course number
     * 
     */
    // Getter for course name
    public String getCourseName() {
        return this.courseName;
    }

    // Setter for course name
    public void setCourseName(String courseName) {
        this.courseName = courseName;   
    }

    // Getter for course number
    public int getCourseNumber() {
        return this.courseNumber;
    }

    // Setter for course number
    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;   
    }

    /**
     * Add course's hour to a day in the week
     * @param int dayOfWeek: day in the week to add course's hour
     * (e.g: 1 - Monday, 2 - Tuesday,..., 3 - Wednesday, 7 - Sunday)  
     * @return add an ArrayList of course's hour of that day to courseHours
     * 
     */
    public void addCourseDay(int dayOfWeek) {
        ArrayList<Object> courseHour = new ArrayList<>();

        courseHour.add(DayOfWeek.of(dayOfWeek));

        /* -------------------------------------------------------------------------------------------------------- */
        System.out.print("Enter the course's start hour for " + DayOfWeek.of(dayOfWeek) + " (HHMM): ");
        String startHourInput = String.valueOf(scanner.nextInt());
        LocalTime startHour = LocalTime.of(Integer.parseInt(startHourInput.substring(0, 2)), 
                                            Integer.parseInt(startHourInput.substring(2)));
        courseHour.add(startHour);

        /* -------------------------------------------------------------------------------------------------------- */
        System.out.print("Enter the course's end hour for " + DayOfWeek.of(dayOfWeek) + " (HHMM): ");
        String endHourInput = String.valueOf(scanner.nextInt());
        LocalTime endHour = LocalTime.of(Integer.parseInt(endHourInput.substring(0, 2)), 
                                            Integer.parseInt(endHourInput.substring(2)));
        courseHour.add(endHour);

        /* -------------------------------------------------------------------------------------------------------- */
        courseHours.add(courseHour);

        scanner.reset();
    }

    // Display the course hours
    public String getCourseHours() {
        String result = "";
        for (ArrayList<Object> courseHour : courseHours) {
            result +=  "Course's hour for "+ courseHour.get(0)+": "
                    + courseHour.get(1)+ "-"+ courseHour.get(2)+ "\n";
        }
        
        return result;
    }

    /**
     * Getters and Setters methods for course date
     * 
     */
    // Date formatter as a string (e.g: 6-Jul-2022)
    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy"); 

    // Getter for start date
    public String getStartDate() {
        String formattedStartDate =  this.startDate.format(formattedDate);
        return formattedStartDate;
    }

    // Setter for start date
    public void setStartDate(int year, int month, int dayOfMonth) {
        this.startDate = LocalDate.of(year, month, dayOfMonth);      
    }

    // Getter for end date
    public String getEndDate() {
        String formattedEndDate =  this.endDate.format(formattedDate);
        return formattedEndDate;
    }

    // Setter for end date
    public void setEndDate(int year, int month, int dayOfMonth) {
        this.endDate = LocalDate.of(year, month, dayOfMonth);
    }

    // Getter for intra's date
    public String getIntraDate() {
        String formattedStartDate =  this.intraDate.format(formattedDate);
        return formattedStartDate;
    }

    // Setter for intra's date 
    public void setIntraDate(int year, int month, int dayOfMonth) {
        this.intraDate = LocalDate.of(year, month, dayOfMonth);      
    }

    // Display all the course's dates
    public String getCourseDate() {
        return  "Course: " + this.courseName + this.courseNumber + "\n"  
                + "Start Date: " + this.getStartDate() + "\n" 
                + "End Date: " + this.getEndDate() + "\n";
    }   

    @Override
    public String toString() {
        return this.courseName + this.courseNumber;
    }

}
