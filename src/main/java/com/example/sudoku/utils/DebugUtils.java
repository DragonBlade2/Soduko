package com.example.sudoku.utils;

public class DebugUtils {

    public static void quit(int quitMode){
        System.out.println(Const.QUIT_MODES[quitMode]);
        System.exit(quitMode);
    }
}
