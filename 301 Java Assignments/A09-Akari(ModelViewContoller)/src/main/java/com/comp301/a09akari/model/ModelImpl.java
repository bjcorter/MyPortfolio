package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private PuzzleLibrary library;
  private int active_puzzle_index;
  private boolean[][] lamp_locations;
  private List<ModelObserver> observers;
  private Puzzle current;

  public ModelImpl(PuzzleLibrary library) {
    this.library = library;
    this.active_puzzle_index = 0;
    this.current = library.getPuzzle(active_puzzle_index);
    this.lamp_locations = new boolean[current.getHeight()][current.getWidth()];
    this.observers = new ArrayList<>();
  }

  @Override
  public void addLamp(int r, int c) {
    if (!isValidCell(r, c)) {
      throw new IndexOutOfBoundsException();
    }
    if (current.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamp_locations[r][c] = true;
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public void removeLamp(int r, int c) {
    if (!isValidCell(r, c)) {
      throw new IndexOutOfBoundsException();
    }
    if (current.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamp_locations[r][c] = false;

    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public boolean isLit(int r, int c) {
    if (!isValidCell(r, c)) {
      throw new IndexOutOfBoundsException();
    }
    if (current.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (lamp_locations[r][c]) {
      return true;
    }
    for (int i = 0; i < current.getHeight(); i++) {
      if (lamp_locations[i][c]) {
        if (isPathClear(r, c, i, c)) {
          return true;
        }
      }
    }

    for (int j = 0; j < current.getWidth(); j++) {
      if (lamp_locations[r][j]) {
        if (isPathClear(r, c, r, j)) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean isPathClear(int r1, int c1, int r2, int c2) {
    int dr = Integer.compare(r2, r1);
    int dc = Integer.compare(c2, c1);

    for (int i = r1 + dr, j = c1 + dc; i != r2 || j != c2; i += dr, j += dc) {
      if (current.getCellType(i, j) != CellType.CORRIDOR) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (!isValidCell(r, c)) {
      throw new IndexOutOfBoundsException();
    }
    if (current.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lamp_locations[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (!isValidCell(r, c)) {
      throw new IndexOutOfBoundsException();
    }
    if (!lamp_locations[r][c]) {
      throw new IllegalArgumentException();
    }

    for (int j = 0; j < current.getWidth(); j++) {
      if (j != c && lamp_locations[r][j]) {
        if (isPathClear(r, c, r, j)) {
          return true;
        }
      }
    }

    for (int i = 0; i < current.getHeight(); i++) {
      if (i != r && lamp_locations[i][c]) {
        if (isPathClear(r, c, i, c)) {
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return current;
  }

  @Override
  public int getActivePuzzleIndex() {
    return active_puzzle_index;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= library.size()) {
      throw new IndexOutOfBoundsException();
    }
    active_puzzle_index = index;
    this.current = library.getPuzzle(active_puzzle_index);
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    for (int i = 0; i < current.getHeight(); i++) {
      for (int j = 0; j < current.getWidth(); j++) {
        lamp_locations[i][j] = false;
      }
    }
    for (ModelObserver observer : observers) {
      observer.update(this);
    }
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < current.getHeight(); i++) {
      for (int j = 0; j < current.getWidth(); j++) {
        if (current.getCellType(i, j) == CellType.CLUE && !isClueSatisfied(i, j)) {
          return false;
        }
      }
    }

    for (int i = 0; i < current.getHeight(); i++) {
      for (int j = 0; j < current.getWidth(); j++) {
        if (current.getCellType(i, j) == CellType.CORRIDOR && !isLit(i, j)) {
          return false;
        }
      }
    }
    for (int i = 0; i < current.getHeight(); i++) {
      for (int j = 0; j < current.getWidth(); j++) {
        if (current.getCellType(i, j) == CellType.CORRIDOR && isLamp(i, j) && isLampIllegal(i, j)) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (!isValidCell(r, c)) {
      throw new IndexOutOfBoundsException();
    }

    if (current.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }

    int clue = current.getClue(r, c);
    int adjLamps = 0;

    if (isValidCell(r, c + 1) && lamp_locations[r][c + 1]) {
      adjLamps++;
    }
    if (isValidCell(r, c - 1) && lamp_locations[r][c - 1]) {
      adjLamps++;
    }
    if (isValidCell(r + 1, c) && lamp_locations[r + 1][c]) {
      adjLamps++;
    }
    if (isValidCell(r - 1, c) && lamp_locations[r - 1][c]) {
      adjLamps++;
    }

    return clue == adjLamps;
  }

  private boolean isValidCell(int r, int c) {
    return r >= 0 && r < current.getHeight() && c >= 0 && c < current.getWidth();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }
}
