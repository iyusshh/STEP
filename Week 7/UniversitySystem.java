// File: UniversitySystem.java

class Person {
    protected String name;
    protected int age;
    protected String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public void introduce() {
        System.out.println("Hi! I'm " + name + ", " + age + " years old.");
    }

    public void getContactInfo() {
        System.out.println("Email: " + email);
    }
}

class Student extends Person {
    private String studentId;
    private String major;

    public Student(String name, int age, String email, String studentId, String major) {
        super(name, age, email);
        this.studentId = studentId;
        this.major = major;
    }

    public void attendLecture() {
        System.out.println(name + " is attending " + major + " lecture.");
    }

    public void submitAssignment() {
        System.out.println("Assignment submitted by " + studentId);
    }
}

class Professor extends Person {
    private String department;

    public Professor(String name, int age, String email, String department) {
        super(name, age, email);
        this.department = department;
    }

    public void conductClass() {
        System.out.println("Prof. " + name + " is teaching " + department);
    }
}

public class UniversitySystem {
    public static void main(String[] args) {
        Student studentAlice = new Student("Modi", 60, "modi@gov.in", "CS2021", "Computer Science");
        Person person = studentAlice;

        System.out.println("--- Accessing through Person reference ---");
        person.introduce();
        person.getContactInfo();

        System.out.println("Accessed name directly: " + person.name);
        
        // person.attendLecture();
    }
}