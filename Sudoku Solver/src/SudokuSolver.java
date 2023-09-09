public class SudokuSolver
{
    private static final int GRID = 9;

    public static void main(String[] args)
    {
        long codeStartTime = System.currentTimeMillis();
        long puzzleGenerateStartTime = System.currentTimeMillis();
        SudokuGenerator generate = new SudokuGenerator();
        int[][] board = generate.generateSudoku();
        long puzzleGenerateEndTime = System.currentTimeMillis();
        long puzzleGenerateExecutionTime = puzzleGenerateEndTime - puzzleGenerateStartTime; // Calculate the execution time in milliseconds
        double GenerateSeconds = puzzleGenerateExecutionTime / 1000.0;

        System.out.println("\n" + "Time to generate the puzzle - " + GenerateSeconds +" seconds " +"\n" );


        printBoard(board);


        if (solveBoard(board)) {
            long codeEndTime = System.currentTimeMillis();
            long codeExecutionTime = codeEndTime - codeStartTime;
            double codeSecond = codeExecutionTime/1000.0;

            System.out.println("\n" +"Solved successfully in "+ codeSecond +" seconds "+"\n");
        }
        else {
            System.out.println("\n"+ "Unsolvable board :(");
        }

        printBoard(board);

    }


    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }
            for (int column = 0; column < GRID; column++) {
                if (column % 3 == 0 && column != 0) {
                    System.out.print("|");
                }
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }

        private static boolean isNumberInRow(int[][] board, int number, int row)
        {
            for(int i = 0; i < GRID; i++)
            {
                if(board[row][i] == number)
                {
                    return true;
                }

            }
            return false;
        }

        private static boolean isNumberColumn(int[][] board, int number, int column)
        {
            for(int i =0; i<GRID; i++)
            {
                if(board[i][column]== number)
                {
                    return true;
                }

            }
            return false;
        }

        private static boolean isNumberBox(int[][] board, int number, int row, int column)
        {
            int localBoxRow = row - row%3;
            int localBoxColumn = column - column%3;
            for(int i = localBoxRow; i < localBoxRow+3; i++)
            {
                for(int j = localBoxColumn; j < localBoxColumn+3; j++ )
                {
                    if(board[i][j] == number)
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        private static boolean isValidPlacement(int[][] board, int number, int row, int column)
        {
            return !isNumberInRow(board, number, row)&&
                    !isNumberColumn(board, number, column)&&
                    !isNumberBox(board, number, row, column);
        }

       private static boolean solveBoard(int[][] board)
       {
           for(int row = 0; row < GRID; row++)
           {
               for(int column = 0; column < GRID; column++)
               {
                   if(board[row][column] == 0)
                   {
                       for(int numberToTry = 1; numberToTry<=9; numberToTry++)
                       {
                           if(isValidPlacement(board, numberToTry, row, column))
                           {
                               board[row][column] = numberToTry;
                               if(solveBoard(board))
                               {
                                   return true;
                               }
                               else
                               {
                                   board[row][column] = 0;
                               }
                           }

                       }
                       return false;
                   }
               }
           }
           return  true;
       }


}
