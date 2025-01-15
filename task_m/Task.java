import java.time.LocalDate;

public class Task{

    private String title;
    private LocalDate dueDate;
    private boolean isDone;
    
    public Task(String title, LocalDate dueDate, boolean isDone){
        this.title = title;
        this.dueDate = dueDate;
        this.isDone = false;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public void setDueDate(LocalDate newDueDate){
        this.dueDate = newDueDate;
    }

    public boolean checkIfDone(){
        return isDone;
    }

    public void toggleIsDone(){
        this.isDone = !isDone;
    }

    public String toString(){
        return "Task - " + title + "Due date - " + dueDate + "Done? - " + isDone;
    }
    
}
