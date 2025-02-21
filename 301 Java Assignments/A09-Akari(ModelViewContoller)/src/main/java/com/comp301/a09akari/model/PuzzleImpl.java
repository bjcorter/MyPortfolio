package com.comp301.a09akari.model;

import javafx.scene.control.Cell;

public class PuzzleImpl implements Puzzle {

  private int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board.length > 0 ? board[0].length : 0;
  }

  @Override
  public int getHeight() {
    // height = length ig
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r >= getWidth() || r < 0 || c >= getHeight() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    int cell = board[r][c];
    if (cell >= 0 && cell <= 4) {
      return CellType.CLUE;
    } else if (cell == 5) {
      return CellType.WALL;
    } else {
      return CellType.CORRIDOR;
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r > getWidth() || r < 0 || c > getHeight() || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return board[r][c];
  }
}
