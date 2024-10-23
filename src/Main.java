import java.util.*;

public class Main { // tic tac toe
    static boolean turn = true;
    static boolean won = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] matrix = new int[3][3];

        String map = """
                          1  2  3
                       A |  |  |  |
                       B |  |  |  |
                       C |  |  |  |
                       """;
        System.out.print(map);
        System.out.println("Enter letter to choose row and number for column (ex: B2 - middle)");
        System.out.println("Enter 'Q' to exit game and 'R' to restart");

        while (true) {
            if (won) {
                System.out.println("Type 'R' to restart or 'Q' to quit.");
                String answ = sc.nextLine().toLowerCase().trim();
                if (answ.equals("r")) {
                    restartM(matrix);
                    printM(matrix);
                } else if (answ.equals("q")) {
                    break;
                }
                continue;
            }

            System.out.print((turn ? "X" : "O") + "'s turn to move: ");
            String move = sc.nextLine().toLowerCase().trim();

            if (move.equals("q")) {
                break;
            } else if (move.equals("r")) {
                restartM(matrix);
                printM(matrix);
            } else {
                placeM(matrix, move);
                printM(matrix);
                checkM(matrix);
                if (!won) {
                    isTie(matrix);
                }
            }
        }
        sc.close();
    }

    static void printM(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                System.out.print("|" + (arr[i][j] > 0 ? "X" : arr[i][j] < 0 ? "O" : " "));
            }
            System.out.println("|");
        }
    }

    static void placeM(int[][] arr, String move) {
        if (move.length() != 2) {
            System.out.println("Invalid move!");
            return;
        }

        char row = move.charAt(0);
        int col = move.charAt(1) - '0';

        if (col < 1 || col > 3 || (row < 'a' || row > 'c')) {
            System.out.println("Invalid move!");
            return;
        }

        int rowIndex = row - 'a';
        if (arr[rowIndex][col - 1] != 0) {
            System.out.println("Can't move there!");
            return;
        }

        arr[rowIndex][col - 1] = turn ? 1 : -1; // 1 for X, -1 for O
        turn = !turn;
    }

    static void checkM(int[][] arr) {
        for (int i = 0; i < 3; i++) {
            if (Math.abs(arr[i][0] + arr[i][1] + arr[i][2]) == 3) {
                won = true;
                System.out.println("Congrats! " + (arr[i][0] > 0 ? "X" : "O") + " won!");
                return;
            }
            if (Math.abs(arr[0][i] + arr[1][i] + arr[2][i]) == 3) {
                won = true;
                System.out.println("Congrats! " + (arr[0][i] > 0 ? "X" : "O") + " won!");
                return;
            }
        }
        if (Math.abs(arr[0][0] + arr[1][1] + arr[2][2]) == 3) {
            won = true;
            System.out.println("Congrats! " + (arr[1][1] > 0 ? "X" : "O") + " won!");
        } else if (Math.abs(arr[0][2] + arr[1][1] + arr[2][0]) == 3) {
            won = true;
            System.out.println("Congrats! " + (arr[1][1] > 0 ? "X" : "O") + " won!");
        }
    }

    static void isTie(int[][] arr) {
        boolean isFull = true;
        for (int i =0; i< arr.length;i++) {
            for (int j =0; j<arr.length;j++) {
                if (arr[i][j] == 0) {
                    isFull = false;
                    break;
                }
            }
            if (!isFull) {
                break;
            }
        }
        if (isFull && !won) {
            System.out.println("It's a tie!");
            won = true;
        }
    }

    static void restartM(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            Arrays.fill(arr[i], 0);
        }
        turn = true;
        won = false;
    }
}
