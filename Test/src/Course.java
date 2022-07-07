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
     * Getters and Setters methods for course's name and course's number
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
     * Getters and Setters methods for course's dates
     * 
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public LocalDate getIntraDate() {
        return this.intraDate;
    }

    public LocalDate getFinalDate() {
        return this.finalDate;
    }

    public void setCourseDate() {
        System.out.print("Enter the course's year: ");
        int courseYear = scanner.nextInt();

        /* ---------------------------------------------------------------------------------------------- */
        System.out.print("Enter the course's start month: ");
        int startMonth = scanner.nextInt();

        System.out.print("Enter the course's start date: ");
        int startDate = scanner.nextInt();

        LocalDate courseStartDate = LocalDate.of(courseYear, startMonth, startDate);
        this.startDate = courseStartDate;

        /* ---------------------------------------------------------------------------------------------- */
        System.out.print("Enter the course's end month: ");
        int endMonth = scanner.nextInt();

        System.out.print("Enter the course's end date: ");
        int endDate = scanner.nextInt();

        LocalDate courseEndDate = LocalDate.of(courseYear, endMonth, endDate);
        this.endDate= courseEndDate;

        /* ---------------------------------------------------------------------------------------------- */
        System.out.print("Enter the intra's month: ");
        int intraMonth = scanner.nextInt();

        System.out.print("Enter the intra's date: ");
        int intraDate = scanner.nextInt();

        LocalDate courseIntraDate = LocalDate.of(courseYear, intraMonth, intraDate);
        this.intraDate = courseIntraDate;

        /* ---------------------------------------------------------------------------------------------- */
        System.out.print("Enter the final's month: ");
        int finalMonth = scanner.nextInt();

        System.out.print("Enter the final's date: ");
        int finalDate = scanner.nextInt();

        LocalDate courseFinalDate = LocalDate.of(courseYear, finalMonth, finalDate);
        this.finalDate = courseFinalDate;
        
    }

    // Date formatter as a string (e.g: 6-Jul-2022)
    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy"); 


    // Display all the course's dates
    public String getCourseDate() {
        return "\n" + "Course: " + this.courseName + this.courseNumber + "\n"  
                + "Start Date: " + this.getStartDate().format(formattedDate) + "\n" 
                + "End Date: " + this.getEndDate().format(formattedDate) + "\n"
                + "Intra Date: " + this.getIntraDate().format(formattedDate) + "\n"
                + "Final Date: " + this.getFinalDate().format(formattedDate) + "\n";
    }   

    @Override
    public String toString() {
        return this.courseName + this.courseNumber;
    }

}
