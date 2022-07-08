import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Course {

    private String courseSubject;
    private int courseNumber;
    private int courseYear = 0;

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
                    "\nWhat would you like to do? Enter the appropriate number choice: \n 1 - Set up course's dates; 2 - Set up course's hours; 3 - See course's infos; 4 - Quit ");

            while (!scanner.hasNextInt()) {
                System.out.println("Enter a valid number choice");
                scanner.next();
            }
            int answer = scanner.nextInt();

            if (answer == 1) {
                this.setCourseDate();
            } else if (answer == 2) {
                this.addCourseHour();
            } else if (answer == 3) {
                try {
                    System.out.println(this.getCourseDate());
                    System.out.println(this.getCourseHours());
                    continue;
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

    /**
     * Add course's hour to a day in the week
     * 1 - Monday, 2 - Tuesday, 3 - Wednesday, 4 - Thurday
     * 5 - Friday, 6 - Saturday, 7 - Sunday
     * 
     */
    public void addCourseHour() {
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
            String startHourInput;
            do {
                System.out.print(
                        "Enter the course's START HOUR on " + DayOfWeek.of(dayOfWeek) + " in the form of (HHMM): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Not a number. Try again!");
                    scanner.next();
                }
                startHourInput = scanner.next();
            } while (startHourInput.length() != 4);

            try {
                LocalTime startHour = LocalTime.of(Integer.parseInt(
                        startHourInput.substring(0, 2)),
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
            String endHourInput;
            do {
                System.out.print(
                        "Enter the course's END HOUR on " + DayOfWeek.of(dayOfWeek) + " in the form of (HHMM): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Not a number. Try again!");
                    scanner.next();
                }
                endHourInput = scanner.next();
            } while (endHourInput.length() != 4);

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

        courseHours.add(courseHour);

    }

    /**
     * Set one of the dates for the course
     * 1 - Start date, 2 - End date
     * 3 - Intra, 4 - Final
     * 
     */
    public void setCourseDate() {
        if (this.courseYear == 0) {
            int courseYear;
            do {
                System.out.print("Enter the course's year (YYYY): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Not a number. Try again");
                    scanner.next();
                }
                courseYear = scanner.nextInt();
                this.courseYear = courseYear;
            } while (String.valueOf(courseYear).length() != 4);
        }

        // -------------------------------------------------------------------------------------------
        // Validate date input from user
        while (true) {
            String dateInput;
            do {
                System.out.print("Enter the date in the form (MMDD): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Not a number. Try again!");
                    scanner.next();
                }
                dateInput = scanner.next();

            } while (dateInput.length() != 4);

            // Decide which date to set (Start date / End date / Intra / Final)
            System.out.println("Which date do you want to set?\n 1 - Start date; 2 - End date; 3 - Intra; 4 - Final");
            while (!scanner.hasNextInt()) {
                System.out.println("Enter a valid number choice");
                continue;
            }
            int answer = scanner.nextInt();

            try {
                LocalDate courseDate = LocalDate.of(courseYear, Integer.parseInt(dateInput.substring(0, 2)),
                        Integer.parseInt(dateInput.substring(2)));
                if (answer == 1) {
                    this.startDate = courseDate;
                    break;
                } else if (answer == 2) {
                    this.endDate = courseDate;
                    break;
                } else if (answer == 3) {
                    this.intraDate = courseDate;
                    break;
                } else if (answer == 4) {
                    this.finalDate = courseDate;
                    break;
                }
            } catch (DateTimeException e) {
                System.out.println("Enter a valid date and month!");
                continue;
            }
        }
    }

    // Display the course hours
    public String getCourseHours() {
        String result = "";
        for (List<Object> courseHour : courseHours) {
            result += "Course's hour on " + courseHour.get(0) + ": "
                    + courseHour.get(1) + "-" + courseHour.get(2);
        }

        return result;
    }

    // Date formatter as a string (e.g: 6-Jul-2022)
    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    // Display all the course's dates
    public String getCourseDate() {
        return "\nCourse: " + this.getCourseSubject() + this.getCourseNumber() + "\n"
                + "Start Date: " + this.getStartDate().format(formattedDate) + "\n"
                + "End Date: " + this.getEndDate().format(formattedDate) + "\n"
                + "Intra Date: " + this.getIntraDate().format(formattedDate) + "\n"
                + "Final Date: " + this.getFinalDate().format(formattedDate);
    }

    @Override
    public String toString() {
        return this.courseSubject + this.courseNumber;
    }

}