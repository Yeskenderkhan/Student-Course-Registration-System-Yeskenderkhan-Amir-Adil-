package app.model;

public class Course {
    private int id;
    private String title;
    private int maxStudents;

    public Course(int id, String title, int maxStudents) {
        this.id = id;
        this.title = title;
        this.maxStudents = maxStudents;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxStudents() {
        return maxStudents;
    }
}
