package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class ControlView implements FXComponent {
  private final ClassicMvcController controller;
  private final HBox root;
  private final Label messageLabel;

  public ControlView(ClassicMvcController controller) {
    this.controller = controller;
    this.root = new HBox();
    this.messageLabel = new Label();
    this.messageLabel.setStyle("-fx-background-color: lightgray; -fx-padding: 10px;");
    this.messageLabel.setVisible(false);
    initializeControls();
  }

  @Override
  public Parent render() {
    return root;
  }

  private void initializeControls() {
    Button prevButton = new Button("Previous Puzzle");
    prevButton.setOnAction(event -> controller.clickPrevPuzzle());

    Button nextButton = new Button("Next Puzzle");
    nextButton.setOnAction(event -> controller.clickNextPuzzle());

    Button resetButton = new Button("Reset Puzzle");
    resetButton.setOnAction(event -> controller.clickResetPuzzle());

    Button checkButton = new Button("Check");
    checkButton.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent actionEvent) {
            checkSolution();
          }
        });

    root.getChildren().addAll(prevButton, nextButton, resetButton, checkButton, messageLabel);
  }

  private void checkSolution() {
    if (controller.checkSolution()) {
      showMessage("Puzzle is solved!");
    } else {
      showMessage("Puzzle is not solved.");
    }
  }

  private void showMessage(String message) {
    messageLabel.setText(message);
    messageLabel.setVisible(true);

    Timeline timeline =
        new Timeline(
            new KeyFrame(
                Duration.seconds(3),
                evt -> {
                  messageLabel.setVisible(false);
                  messageLabel.setText("");
                }));
    timeline.play();
  }
}
