package com.example.sudoku.GameMechanics;

import com.example.sudoku.utils.Const;

public class Solver {
    private int curCellX = 0;
    private int curCellY = 0;
    private int NUMBER_OF_CELLS = Const.NUMBER_OF_CELLS;
    private Cell[][] cells = new Cell[NUMBER_OF_CELLS][NUMBER_OF_CELLS];
    public Solver(){
        for(int x = 0; x < NUMBER_OF_CELLS; x++){
            for(int y = 0; y < NUMBER_OF_CELLS; y++){
                cells[x][y] = new Cell();
            }
        }
    }
    public Solver(int[][] board){
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
    public int getCurCellX(){
        return curCellX;
    }
    public int getCurCellY(){
        return curCellY;
    }
    private Cell getCell(int x, int y){
        curCellX = x;
        curCellY = y;
        return cells[x][y];
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
    public int[][] getMap(){
        int[][] map = new int[cells.length][cells[0].length];
        for(int xIndex = 0; xIndex < cells.length; xIndex++){
            for(int yIndex = 0; yIndex < cells.length; yIndex++){
                map[xIndex][yIndex] = cells[xIndex][yIndex].getValue();
            }   
        }
        return map;
    }
    public void solve(){
        Checker checker = new Checker();
        while (checker.checkMap(getMap()) == false) {
            //set all the cells that can only be one thing to that one thing
            for(int x = 0; x < NUMBER_OF_CELLS; x++){
                for(int y = 0; y < NUMBER_OF_CELLS; y++){
                    if(cells[x][y].getValue() == 0){
                        cells[x][y].computeForRow(getRow(y));
                        cells[x][y].computeForColumn(getColumn(x));
                        cells[x][y].computeForBlock(getBlock(x,y));
                        cells[x][y].compute();
                    }
                }
            }
            //for every cell in the map, check if the row, column and block of the cell all require the number of the cell
        }
    }
    private int[] getMissingDigits(int[] array){
        int[] digitsInArray = new int[9];
        for(int index = 1; index < 10; index++){
            digitsInArray[index - 1] = index;
        }
        int numberOfDigits = 0;
        for(int index : array){
            if(index > 0){
                digitsInArray[index - 1] = 0;
                numberOfDigits++;
            }
        }

        for(int index = 0; index < numberOfDigits; index++){

        }
        return digitsInArray;
    }
}
