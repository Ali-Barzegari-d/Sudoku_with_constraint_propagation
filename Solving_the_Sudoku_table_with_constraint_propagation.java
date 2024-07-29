import java.util.*;

public class Solving_the_Sudoku_table_with_constraint_propagation {
    static final int N = 9;

    static int[][] board = {
        {3, 0, 6, 5, 0, 8, 4, 0, 0},
        {5, 2, 0, 0, 0, 0, 0, 0, 0},
        {0, 8, 7, 0, 0, 0, 0, 3, 1},
        {0, 0, 3, 0, 1, 0, 0, 8, 0},
        {9, 0, 0, 8, 6, 3, 0, 0, 5},
        {0, 5, 0, 0, 9, 0, 6, 0, 0},
        {1, 3, 0, 0, 0, 0, 2, 5, 0},
        {0, 0, 0, 0, 0, 0, 0, 7, 4},
        {0, 0, 5, 2, 0, 6, 3, 0, 0}
    };

    public static void printBoard(int[][] board) {
        for (int r = 0; r < N; r++) {
            for (int d = 0; d < N; d++) {
                if (board[r][d] == 0 && d % 3 == 2 && d != 8) {
                    System.out.print(". | ");
                } else if (board[r][d] == 0 && d % 3 != 2) {
                    System.out.print(". ");
                } else {
                    if (d % 3 == 2 && d != 8) {
                        System.out.print(board[r][d] + " | ");
                    } else {
                        System.out.print(board[r][d] + " ");
                    }
                }
            }
            System.out.println();
            if (r % 3 == 2 && r != 8) {
                System.out.println("---------------------");
            }
        }
    }

    public static boolean isValid(int[][] board, int num, int row, int col) {
        for (int x = 0; x < N; x++) {
            if (board[row][x] == num || board[x][col] == num) {
                return false;
            }
        }

        int startRow = row - row % 3, startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int[] findEmptyLocation(int[][] board) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static void initializeDomain(int[][] board, Set<Integer>[][] domain) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, num, row, col)) {
                            domain[row][col].add(num);
                        }
                    }
                } else {
                    domain[row][col].add(board[row][col]);
                }
            }
        }
    }

    public static boolean AC3(Set<Integer>[][] domain) {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (domain[i][j].size() > 1) {
                    for (int k = 0; k < N; k++) {
                        if (k != j) {
                            q.add(new int[]{i, j});
                        }
                        if (k != i) {
                            q.add(new int[]{i, j});
                        }
                    }
                    int startRow = i - i % 3, startCol = j - j % 3;
                    for (int r = 0; r < 3; r++) {
                        for (int c = 0; c < 3; c++) {
                            if (startRow + r != i || startCol + c != j) {
                                q.add(new int[]{i, j});
                            }
                        }
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0], col = cell[1];

            if (domain[row][col].size() == 1) {
                int val = domain[row][col].iterator().next();
                for (int k = 0; k < N; k++) {
                    if (k != col && domain[row][k].contains(val) && domain[row][k].size() == 1) {
                        q.add(new int[]{row, k});
                    }
                    if (k != row && domain[k][col].contains(val) && domain[k][col].size() == 1) {
                        q.add(new int[]{k, col});
                    }
                }
                int startRow = row - row % 3, startCol = col - col % 3;
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        if ((startRow + r != row || startCol + c != col) && domain[startRow + r][startCol + c].contains(val) && domain[startRow + r][startCol + c].size() == 1) {
                            q.add(new int[]{startRow + r, startCol + c});
                        }
                    }
                }
            }

            if (domain[row][col].isEmpty()) {
                return false;
            }
        }

        return true;
    }

    public static boolean solveSudoku(int[][] board, Set<Integer>[][] domain) {
        int[] emptyLocation = findEmptyLocation(board);
        int row = emptyLocation[0], col = emptyLocation[1];
        if (row == -1) {
            return true;
        }

        initializeDomain(board, domain);

        if (!AC3(domain)) {
            return false;
        }

        for (int num : domain[row][col]) {
            if (isValid(board, num, row, col)) {
                board[row][col] = num;
                if (solveSudoku(board, domain)) {
                    return true;
                }
                board[row][col] = 0;
            }
            //System.out.println("");
            //printBoard(board);
            //System.out.println("____________________________");
        }

        return false;
    }

    public static void main(String[] args) {
        Set<Integer>[][] domain = new HashSet[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                domain[i][j] = new HashSet<>();
            }
        }

        if (solveSudoku(board, domain)) {
            System.out.println("");
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }
}


//Ali Barzegari dahaj