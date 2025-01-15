import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> currentTasks;
    private ArrayList<Task> completedTasks;

    public TaskManager(){
        this.currentTasks = new ArrayList<>();
    }

    public void addTask(Task task){
        currentTasks.add(task);
    }

    public Task removeTask(String title){
        //returns the task so that it can be moved to completedTasks
        for(Task task : currentTasks){
            if (task.getTitle() == title){
                currentTasks.remove(task);
                return task;
            }
            else{
                System.out.println("Task Not Found");
                return null;
            }   
        }
        return null;
    }

    public void finishedTask(String title){
        Task task = removeTask(title);
        completedTasks.add(task);
    }

    public ArrayList<Task> getTasks(){
        return currentTasks;
    }

}
