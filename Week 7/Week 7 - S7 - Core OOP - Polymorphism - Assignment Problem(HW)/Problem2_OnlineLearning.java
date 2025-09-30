class Course {
    String title, instructor;
    Course(String title, String instructor) {
        this.title = title; this.instructor = instructor;
    }
    void showProgress() {
        System.out.println("Generic course progress...");
    }
}
class VideoCourse extends Course {
    int percentage, watchTime;
    VideoCourse(String t, String i, int p, int w) { super(t,i); percentage=p; watchTime=w; }
    @Override void showProgress() {
        System.out.println(title + ": " + percentage + "% completed, WatchTime=" + watchTime+" hrs");
    }
}
class InteractiveCourse extends Course {
    int quizzes, projects;
    InteractiveCourse(String t, String i, int q, int p) { super(t,i); quizzes=q; projects=p; }
    @Override void showProgress() {
        System.out.println(title + ": Quizzes=" + quizzes + ", Projects=" + projects);
    }
}
class ReadingCourse extends Course {
    int pages, notes;
    ReadingCourse(String t, String i, int p, int n) { super(t,i); pages=p; notes=n; }
    @Override void showProgress() {
        System.out.println(title + ": Pages Read=" + pages + ", Notes=" + notes);
    }
}
class CertificationCourse extends Course {
    int attempts; boolean certified;
    CertificationCourse(String t, String i, int a, boolean c) { super(t,i); attempts=a; certified=c; }
    @Override void showProgress() {
        System.out.println(title + ": Exam Attempts=" + attempts + ", Certified=" + certified);
    }
}

public class Problem2_OnlineLearning {
    public static void main(String[] args) {
        Course[] courses = {
            new VideoCourse("Java", "Mr. A", 80, 15),
            new InteractiveCourse("Python", "Ms. B", 5, 2),
            new ReadingCourse("History", "Mr. C", 120, 20),
            new CertificationCourse("AWS", "Ms. D", 2, true)
        };
        for (Course c : courses) c.showProgress();
    }
}
