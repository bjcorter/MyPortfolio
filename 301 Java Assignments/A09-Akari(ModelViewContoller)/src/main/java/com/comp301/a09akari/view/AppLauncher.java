package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import com.comp301.a09akari.SamplePuzzles;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    PuzzleLibrary library = new PuzzleLibraryImpl();
    Puzzle puzzle1 = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
    Puzzle puzzle3 = new PuzzleImpl(SamplePuzzles.PUZZLE_03);
    Puzzle puzzle5 = new PuzzleImpl(SamplePuzzles.PUZZLE_05);
    library.addPuzzle(puzzle1);
    library.addPuzzle(puzzle3);
    library.addPuzzle(puzzle5);

    Model model = new ModelImpl(library);
    ClassicMvcController controller = new ControllerImpl(model);

    PuzzleView puzzleView = new PuzzleView(controller, model);
    ControlView controlView = new ControlView(controller);

    HBox controlContainer = new HBox(controlView.render());
    controlContainer.setAlignment(Pos.CENTER);

    BorderPane root = new BorderPane();
    root.setCenter(puzzleView.render());

    Scene scene = new Scene(root, 800, 600);

    BorderPane.setMargin(puzzleView.render(), new Insets(50));

    root.setBottom(controlContainer);

    stage.setScene(scene);
    stage.setTitle("Akari");
    stage.show();
  }
}
