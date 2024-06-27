package com.example.sudoku.GameMechanics;
public class Checker{
    private int[][] map;
    /**
     * checks if there are any repeating elements in a given array
     * @param array an array of Integers (numbers from 1 -> 9)
     * @return false if there are any repeating numbers
     */
    public boolean checkArray(int[] array){
        boolean arrayValid = true;
        boolean[] validDigits =  new boolean[9];
        for(int index = 0; index < validDigits.length; index++){
            validDigits[index] = false;
        }
        for(int index : array){
            int digit = index - 1;
            if(digit >= 0 && digit < validDigits.length){
                if(validDigits[digit] == true){
                    arrayValid = false;
                }else{
                    validDigits[digit] = true;
                }
            }else{
                arrayValid = false;
            }
        }
        return arrayValid;
    }
    /**
     * checks if there are any repeating elements in the row
     * @param rowIndex the index of the row (0 at the top, 8 at the bottom)
     * @return false if the row contains any repeating elements
     */
    public boolean checkRow(int rowIndex){
        boolean rowValid = checkArray(map[rowIndex]);
        if(rowValid == false){
            System.out.println("row number " + rowIndex + " is invalid: ");
            for(int index : map[rowIndex]){
                System.out.print(index);
            }
            System.out.println();
        }
        return rowValid;
    }
    /**
     * checks if there are any repeating elements in the given column
     * @param comlunIndex the index of the column (0 left to 8 right)
     * @return false if the column has any repeating elements
     */
    public boolean checkColumn(int comlunIndex){
        int[] column = new int[map.length];
        for(int columnIndex = 0; columnIndex < map.length; columnIndex++){
            column[columnIndex] = map[columnIndex][comlunIndex];
        }
        if(checkArray(column) == false){
            System.out.println("column number " + comlunIndex + " is invalid");
            for(int index : column){
                System.out.print(index);
            }
            System.out.println();
        }
        return checkArray(column);
    }
    public boolean checkBlock(int blockX, int blockY){
        int leftX = blockX - blockX % 3;
        int leftY = blockY - blockY % 3;
        int[] block = new int[9];
        int index = 0;
        
        for(int xIndex = 0; xIndex < 3; xIndex++){
            for(int yIndex = 0; yIndex < 3; yIndex++){
                block[index] = map[leftX + xIndex][leftY + yIndex];
                index++;
            }   
        }

        if(checkArray(block) == false){
            System.out.println("block at " + blockX + " " + blockY + " is invalid");
            for(int xIndex = 0; xIndex < 3; xIndex++){
                for(int yIndex = 0; yIndex < 3; yIndex++){
                    System.out.print(map[leftX + xIndex][leftY + yIndex]);
                }   
                System.out.println();
            }
        }
        return checkArray(block);
    }
    public boolean checkMap(int[][] map){
        boolean mapValid = true;
        this.map = map;
        for(int xIndex = 0; xIndex < map.length; xIndex++){
            for(int yIndex = 0; yIndex < map[xIndex].length; yIndex++){
                if(checkRow(yIndex) == false ||
                   checkColumn(xIndex) == false ||
                   checkBlock(xIndex,yIndex) == false){
                    mapValid = false;
                }
            }   
        }
        return mapValid;
    }
}