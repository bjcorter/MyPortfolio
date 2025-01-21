import java.time.LocalDate;

public class Task {
    private String title;
    private LocalDate dueDate;
    private boolean isDone; // Completion status

    public Task(String title, LocalDate dueDate) {
        this.title = title;
        this.dueDate = dueDate;
        this.isDone = false; // Default to not completed
    }

    public Task() {
        // Default constructor for Jackson
    }
    

    // Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    // Toggle completion status
    public void toggleIsDone() {
        this.isDone = !this.isDone;
    }

    // Override toString for better representation
    @Override
    public String toString() {
        return title + (isDone ? " (Completed)" : " (Due: " + dueDate + ")");
    }
}
