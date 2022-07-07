import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Course {

    private String courseSubject;
    private int courseNumber;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate intraDate;
    private LocalDate finalDate;

    private List<List<Object>> courseHours = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    // Course constructor
    public Course(String courseSubject, int courseNumber) {
        this.setCourseSubject(courseSubject);
        this.setCourseNumber(courseNumber);

        /**
         * Setup the course. Enter a valid input to perform diffent actions:
         * 1 - Set up course's dates; 2 - Set up course's hours
         * 3 - See course's infos; 4 - Quit the setup
         * 
         */
        while (true) {
            scanner.reset();
            System.out.println(
                    "\nWhat would you like to do? Enter the appropriate number: \n 1 - Set up course's dates; 2 - Set up course's hours; 3 - See course's infos; 4 - Quit ");

            while (!scanner.hasNextInt()) {
                System.out.println("Enter a valid number choice");
                continue;
            }
            int answer = scanner.nextInt();

            if (answer == 1) {
                this.setCourseDate();
            } else if (answer == 2) {
                this.addCourseDay();
            } else if (answer == 3) {
                try {
                    System.out.println(this.getCourseDate());
                    System.out.println(this.getCourseHours());
                } catch (NullPointerException e) {
                    System.out.println("You need to enter the course's details!");
                    continue;
                }
            } else if (answer == 4) {
                break;
            } else {
                System.out.println("Enter a valid number choice");
                continue;
            }
        }
    }

    /**
     * Getters and Setters methods for course's name and course's number
     * 
     */
    // Getter for course name
    public String getCourseSubject() {
        return this.courseSubject;
    }

    // Setter for course name
    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
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
     * 1 - Monday, 2 - Tuesday, 3 - Wednesday, 4 - Thurday
     * 5 - Friday, 6 - Saturday, 7 - Sunday
     * 
     */
    public void addCourseDay() {
        List<Object> courseHour = new ArrayList<>();

        // -------------------------------------------------------------------------------------------
        // Get and validate input for day of week.
        int dayOfWeek;
        do {
            System.out.print("Enter the day of the week to add course's hour: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Not a number. Try again!");
                scanner.next();
            }
            dayOfWeek = scanner.nextInt();
        } while (dayOfWeek <= 0 || dayOfWeek >= 8);

        courseHour.add(DayOfWeek.of(dayOfWeek));

        // -------------------------------------------------------------------------------------------
        // Get and validate input for course's start hour
        while (true) {
            int numHour;
            do {
                System.out.print(
                        "Enter the course's START HOUR for " + DayOfWeek.of(dayOfWeek) + " with the form of (hhmm): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Not a number. Try again!");
                    scanner.next();
                }
                numHour = scanner.nextInt();
            } while (String.valueOf(numHour).length() != 4);

            String startHourInput = String.valueOf(numHour);
            try {
                LocalTime startHour = LocalTime.of(Integer.parseInt(startHourInput.substring(0, 2)),
                        Integer.parseInt(startHourInput.substring(2)));
                courseHour.add(startHour);
                break;
            } catch (DateTimeException e) {
                System.out.println("Enter a valid number of hour!");
                continue;
            }
        }

        // -------------------------------------------------------------------------------------------
        // Get and validate input for course's end hour
        while (true) {
            int numHour;
            do {
                System.out.print(
                        "Enter the course's END HOUR for " + DayOfWeek.of(dayOfWeek) + " with the form of (HHMM): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Not a number. Try again!");
                    scanner.next();
                }
                numHour = scanner.nextInt();
            } while (String.valueOf(numHour).length() != 4);

            String endHourInput = String.valueOf(numHour);
            try {
                LocalTime endHour = LocalTime.of(Integer.parseInt(endHourInput.substring(0, 2)),
                        Integer.parseInt(endHourInput.substring(2)));
                courseHour.add(endHour);
                break;
            } catch (DateTimeException e) {
                System.out.println("Enter a valid number of hour!");
                continue;
            }
        }

    }

    // Display the course hours
    public String getCourseHours() {
        String result = "";
        for (List<Object> courseHour : courseHours) {
            result += "Course's hour for " + courseHour.get(0) + ": "
                    + courseHour.get(1) + "-" + courseHour.get(2) + "\n";
        }

        return result;
    }

    // Getters and Setters methods for course's dates

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

        /*
         * -----------------------------------------------------------------------------
         * -----------------
         */
        System.out.print("Enter the course's start month: ");
        int startMonth = scanner.nextInt();

        System.out.print("Enter the course's start date: ");
        int startDate = scanner.nextInt();

        LocalDate courseStartDate = LocalDate.of(courseYear, startMonth, startDate);
        this.startDate = courseStartDate;

        /*
         * -----------------------------------------------------------------------------
         * -----------------
         */
        System.out.print("Enter the course's end month: ");
        int endMonth = scanner.nextInt();

        System.out.print("Enter the course's end date: ");
        int endDate = scanner.nextInt();

        LocalDate courseEndDate = LocalDate.of(courseYear, endMonth, endDate);
        this.endDate = courseEndDate;

        /*
         * -----------------------------------------------------------------------------
         * -----------------
         */
        System.out.print("Enter the intra's month: ");
        int intraMonth = scanner.nextInt();

        System.out.print("Enter the intra's date: ");
        int intraDate = scanner.nextInt();

        LocalDate courseIntraDate = LocalDate.of(courseYear, intraMonth, intraDate);
        this.intraDate = courseIntraDate;

        /*
         * -----------------------------------------------------------------------------
         * -----------------
         */
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
        return "\nCourse: " + this.getCourseSubject() + this.getCourseNumber() + "\n"
                + "Start Date: " + this.getStartDate().format(formattedDate) + "\n"
                + "End Date: " + this.getEndDate().format(formattedDate) + "\n"
                + "Intra Date: " + this.getIntraDate().format(formattedDate) + "\n"
                + "Final Date: " + this.getFinalDate().format(formattedDate) + "\n";
    }

    @Override
    public String toString() {
        return this.courseSubject + this.courseNumber;
    }

}