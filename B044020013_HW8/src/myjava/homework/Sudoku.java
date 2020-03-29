package myjava.homework;

import java.util.Scanner;
public class Sudoku {
    private Integer[][] board;
    private boolean[][] start;
    public Sudoku(){
        board = new Integer[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0}};
        start = new boolean[][]{{true,true,true,true,true,true,true,true,true},
                                {true,true,true,true,true,true,true,true,true},
                                {true,true,true,true,true,true,true,true,true},
                                {true,true,true,true,true,true,true,true,true},
                                {true,true,true,true,true,true,true,true,true},
                                {true,true,true,true,true,true,true,true,true},
                                {true,true,true,true,true,true,true,true,true},
                                {true,true,true,true,true,true,true,true,true},
                                {true,true,true,true,true,true,true,true,true},};
    };
    public String toString(){
        String str = "▁▁▁▁▁▁▁▁▁▁▁▁\n";
        for(int i = 0; i < 9; i++){
            str = str + "▎";
            for (int j = 0; j < 9; j++){
                if (getValueIn(i, j) == 0)
                    str = str + ". ";
                else
                    str = str + getValueIn(i, j) + " ";
                if (j == 2 || j == 5 || j == 8)
                    str = str + "▎";
            }
            str = str + "\n";
            if (i == 2 || i == 5)
                str = str + "────────────\n";
        }
        str = str + "￣￣￣￣￣￣￣￣￣￣￣￣\n";
        return str;
    };
    public void addInitial(int row, int col, int value){
        board[row][col] = value;
        start[row][col] = false;
    };
    public void addGuess(int row, int col, int value){
        int ini = board[row][col];
        board[row][col] = value;
        if(checkPuzzle() == false){
            board[row][col] = ini;
            System.out.println("Wrong Input!");
        }
    };
    public boolean checkPuzzle(){
        //檢測row
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (board[i][j] > 0 && board[i][j] <= 9){
                    for (int k = j + 1; k < 9; k++){
                        if (board[i][j] == board[i][k])
                            return false;
                    }
                }
                else if(board[i][j] != 0)
                    return false;
            }
        }
        //檢測col
        for(int j = 0; j < 9; j++){
            for (int i = 0; i < 9; i++){
                if (board[i][j] > 0 && board[i][j] <= 9){
                    for (int k = i + 1; k < 9; k++){
                        if (board[i][j] == board[k][j])
                            return false;
                    }
                }
                else if(board[i][j] != 0)
                    return false;
            }
        }
        //檢測3*3
        for(int i = 0; i < 9; i = i + 3){
            for (int j = 0; j < 9; j = j + 3){
                for (int ii = i; ii < i + 3; ii++){
                    for (int jj = j; jj < j + 3 ; jj++){
                        if (board[ii][jj] > 0 && board[ii][jj] <= 9){
                            for (int iii = i; iii < i + 3; iii++) {
                                for (int jjj = j; jjj < j + 3; jjj++) {
                                    if (ii != iii && jj != jjj){
                                        if (board[ii][jj] == board[iii][jjj])
                                            return false;
                                    }
                                }
                            }
                        }
                        else if(board[ii][jj] != 0)
                            return false;
                    }
                }
            }
        }
        return true;
    };
    public int getValueIn (int row, int col){
        return board[row][col];
    };
    public Integer[] getAllowedValue (int row, int col){
        Integer[] allow = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        //檢測col
        for (int i = 0; i < 9; i++){
            for (int a = 0; a < 9; a++){
                if (board[i][col] == allow[a]){
                    allow[a] = 0;
                }
            }
        }
        //檢測row
        for (int j = 0; j < 9; j++){
            for (int a = 0; a < 9; a++){
                if (board[row][j] == allow[a]){
                    allow[a] = 0;
                }
            }
        }
        //檢測3*3
        int inirow = 0;
        int inicol = 0;
        if (row < 3)
            inirow = 0;
        else if (row > 5)
            inirow = 6;
        else
            inirow = 3;

        if (col < 3)
            inicol = 0;
        else if (col > 5)
            inicol = 6;
        else
            inicol = 3;

        for (int i = inirow; i < inirow + 3; i++){
            for (int j = inicol; j < inicol + 3; j++){
                for (int a = 0; a < 9; a++){
                    if (board[i][j] == allow[a]){
                        allow[a] = 0;
                    }
                }
            }
        }

        return allow;
    };
    public boolean isFull(){
        int flag = 0;
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(board[i][j] == 0)
                    flag = 1;
            }
        }
        if (flag == 1){
            return false;
        }
        else
            return true;
    };
    public void reset(){
        for(int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(start[i][j] == true)
                    board[i][j] = 0;
            }
        }
    };

    public static void main(String args[]) {
            Sudoku Sudoku = new Sudoku();
            Sudoku.addInitial(0, 0, 1);
            Sudoku.addInitial(0, 1, 2);
            Sudoku.addInitial(0, 2, 3);
            Sudoku.addInitial(0, 3, 4);
            Sudoku.addInitial(0, 4, 9);
            Sudoku.addInitial(0, 5, 7);
            Sudoku.addInitial(0, 6, 8);
            Sudoku.addInitial(0, 7, 6);
            Sudoku.addInitial(0, 8, 5);
            Sudoku.addInitial(1, 0, 4);
            Sudoku.addInitial(1, 1, 5);
            Sudoku.addInitial(1, 2, 9);
            Sudoku.addInitial(2, 0, 6);
            Sudoku.addInitial(2, 1, 7);
            Sudoku.addInitial(2, 2, 8);
            Sudoku.addInitial(3, 0, 3);
            Sudoku.addInitial(3, 4, 1);
            Sudoku.addInitial(4, 0, 2);
            Sudoku.addInitial(5, 0, 9);
            Sudoku.addInitial(5, 5, 5);
            Sudoku.addInitial(6, 0, 8);
            Sudoku.addInitial(7, 0, 7);
            Sudoku.addInitial(8, 0, 5);
            Sudoku.addInitial(8, 3, 9);
            System.out.println(Sudoku);

        while (true) {
            System.out.print("Input options: 1.input number 2.display number 3.reset : ");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.print("Input row: ");
                    int row = scanner.nextInt() - 1;
                    System.out.print("Input col: ");
                    int col = scanner.nextInt() - 1;
                    System.out.print("Input value: ");
                    int value = scanner.nextInt();
                    Sudoku.addGuess(row, col, value);
                    break;
                case "2":
                    System.out.print("Input row: ");
                    row = scanner.nextInt() - 1;
                    System.out.print("Input col: ");
                    col = scanner.nextInt() - 1;
                    System.out.print("You can input : ");
                    Integer[] num = Sudoku.getAllowedValue(row, col);
                    for (int i = 0; i < 9; i++){
                        if (num[i] != 0)
                            System.out.print(num[i] + " ");
                    }
                    System.out.println(" ");
                    break;
                case "3":
                    Sudoku.reset();
                    break;
                default:
                    System.out.println("Wrong Input!!!!!!!");
                    break;
            }
            System.out.println(Sudoku);
            if (Sudoku.isFull() == true){
                System.out.println("You Win!");
                System.exit(0);
            }
        }
    }
}
