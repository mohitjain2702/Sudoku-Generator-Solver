import java.util.Random;
import java.util.Arrays;

public class SudokuGenerator
{
      private int[][] board;
      private Random random;

      public SudokuGenerator()
      {
          board = new int[9][9];
          random = new Random();
      }



    public int[][] generateSudoku()
    {
        boolean validBoard = false;
        int randomNumber = random.nextInt(9) + 25;
        while(validBoard != true)
        {
            fillTheBoard(board, randomNumber, 1, 9);
            int[][] testBoard = new int[board.length][];
            for (int i = 0; i < board.length; i++) {
                testBoard[i] = Arrays.copyOf(board[i], board[i].length);
            }
            if(solveBoard(testBoard))
            {
                validBoard = true;

            }
            else {
                for(int i = 0; i<9; i++)
                {
                    for(int j  = 0; j< 9 ; j++)
                    {
                        board[i][j] = 0;
                    }
                }
               // fillTheBoard(board, randomNumber, 1, 9);
            }



        }

        //fillTheBoard(board, randomNumber, 1, 9);
        return board;
    }

    public void fillTheBoard(int[][] board, int remainingNumber, int minValue, int maxValue)
    {
        if(remainingNumber == 0)
        {
            return;
        }
        int row = random.nextInt(9);
        int column = random.nextInt(9);
        int randomNumber = random.nextInt(maxValue-minValue+1)+minValue;
        if(board[row][column]==0 && isValidPlacement(board, randomNumber, row, column))
        {
            board[row][column] = randomNumber;
            fillTheBoard(board, remainingNumber-1, minValue, maxValue);
        }
        else
        {
            fillTheBoard(board, remainingNumber, minValue, maxValue);
        }
    }
    public boolean isNumberInRow(int[][] board, int number, int row)
    {
        for(int i = 0; i < 9; i++)
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
        for(int i =0; i<9; i++)
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
    public  boolean isValidPlacement(int[][] board, int number, int row, int column)
    {
        return !isNumberInRow(board, number, row)&&
                !isNumberColumn(board, number, column)&&
                !isNumberBox(board, number, row, column);
    }
    public boolean solveBoard(int[][] board)
    {
        for(int row = 0; row < 9; row++)
        {
            for(int column = 0; column < 9; column++)
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
