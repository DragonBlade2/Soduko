package com.example.sudoku.GameMechanics;

import com.example.sudoku.utils.Const;

public class Game {
    private int NUMBER_OF_CELLS = Const.NUMBER_OF_CELLS;
    private Cell[][] cells = new Cell[NUMBER_OF_CELLS][NUMBER_OF_CELLS];
    public Game(){
        for(int x = 0; x < NUMBER_OF_CELLS; x++){
            for(int y = 0; y < NUMBER_OF_CELLS; y++){
                cells[x][y] = new Cell();
            }
        }
    }
    public Game(int[][] board){
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[x].length; y++){
                if(board[x][y] > 0){
                    cells[x][y] = new Cell(board[x][y]);
                }else{
                    cells[x][y] = new Cell();
                }
            }
        }
    }
    /**
     * fetches all the numbers from a specific row
     * @param rowIndex
     * @return
     */
    private int[] getRow(int rowIndex){
            int[] row = new int[NUMBER_OF_CELLS];
            for(int index = 0; index < NUMBER_OF_CELLS; index++){
                row[index] = cells[index][rowIndex].getValue();
            }
            return row;
    }
    /**
     * fetches all the numbers from a specific column
     * @param columnIndex
     * @return
     */
    private int[] getColumn(int columnIndex){
        int[] column = new int[NUMBER_OF_CELLS];
        for(int index = 0; index < NUMBER_OF_CELLS; index++){
            column[index] = cells[index][columnIndex].getValue();
        }
        return column;
    }

    /**
     * fetches all the numbers in the block contained by a cell
     * @param cellX the x-index of the cell
     * @param cellY the y-index of the cell
     * @return a two-dimensional array representing the numbers in the block
     */
    private int[][] getBlock(int cellX, int cellY){
       int[][] block = new int[3][3];
        int leftX = cellX - cellX%3;
        int leftY = cellY - cellY%3;
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                block[x][y] = cells[leftX + x][leftY + y].getValue();
            }
        }
        return block;
    }
    public void solve(){
        for(int x = 0; x < NUMBER_OF_CELLS; x++){
            for(int y = 0; y < NUMBER_OF_CELLS; y++){
                cells[x][y].computeForRow(getRow(y));
                cells[x][y].computeForColumn(getColumn(x));
                cells[x][y].computeForBlock(getBlock(x,y));
            }
        }
        for(int x = 0; x < NUMBER_OF_CELLS; x++){
            for(int y = 0; y < NUMBER_OF_CELLS; y++){
                if(cells[x][y].getValue() == 0){
                    cells[x][y].compute();

                }
            }
        }
    }
    private void computeForCell(int x, int y){
        cells[x][y].compute();
        /*
        if(cells[x][y].getValue() > 0){

        }
        */
    }
}
