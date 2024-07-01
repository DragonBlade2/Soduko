package com.example.sudoku.GameMechanics;

import com.example.sudoku.utils.Const;

public class Board {
    private int curCellX = 0;
    private int curCellY = 0;
    
    public static int NUMBER_OF_CELLS = Const.NUMBER_OF_CELLS;
    private Cell[][] cells = new Cell[NUMBER_OF_CELLS][NUMBER_OF_CELLS];
    private boolean[][][] possibleDigits = new boolean[9][9][9];
    public Board(){
        for(int x = 0; x < NUMBER_OF_CELLS; x++){
            for(int y = 0; y < NUMBER_OF_CELLS; y++){
                cells[x][y] = new Cell();
            }
        }
    }
    public Board(int[][] board){
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board[x].length; y++){
                if(board[x][y] == 0){
                    cells[x][y] = new Cell();
                }else{
                    cells[x][y] = new Cell(board[x][y]);
                }
            }
        }
    }
    private void updatePossibleValues(){
        for(int number = 0; number < 9; number++){
            //System.out.println(number + ":");
            for(int x = 0; x < 9; x++){
                for(int y = 0; y < 9; y++){
                    possibleDigits[number][x][y] = cells[x][y].digitPossible(number + 1);
                    if(cells[x][y].digitPossible(number + 1)){
                        //System.out.print(number + 1);
                    }else{
                        //System.out.print(".");
                    }
                }
                //System.out.println();
            }   
            //System.out.println("\n");
        }
    }
    public int getCurCellX(){
        return curCellX;
    }
    public int getCurCellY(){
        return curCellY;
    }
    public Cell getCell(int x, int y){
        curCellX = x;
        curCellY = y;
        return cells[x][y];
    }
    /**
     * fetches all the numbers from a specific row
     * @param rowIndex
     * @return
     */
    public int[] getRowDigits(int rowIndex){
        Cell[] row = getRow(rowIndex);
        int[] digits = new int[row.length];
        for(int index = 0; index < row.length; index++){
            digits[index] = row[index].getValue();
        }
        return digits;
    }
    /**
     * fetches all the numbers from a specific row
     * @param rowIndex
     * @return
     */
    public Cell[] getRow(int rowIndex){
        Cell[] row = new Cell[NUMBER_OF_CELLS];
        for(int index = 0; index < NUMBER_OF_CELLS; index++){
            row[index] = getCell(index, rowIndex);
        }
        return row;
}
    /**
     * fetches all the numbers from a specific column
     * @param columnIndex
     * @return
     */
    public int[] getColumnDigits(int columnIndex){
        Cell[] column = getColumn(columnIndex);
        int[] digits = new int[column.length];
        for(int index = 0; index < column.length; index++){
            digits[index] = column[index].getValue();
        }
        return digits;
    }
    
    /**
     * fetches all the cells from a specific column
     * @param columnIndex
     * @return an array representing all the cells in the column
     */
    public Cell[] getColumn(int columnIndex){
        Cell[] column = new Cell[NUMBER_OF_CELLS];
        for(int index = 0; index < NUMBER_OF_CELLS; index++){
            column[index] = getCell(columnIndex,index);
        }
        return column;
    }
    /**
     * fetches all the numbers in the block contained by a cell
     * @param cellX the x-index of the cell
     * @param cellY the y-index of the cell
     * @return a one-dimensional array representing the numbers in the block
     */
    public int[] getBlockDigits(int cellX, int cellY){
        Cell[] block = getBlock(cellX, cellY);
        int[] digits = new int[block.length];
        for(int index = 0; index < block.length; index++){
            digits[index] = block[index].getValue();
        }
        return digits;
    }
    /**
     * fetches all the cells in the block contained by a cell
     * @param cellX the x-index of the cell
     * @param cellY the y-index of the cell
     * @return a one-dimensional array representing the cells in the block
     */
    public Cell[] getBlock(int cellX, int cellY){
         Cell[] block = new Cell[9];
         int leftX = cellX - cellX%3;
         int leftY = cellY - cellY%3;
         int index = 0;
         for(int x = 0; x < 3; x++){
             for(int y = 0; y < 3; y++){
                 block[index] = getCell(leftX + x, leftY + y);
                 index++;
             }
         }
         return block;
     }
    
    public int[][] getMap(){
        int[][] map = new int[cells.length][cells[0].length];
        for(int xIndex = 0; xIndex < cells.length; xIndex++){
            for(int yIndex = 0; yIndex < cells.length; yIndex++){
                map[xIndex][yIndex] = getCell(xIndex, yIndex).getValue();
            }   
        }
        return map;
    }
    public void computeCell(int x, int y){
        Cell curCell = getCell(x,y);
        if(curCell.getValue() == 0){
            int[] row = getRowDigits(y);
            int[] column = getColumnDigits(x);
            int[] block = getBlockDigits(x,y);
            curCell.computeForRow(row);
            curCell.computeForColumn(column);
            curCell.computeForBlock(block);
            //curCell.compute();
            updatePossibleValues();
        }
    }
}
