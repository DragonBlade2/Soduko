package com.example.sudoku.GameMechanics;
import com.example.sudoku.utils.Const;
public class Cell {
    private boolean[] possibleNumbers = new boolean[Const.NUMBER_OF_CELLS];
    /*
    this array holds a boolean value for every possible number in the puzzle.
        - the number is represented by the index in the array
        - if the value in the array for said number is true, then said number is not in the same row, column, or block as the cell
        - in other words, if the boolean for the number is true, then the value of the cell could be that number
    The possible numbers go from 0 to 8, so when computing the number, we take one away from the number when computing
     */
    private int value = 0;
    public Cell(){
        setValue(0);
        for(int index = 0; index < possibleNumbers.length; index++){
            possibleNumbers[index] = true;
        }
    }
    public Cell(int value){
        setValue(value);
        for(int index = 0; index < possibleNumbers.length; index++){
            possibleNumbers[index] = true;
        }
    }
    public int getValue(){
        return value;
    }
    public void setValue(int newValue){
        this.value = newValue;
    }

    /**
     * checks if the cell could possibly hold the given number
     * takes one away from the number to put it in the array
     * @param number the number of the cell you are checking
     */
    private void checkNumber(int number){
        if(number > 0 &&  number < possibleNumbers.length){
            boolean numberInPossibleNumbers = possibleNumbers[number - 1]; // check if the array of possible numbers contains the supplied number
            if(numberInPossibleNumbers){
                possibleNumbers[number] = false;
            }
        }
    }

    /**
     * checks if the cell could hold any of the numbers in the row
     * @param row
     */
    public void computeForRow(int[] row){
        for(int index : row){
            checkNumber(index);
        }
    }

    /**
     * checks if the cell could be any of the numbers in the column
     * @param column
     */
    public void computeForColumn(int[] column){
        computeForRow(column);
    }

    /**
     * checks if the cell could hold any of the numbers in the block
     * @param block
     */
    public void computeForBlock(int[][] block){
        for(int[] row : block){
            computeForRow(row);
        }
    }

    /**
     * goes through all the possible numbers, if there is only one possible number, then the cell sets itself to that number
     */
    public void compute(){
        int numberOfChoices = 0;
        for(boolean index : possibleNumbers){
            if(index){
                numberOfChoices++;
            }
        }
        if(numberOfChoices == 1){
            for(int index = 0; index < possibleNumbers.length; index++){
                if(possibleNumbers[index]){
                    setValue(index);
                }
            }
        }
    }
}
