import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Object Course represents an instance of course for student to enroll
 * 
 */
public class Course {

    private String courseSubject;
    private int courseNumber;
    private int courseCredits;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate intraDate;
    private LocalDate finalDate;

    private List<List<Object>> courseHours = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    /**
     * Contructor of Course class
     * 
     * @param courseSubject: name of the subject
     * @param courseNumber:  number of the course
     * @param courseCredits: number of credits
     */
    public Course(String courseSubject, int courseNumber, int courseCredits) {
        this.setCourseSubject(courseSubject);
        this.setCourseNumber(courseNumber);
        this.setCourseCredits(courseCredits);
    }

    /**
     * Getter of courseSubject
     * 
     * @return name of the course's subject
     */
    public String getCourseSubject() {
        return this.courseSubject;
    }

    /**
     * Getter of courseCredits
     * 
     * @return number of credits this course has
     */
    public int getCourseCredits() {
        return this.courseCredits;
    }

    /**
     * Getter of courseNumber
     * 
     * @return the course's number
     */
    public int getCourseNumber() {
        return this.courseNumber;
    }

    // -------------------------------------------------------------------------------------------

    /**
     * Setter for courseNumber
     * 
     * @param courseNumber: the course's number to set to
     */
    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    /**
     * Setter for courseSubject
     * 
     * @param courseSubject: the course's subject to set to
     */
    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    /**
     * Setter for courseCredits
     * 
     * @param courseCredits: the number of credits to set to
     */
    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }

    // -------------------------------------------------------------------------------------------

    /**
     * Getter for startDate
     * 
     * @return the start date of the course
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Getter for endDate
     * 
     * @return the end date of the course
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Getter for intraDate
     * 
     * @return the date of the midterm exam
     */
    public LocalDate getIntraDate() {
        return this.intraDate;
    }

    /**
     * Getter for finalDate
     * 
     * @return the date of the final exam
     */
    public LocalDate getFinalDate() {
        return this.finalDate;
    }

    /**
     * Getter for course's hours
     * 
     * @return a 2D arrayList of all the course's hours in the week
     */
    public List<List<Object>> getCourseHours() {
        return this.courseHours;
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
            System.out.print("\nEnter the DAY OF THE WEEK to add course's hour for " + this.toString() + ": ");
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
            System.out.print(
                    "Enter the course's START HOUR (HH:MM) on " + DayOfWeek.of(dayOfWeek) + ": ");
            startHourInput = scanner.next();

            try {
                LocalTime startHour = LocalTime.parse(startHourInput);
                courseHour.add(startHour);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Enter a valid input of hour!");
                continue;
            }
        }

        // -------------------------------------------------------------------------------------------
        // Get and validate input for course's end hour
        while (true) {
            String endHourInput;
            System.out.print(
                    "Enter the course's END HOUR (HH:MM) on " + DayOfWeek.of(dayOfWeek) + ": ");
            endHourInput = scanner.next();

            try {
                LocalTime endHour = LocalTime.parse(endHourInput);
                courseHour.add(endHour);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Enter a valid input of hour!");
                continue;
            }
        }

        // -------------------------------------------------------------------------------------------
        // Set which type of course this is for (Theory / Practical / LAB)
        String courseHourType;
        do {
            System.out.print(
                    this.toString() + " course's hour of:\n T - Theory course; P - Practical course; L - LAB:  ");
            while (!scanner.hasNext()) {
                scanner.next();
            }
            courseHourType = scanner.next();
        } while (!courseHourType.equalsIgnoreCase("T") && !courseHourType.equalsIgnoreCase("P") && !courseHourType
                .equalsIgnoreCase("L"));

        courseHourType = courseHourType.toUpperCase();
        switch (courseHourType) {
            case "T":
                courseHour.add("Theory");
                break;
            case "P":
                courseHour.add("Practical");
                break;
            case "L":
                courseHour.add("LAB");
                break;
        }

        courseHours.add(courseHour);
    }

    /**
     * Set one of the dates for the course
     * 1 - Start date, 2 - End date
     * 3 - Intra exam, 4 - Final exam
     * 
     */
    public void setCourseDate() {
        LocalDate dateInput;
        while (true) {
            int answer;
            do {
                System.out.print("\nFor " + this.toString() + ": " + " 1 - Set a date; 2 - Quit:  ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Not a number! Try again");
                    scanner.next();
                }
                answer = scanner.nextInt();
            } while (answer < 1 || answer > 2);

            if (answer == 2) {
                break;
            } else {
                System.out.print("Enter a date(YYYY-MM-DD): ");
                String dateString = scanner.next();

                try {
                    dateInput = LocalDate.parse(dateString);
                } catch (DateTimeParseException e) {
                    System.out.println("Enter a valid input of date and month! (YYYY-MM-DD)");
                    continue;
                }

                int choice;
                do {
                    System.out.println("Which date do you want to set?\n 1-Start; 2-End; 3-Intra; 4-Final");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Not a number! Try again");
                        scanner.next();
                    }
                    choice = scanner.nextInt();
                } while (choice < 1 || choice > 4);

                if (choice == 1) {
                    this.startDate = dateInput;
                    continue;
                } else if (choice == 2) {
                    this.endDate = dateInput;
                    continue;
                } else if (choice == 3) {
                    this.intraDate = dateInput;
                    continue;
                } else if (choice == 4) {
                    this.finalDate = dateInput;
                    continue;
                }
            }

        }

    }

    // Date formatter as a string (e.g: 6-Jul-2022)
    DateTimeFormatter formattedDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    /**
     * Display all the course's infos
     * 
     */
    public String displayCourseInfos() {
        List<List<Object>> courseDays = this.getCourseHours();
        return "\nCourse: " + this.toString() + ". Nombre de crédits: " + this.getCourseCredits() + "\n"
                + "Start Date: " + this.getStartDate().format(formattedDate) + "\n"
                + "End Date: " + this.getEndDate().format(formattedDate) + "\n"
                + "Intra Date: " + this.getIntraDate().format(formattedDate) + "\n"
                + "Final Date: " + this.getFinalDate().format(formattedDate) + "\n"
                + "Course hours: " + courseDays + "\n";
    }

    @Override
    public String toString() {
        return this.getCourseSubject() + this.getCourseNumber();
    }

}