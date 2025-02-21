package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;

public class ControllerImpl implements ClassicMvcController {
  private final Model model;

  public ControllerImpl(Model model) {
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    int nextIndex = model.getActivePuzzleIndex() + 1;
    if (nextIndex < model.getPuzzleLibrarySize()) {
      model.setActivePuzzleIndex(nextIndex);
    } else {
      model.setActivePuzzleIndex(0);
    }
    model.resetPuzzle();
  }

  @Override
  public void clickPrevPuzzle() {

    int prevIndex = model.getActivePuzzleIndex() - 1;
    if (prevIndex >= 0) {
      model.setActivePuzzleIndex(prevIndex);
    } else {
      model.setActivePuzzleIndex(model.getPuzzleLibrarySize() - 1);
    }
    model.resetPuzzle();
  }

  @Override
  public void clickRandPuzzle() {
    int randIndex = (int) (Math.random() * model.getPuzzleLibrarySize());
    model.setActivePuzzleIndex(randIndex);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.isLamp(r, c)) {
      model.removeLamp(r, c);
    } else {
      model.addLamp(r, c);
    }
  }

  @Override
  public boolean checkSolution() {
    return model.isSolved();
  }
}
