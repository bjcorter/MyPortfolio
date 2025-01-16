import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManagerApp extends Application {

    private ObservableList<Task> currentTasks;
    private ObservableList<Task> completedTasks;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        currentTasks = FXCollections.observableArrayList();
        completedTasks = FXCollections.observableArrayList();

        String taskFile = "tasks.json";

        loadTasksFromFile(taskFile);

        ListView<Task> currentTaskListView = new ListView<>(currentTasks);
        currentTaskListView.setCellFactory(param -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                setText(empty || task == null ? null : task.getTitle() + " (Due: " + task.getDueDate() + ")");
            }
        });

        ListView<Task> completedTaskListView = new ListView<>(completedTasks);
        completedTaskListView.setCellFactory(param -> new ListCell<Task>() {
            @Override
            protected void updateItem(Task task, boolean empty) {
                super.updateItem(task, empty);
                setText(empty || task == null ? null : task.getTitle() + " (Completed)");
            }
        });

        TextField titleField = new TextField();
        titleField.setPromptText("Task Title");

        DatePicker dueDatePicker = new DatePicker();
        dueDatePicker.setPromptText("Due Date");

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> {
            String title = titleField.getText();
            LocalDate dueDate = dueDatePicker.getValue();
            if (title.isEmpty() || dueDate == null) {
                showAlert("Error", "Please enter a title and due date!");
            } else {
                currentTasks.add(new Task(title, dueDate));
                titleField.clear();
                dueDatePicker.setValue(null);
                saveTasksToFile(taskFile);
            }
        });

        Button deleteButton = new Button("Delete Task");
        deleteButton.setOnAction(e -> {
            Task selectedTask = currentTaskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                currentTasks.remove(selectedTask);
                saveTasksToFile(taskFile);
            } else {
                showAlert("Error", "No task selected!");
            }
        });

        Button completeButton = new Button("Mark as Completed");
        completeButton.setOnAction(e -> {
            Task selectedTask = currentTaskListView.getSelectionModel().getSelectedItem();
            if (selectedTask != null) {
                currentTasks.remove(selectedTask);
                selectedTask.toggleIsDone();
                completedTasks.add(selectedTask);
                saveTasksToFile(taskFile);
            } else {
                showAlert("Error", "No task selected!");
            }
        });

        VBox inputLayout = new VBox(5, titleField, dueDatePicker, addButton, deleteButton, completeButton);
        inputLayout.setPrefWidth(300);

        VBox taskListsLayout = new VBox(10,
            new Label("Current Tasks"), currentTaskListView,
            new Label("Completed Tasks"), completedTaskListView
        );

        BorderPane root = new BorderPane();
        root.setCenter(taskListsLayout);
        root.setRight(inputLayout);

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setTitle("Task Manager");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void saveTasksToFile(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule()); // Register module
        try {
            List<Task> allTasks = new ArrayList<>();
            allTasks.addAll(currentTasks);
            allTasks.addAll(completedTasks);
    
            mapper.writeValue(new File(filename), allTasks);
            System.out.println("Tasks saved to file: " + filename);
        } catch (IOException e) {
            showAlert("Error", "Failed to save tasks to file: " + e.getMessage());
        }
    }
    

    private void loadTasksFromFile(String filename) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule()); // Register module
        try {
            File file = new File(filename);
            if (file.exists()) {
                Task[] tasks = mapper.readValue(file, Task[].class);
                for (Task task : tasks) {
                    if (task.checkIfDone()) {
                        completedTasks.add(task);
                    } else {
                        currentTasks.add(task);
                    }
                }
                System.out.println("Tasks loaded from file: " + filename);
            }
        } catch (IOException e) {
            showAlert("Error", "Failed to load tasks from file: " + e.getMessage());
        }
    }
    
}
