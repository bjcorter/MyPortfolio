package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import com.comp301.a09akari.model.Puzzle;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PuzzleView implements FXComponent, ModelObserver {
  private final ClassicMvcController controller;
  private final Model model;
  private final GridPane gridPane;

  public PuzzleView(ClassicMvcController controller, Model model) {
    this.controller = controller;
    this.model = model;
    this.model.addObserver(this);

    this.gridPane = new GridPane();
    renderPuzzle();
  }

  @Override
  public Parent render() {
    return gridPane;
  }

  private void renderPuzzle() {
    gridPane.getChildren().clear();
    Puzzle puzzle = model.getActivePuzzle();
    for (int i = 0; i < puzzle.getHeight(); i++) {
      for (int j = 0; j < puzzle.getWidth(); j++) {
        Label cellLabel = new Label();
        cellLabel.setPrefSize(30, 30);
        cellLabel.setAlignment(Pos.CENTER);

        switch (puzzle.getCellType(i, j)) {
          case WALL:
            cellLabel.setStyle("-fx-background-color: black;");
            break;
          case CORRIDOR:
            if (model.isLit(i, j)) {
              cellLabel.setStyle("-fx-background-color: yellow;");
            } else {
              cellLabel.setStyle("-fx-background-color: white;");
            }
            break;
          case CLUE:
            int clue = puzzle.getClue(i, j);
            cellLabel.setText(Integer.toString(clue));
            cellLabel.setStyle("-fx-background-color: lightgray;");
            break;
        }

        if (model.getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR && model.isLamp(i, j)) {
          try {
            ImageView imageView =
                new ImageView(new Image(new FileInputStream("src/main/resources/light-bulb.png")));
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            cellLabel.setGraphic(imageView);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }
        }

        gridPane.add(cellLabel, j, i);

        int row = i;
        int col = j;
        cellLabel.setOnMouseClicked(
            event -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                controller.clickCell(row, col);
              }
            });
      }
    }
  }

  @Override
  public void update(Model model) {
    renderPuzzle();
  }
}
