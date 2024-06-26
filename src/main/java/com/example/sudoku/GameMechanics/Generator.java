package com.example.sudoku.GameMechanics;

public class Generator {

    //Generator the completed sudo board
    public int[][] board = new int[9][9];

    public void workFFS() {
        populate();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                boolean check = checkSum();
                board[i][j] = (int) (Math.random() * 9 + 1);
                while (check == false) {
                    board[i][j] = (int) (Math.random() * 9 + 1);
                    check = checkSum();
                }
            }
        }
        print();
    }

    private void populate() { //fills the board with "0"s
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = 0;

            }
        }
        System.out.println("pop");
    }

    private boolean checkSum() {
        boolean output = false;
        output = checkRow();
        output = checkColumn();
        output = checkSquare();
        return output;
    }

    private boolean checkRow() {
        boolean output = true;
        int m=0;int tempCount = 0;
        for (m = 1; m <= 9; m++) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {



                    int temp = m;
                    if (board[j][i] == temp) {
                        tempCount++;
                    }
                    if (tempCount > 1) {
                        output = false;
                    }
                }
            }
        }
        return output;
    }

    private boolean checkColumn() {
        boolean output = true;
        //check if there are 2 of any numbers besides 0
        int m=0;
        int tempCount = 0;
        for (m = 1; m <= 9; m++) {
            int temp = m;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {





                    if (board[i][j] == temp) {
                        tempCount++;
                    }
                    if (tempCount > 1) {
                        output = false;
                    }
                }
            }
        }
        return output;
    }

    private boolean checkSquare() {
        boolean output = true;
        //check if there are 2 of any numbers besides 0
        for (int m = 1; m <= 9; m++) {
            int temp = m;
            int tempCount = 0;

            for (int l = 0; l < 3; l++) {
                for (int k = 0; k < 3; k++) {
                    for (int i = k; i < 3 * k; i++) {
                        for (int j = l; j < 3 * l; j++) {

                            if (board[i][j] == temp) {
                                tempCount++;
                            }
                            if (tempCount > 1) {
                                output = false;
                            }
                        }
                    }
                }
            }
        }
        return output;
    }
    private void print(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]+";");
            }
            System.out.print("\n");
        }
    }
}
