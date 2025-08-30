import java.util.*;

class Subject {
    String subjectCode;
    String subjectName;
    int credits;
    String instructor;

    public Subject(String subjectCode, String subjectName, int credits, String instructor) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
        this.instructor = instructor;
    }
}

class Student {
    private String studentId;
    private String studentName;
    private String className;
    private String[] subjects;
    private double[][] marks;
    private double gpa;

    static int totalStudents = 0;
    static String schoolName = "ABC International School";
    static String[] gradingScale = {"A", "B", "C", "D", "F"};
    static double passPercentage = 40.0;

    public Student(String studentId, String studentName, String className, String[] subjects) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects;
        this.marks = new double[subjects.length][3];
        this.gpa = 0.0;
        totalStudents++;
    }

    public void addMarks(String subject, double[] subjectMarks) {
        for (int i = 0; i < subjects.length; i++) {
            if (subjects[i].equalsIgnoreCase(subject)) {
                marks[i] = subjectMarks;
                break;
            }
        }
    }

    public void calculateGPA() {
        double total = 0;
        int totalSubjects = subjects.length;

        for (int i = 0; i < totalSubjects; i++) {
            double sum = 0;
            for (double m : marks[i]) sum += m;
            double average = sum / marks[i].length;
            total += average;
        }
        gpa = total / totalSubjects / 10;
    }

    public String getGrade(double percentage) {
        if (percentage >= 90) return gradingScale[0];
        else if (percentage >= 75) return gradingScale[1];
        else if (percentage >= 60) return gradingScale[2];
        else if (percentage >= 40) return gradingScale[3];
        else return gradingScale[4];
    }

    public void generateReportCard() {
        System.out.println("\n==============================");
        System.out.println("Report Card - " + studentName);
        System.out.println("Student ID: " + studentId + " | Class: " + className);
        System.out.println("School: " + schoolName);
        System.out.println("==============================");
        double totalMarks = 0;
        double maxMarks = 0;
        for (int i = 0; i < subjects.length; i++) {
            double sum = 0;
            for (double m : marks[i]) sum += m;
            double percentage = sum / marks[i].length;
            totalMarks += percentage;
            maxMarks += 100;
            System.out.println(subjects[i] + " : " + String.format("%.2f", percentage) + "% | Grade: " + getGrade(percentage));
        }
        double overallPercentage = (totalMarks / subjects.length);
        System.out.println("--------------------------------");
        System.out.println("Overall GPA: " + String.format("%.2f", gpa));
        System.out.println("Overall Percentage: " + String.format("%.2f", overallPercentage) + "%");
        System.out.println("Final Grade: " + getGrade(overallPercentage));
        System.out.println("Promotion Status: " + (overallPercentage >= passPercentage ? "Promoted" : "Not Promoted"));
        System.out.println("==============================");
    }

    public static void setGradingScale(String[] newScale) {
        gradingScale = newScale;
    }

    public static double calculateClassAverage(Student[] students) {
        double total = 0;
        for (Student s : students) {
            s.calculateGPA();
            total += s.gpa;
        }
        return total / students.length;
    }

    public static void getTopPerformers(Student[] students, int count) {
        Arrays.sort(students, (a, b) -> Double.compare(b.gpa, a.gpa));
        System.out.println("\nTop " + count + " Performers:");
        for (int i = 0; i < count && i < students.length; i++) {
            System.out.println((i + 1) + ". " + students[i].studentName + " - GPA: " + String.format("%.2f", students[i].gpa));
        }
    }

    public static void generateSchoolReport(Student[] students) {
        System.out.println("\n=========== School Report ===========");
        System.out.println("Total Students: " + totalStudents);
        double avgGPA = calculateClassAverage(students);
        System.out.println("Average GPA: " + String.format("%.2f", avgGPA));
        getTopPerformers(students, 3);
        System.out.println("=====================================");
    }
}

public class StudentGradeManagementSystem {
    public static void main(String[] args) {
        String[] subjects = {"Math", "Science", "English", "Computer"};

        Student s1 = new Student("S001", "Aayush Rai", "10-A", subjects);
        Student s2 = new Student("S002", "Rahul Verma", "10-A", subjects);
        Student s3 = new Student("S003", "Priya Singh", "10-A", subjects);

        s1.addMarks("Math", new double[]{90, 85, 88});
        s1.addMarks("Science", new double[]{92, 89, 95});
        s1.addMarks("English", new double[]{80, 78, 85});
        s1.addMarks("Computer", new double[]{95, 97, 96});

        s2.addMarks("Math", new double[]{75, 70, 72});
        s2.addMarks("Science", new double[]{78, 80, 79});
        s2.addMarks("English", new double[]{65, 68, 70});
        s2.addMarks("Computer", new double[]{85, 83, 82});

        s3.addMarks("Math", new double[]{55, 60, 58});
        s3.addMarks("Science", new double[]{50, 55, 52});
        s3.addMarks("English", new double[]{62, 64, 61});
        s3.addMarks("Computer", new double[]{70, 72, 68});

        s1.calculateGPA();
        s2.calculateGPA();
        s3.calculateGPA();

        s1.generateReportCard();
        s2.generateReportCard();
        s3.generateReportCard();

        Student[] students = {s1, s2, s3};
        Student.generateSchoolReport(students);
    }
}
