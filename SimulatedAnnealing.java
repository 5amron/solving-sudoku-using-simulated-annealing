package my_sudoku;

import java.util.Random;




public class SimulatedAnnealing {

    int heuristic_qabli;
    int emt_glob;
    

    
    
    Random rand1;
    Random rand2;
    Random rand3;



    double prob;
    double exp;
    int best_heuristic=-162;
    int maximum,first;
    
    int[][] zir_arr;
    
    
    Main oldBoard, newBoard;

    public SimulatedAnnealing(Main board_qabli) {

        oldBoard = board_qabli;

        newBoard=new Main(board_qabli);


        zir_arr=newBoard.getMySudoku();

        //newBoard.toString();
//        System.out.println("Initial Board :");
//        System.out.println(" ");
//        System.out.println(oldBoard.toString());


        rand1 = new Random();
        rand2 = new Random();
        rand3 = new Random();

        emt_glob = heuristic_qabli = 0;


        maximum = 7;
        first=maximum;
        run();

    }

    private void run() {


        boolean can_we_swap;
        int colmn,t;
        int[] maqadir={0,1,2,3,4,5,6,7,8};
        int temp,col_selected;

        whatIsIt(zir_arr);

        long time_1 = System.currentTimeMillis();

        while (maximum > 0 && heuristic_qabli != best_heuristic) {
            //System.out.println(heuristic_qabli);


            for(t=maqadir.length-1;t<0;t--){
                col_selected=rand3.nextInt(t);
                temp=maqadir[t];


                maqadir[t] = maqadir[col_selected];
                maqadir[col_selected]=temp;

            }


            for(int colIndex=0;colIndex<maqadir.length ;colIndex++)
            {
                colmn=maqadir[colIndex];

                for(int ite_num=0;ite_num<21;ite_num++)
                {
                    newBoard=new Main(oldBoard);

                    if(heuristic_qabli == best_heuristic){
                        System.out.println("beeeest result!!!!!!");
                        break;
                    }

                    int r1 = rand1.nextInt(9);
                    int r2 = rand1.nextInt(9);

                    if(r1==r2){

                        //System.out.println("hello");
                    }
                    else
                    {

                        can_we_swap=newBoard.swap(colmn, r1, r2);
                        if(can_we_swap){
                            
                            
                            zir_arr=newBoard.getMySudoku();
                            emt_glob=0;
                            emt_glob=whatIsIt(zir_arr);
                            exp=(-(double)(emt_glob-heuristic_qabli)/(double)maximum);
                            prob=Math.pow(2.718, exp);
                            if(prob>0.8){

                                heuristic_qabli=emt_glob;
                                oldBoard=new Main(newBoard);

                                zir_arr=oldBoard.getMySudoku();

                            }
                            else
                            {

                            }

                        }
                        else
                        {
                            //System.out.println("next time");
                        }

                    }

                }



            }
            if(heuristic_qabli>emt_glob)
                maximum-=80;

            else
                maximum--;

        }


        zir_arr = oldBoard.getMySudoku();

        System.out.println("Final Board: ");

        System.out.println(oldBoard.showingTheBoard());
        System.out.println(" ");
        System.out.println("Final score:" + heuristic_qabli);
        System.out.print("Best Score:" + -168);
        System.out.println(" ");

        System.out.println(" ");

        long time_2 = System.currentTimeMillis();
        long duratoin = time_2 - time_1;

        System.out.print("duration : " + duratoin);


    }

    private int whatIsIt(int[][] board){
        int row_heuristic ,coll_heu;

        row_heuristic=row_heuristics(board);

        coll_heu=col_heuristic(board);
        return row_heuristic+coll_heu;
    }

    private int row_heuristics(int [][]board){
        int []duup;
        int cur_heuristic = 0;
        //int ;

        for(int row = 0; row < board.length; row++){
            // = 0;


            duup = new int[10]; //reset

            for(int col = 0; col < board[row].length; col++){
                duup[board[row][col]]++;
            }

            //minus one for each non-duplicated number
            for(int x = 1; x < duup.length; x++){
                if(duup[x] == 1){
                    cur_heuristic--;
                }
            }

        }
        //      System.out.println("First one:" + cur_heuristic);
        return cur_heuristic;
    }

    private int col_heuristic(int [][]my_sudoku_board){
        int []duup;



        int cur_heuristic = 0;
        int row = 0;
        int col = 0;



        int llii_1 = row + 3;
        int llii_2 = col + 3;

        while(row < my_sudoku_board.length){
            duup = new int[10];

            while(row < llii_1){
                col = llii_2 - 3;
                while(col < llii_2){
                    duup[my_sudoku_board[row][col]]++;
                    ++col;
                }
                //System.out.println();
                ++row;
            }

            for(int x = 1; x < duup.length; x++){
                if(duup[x] == 1){
                    cur_heuristic--;

                }
            }
            
            
            

            if(llii_2 < my_sudoku_board.length){
                row = llii_1 - 3;

                llii_2 += 3;
            }
            else{
                col = 0;
                llii_2 = 3;
                row = llii_1;
                llii_1 = row + 3;
            }
        }
        return cur_heuristic;
    }
}