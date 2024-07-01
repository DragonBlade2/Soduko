package com.example.sudoku.GameMechanics;

public class Solver {
    private Board board;
    public Solver(){
        board = new Board();
    }
    public Solver(int[][] map){
        board = new Board(map);
    }
    public void printMap(){
        for(int x = 0; x < Board.NUMBER_OF_CELLS; x++){
            for(int y = 0; y < Board.NUMBER_OF_CELLS; y++){
                System.out.print(board.getCell(x,y).getValue());
            }
            System.out.println();
        }
        System.out.println();
    }
    public void solve(){
        printMap();
        Checker checker = new Checker();
        while (checker.checkMap(board.getMap()) == false) {
            int numberSolved = 0;
            for(int x = 0; x < Board.NUMBER_OF_CELLS; x++){
                for(int y = 0; y < Board.NUMBER_OF_CELLS; y++){
                    //if the value of the cell can only be one thing, set the value of that cell to the one thing
                    if(board.getCell(x,y).getValue() == 0){
                        board.computeCell(x,y);
                    }else{
                        numberSolved++;
                    }
                }
            }
            for(int blockX = 0; blockX < 9; blockX += 3){
                for(int blockY = 0; blockY < 9; blockY += 3){
                    int[] missingDigits = getMissingDigits(board.getBlockDigits(blockX, blockY));
                    for(int digit : missingDigits){
                        Cell[] validCells = getCellsWithDigit(board.getBlock(blockX, blockY), digit);
                        if(validCells.length == 1){
                            validCells[0].setValue(digit);
                        }
                    }
                }    
            }
            for(int rowIndex = 0; rowIndex < 9; rowIndex++){
                int[] rowDigits = board.getRowDigits(rowIndex);
                int[] missingDigits = getMissingDigits(rowDigits);
                for(int digit : missingDigits){
                    Cell[] validCells = getCellsWithDigit(board.getRow(rowIndex),digit);
                    if(validCells.length == 1){
                        validCells[0].setValue(digit);
                    }
                }
            }
            for(int columnIndex = 0; columnIndex < 9; columnIndex++){
                int[] columnDigits = board.getColumnDigits(columnIndex);
                int[] missingDigits = getMissingDigits(columnDigits);
                for(int digit : missingDigits){
                    Cell[] validCells = getCellsWithDigit(board.getColumn(columnIndex),digit);
                    if(validCells.length == 1){
                        validCells[0].setValue(digit);
                    }
                }
            }
            System.out.println(numberSolved + " cells solved");
            printMap();
        }
        System.out.println("board solved");
        printMap();
    }
    /**
     * 
     * @param array
     * @return an array representing the numbers missing from the array
     */
    public int[] getMissingDigits(int[] array){
        int[] missingDigits = new int[0];
        for(int index = 1; index < 10; index++){
            if(!numberInArray(array, index)){
                missingDigits = addNumber(missingDigits, index);
            }
        }
        return missingDigits;
    }
    private int[] addNumber(int[] array, int number){
        int[] result = new int[array.length + 1];
        for(int index = 0; index < array.length; index++){
            result[index] = array[index];
        }
        result[result.length - 1] = number;
        return result;
    }
    private boolean numberInArray(int[] array, int integer){
        for(int index : array){
            if(index == integer){
                return true;
            }
        }
        return false;
    }
    /**
     * 
     * @param array
     * @param digit
     * @return an array of cells that could possibly be the digit given
     */
    public Cell[] getCellsWithDigit(Cell[] array, int digit){
        Cell[] cellsWithDigit = new Cell[0];
        for(Cell index : array){
            if(index.digitPossible(digit)){
                cellsWithDigit = addCell(cellsWithDigit, index);
            }
        }
        return cellsWithDigit;
    }
    private Cell[] addCell(Cell[] array, Cell cell){
        Cell[] result = new Cell[array.length + 1];
        for(int index = 0; index < array.length; index++){
            result[index] = array[index];
        }
        result[result.length - 1] = cell;
        return result;
    }
    public Board getBoard(){
        return board;
    }
}
