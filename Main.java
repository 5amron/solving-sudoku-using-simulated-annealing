package my_sudoku;

import java.util.ArrayList;
import java.util.Random;




public class Main {


    private ArrayList<ArrayList<Integer>> my_array;
    private boolean [][]dont_move;



    public Main(boolean boool){

        my_array = new ArrayList <ArrayList<Integer>>();



        rand = new Random();



        dont_move = new boolean[9][9];
        for(int i = 0; i < 9; i++){
            my_array.add(new ArrayList<Integer>());

            for(int j = 1; j < 10; j++){
                my_array.get(i).add(new Integer(j));
            }
        }

        if(boool){
            first_sudoku();
        }
        else
            sudoku_board = new int[9][9];
    }


    public Main(Main mm){


        sudoku_board = new int[9][9];
        pre_b = mm.getpre_b();
        sud_copy(mm);
    }
    private Random rand;

    private int[][] pre_b;

    public void sud_copy(Main mm){
        int [][]aray = mm.getMySudoku();

        for(int i = 0; i < aray.length; i++){
            for(int j = 0; j < aray[i].length; j++){


                this.sudoku_board[i][j] = aray[i][j];
            }
        }

        this.dont_move = mm.getdont_move();
    }

    private int[][] sudoku_board;
    private void first_sudoku(){
        
        
        
        sudoku_board = new int[][]
                {       {9,8,7,6,5,4,3,2,1},
                        {9,8,7,6,5,4,3,2,1},
                        {9,8,7,6,5,4,3,2,1},
                        {9,8,7,6,5,4,3,2,1},
                        {9,8,7,6,5,4,3,2,1},
                        {9,8,7,6,5,4,3,2,1},
                        {9,8,7,6,5,4,3,2,1},
                        {9,8,7,6,5,4,3,2,1},
                        {9,8,7,6,5,4,3,2,1}};

//        for(int i=0 ; i<sudoku_board.length){
//
//        }
        //System.out.println("sss  "+ sudoku_board.length);

        //newsudoku_board.showingTheBoard();
        System.out.println("Initial sudoku_board :");
        System.out.println(" ");
        System.out.println(showingTheBoard());



        Random rand11;
        Random rand22;

        int r1;
        int r2;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
//                rand11 = new Random();
//                rand22 = new Random();
//                r1 = rand11.nextInt(9);
//                r2 = rand22.nextInt(9);
//                r2+=5;
//                if (r1<r2){
//                    sudoku_board[i][j]=0;}
                sudoku_board[i][j]=0;
            }
        }

//        System.out.println("Initial sudoku_board :");
//        System.out.println(" ");
//        System.out.println(showingTheBoard());


        pre_b = sudoku_board;




        for(int i = 0; i < sudoku_board.length; i++){
            for(int j = 0; j < sudoku_board[i].length; j++){
                if(sudoku_board[i][j] != 0){

                    dont_move[i][j] = true;

                    my_array.get(j).remove(new Integer(sudoku_board[i][j]));
                }
            }
        }
        fillBlanks();
    }


    public void fillBlanks(){
        for(int i = 0; i < sudoku_board.length; i++){
            for(int j = 0; j < sudoku_board[i].length; j++){
                if(sudoku_board[i][j] == 0){
                    sudoku_board[i][j] = my_array.get(j).remove(rand.nextInt(my_array.get(j).size()));
                }
            }
        }
    }

    public boolean swap(int col, int r1, int r2){
        int temp;

        if(doesIt(r1, col))
            return false;

        if(doesIt(r2, col))
            return false;

        temp = sudoku_board[r1][col];
        sudoku_board[r1][col] = sudoku_board[r2][col];
        sudoku_board[r2][col] = temp;

        return true;
    }


    public boolean doesIt(int row, int col){
        return dont_move[row][col];
    }

    public int[][] getMySudoku(){
        return sudoku_board;
    }

    public int[][] getpre_b(){
        return pre_b;
    }



    public boolean[][] getdont_move(){
        return dont_move;
    }


    public void printmy_array(){
        for(int i = 0; i < my_array.size(); i++){
            for(int j = 0; j < my_array.get(i).size(); j++){
                System.out.println("I: " + i + " J: " + j + " Value: " + my_array.get(i).get(j));
            }
        }
    }

    public String showingTheBoard(){
        StringBuilder out = new StringBuilder();

        for(int i = 0; i < sudoku_board.length; i++){
            if(i == 3 || i == 6){
                System.out.println(" ");
            }

            for(int j = 0; j < sudoku_board[i].length; j++){
                if(j == 3 || j == 6){
                    out.append('|');
                }

                if(sudoku_board[i][j] > 0){
                    out.append(sudoku_board[i][j]);
                }
                else
                    out.append('_');

                out.append(' ');
            }
            out.append('\n');
        }
        return out.toString();
    }

    public static void main(String args[]){


        Main main = new Main(true);
        SimulatedAnnealing simulated_annealing = new SimulatedAnnealing(main);
    }



}