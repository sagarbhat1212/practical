import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NQueens {

    public static List<List<String>> solveNQueens(int n, int firstQueenCol) {
        Set<Integer> col = new HashSet<>();
        Set<Integer> posDiag = new HashSet<>();
        Set<Integer> negDiag = new HashSet<>();
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        
        // Initialize the board with '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        // Place the first queen at the specified column in the first row
        col.add(firstQueenCol);
        posDiag.add(0 + firstQueenCol);
        negDiag.add(0 - firstQueenCol);
        board[0][firstQueenCol] = 'Q';

        // Start backtracking from the second row
        backtrack(1, n, col, posDiag, negDiag, board, res);

        return res;
    }

    private static void backtrack(int r, int n, Set<Integer> col, Set<Integer> posDiag, Set<Integer> negDiag, char[][] board, List<List<String>> res) {
        if (r == n) {
            res.add(construct(board));
            return;
        }

        for (int c = 0; c < n; c++) {
            if (col.contains(c) || posDiag.contains(r + c) || negDiag.contains(r - c)) {
                continue;
            }

            col.add(c);
            posDiag.add(r + c);
            negDiag.add(r - c);
            board[r][c] = 'Q';

            backtrack(r + 1, n, col, posDiag, negDiag, board, res);

            col.remove(c);
            posDiag.remove(r + c);
            negDiag.remove(r - c);
            board[r][c] = '.';
        }
    }

    private static List<String> construct(char[][] board) {
        List<String> path = new ArrayList<>();
        for (char[] row : board) {
            path.add(new String(row));
        }
        return path;
    }

    public static void main(String[] args) {
        int n = 8;
        int firstQueenCol = 1;
        List<List<String>> solutions = solveNQueens(n, firstQueenCol);

        if (!solutions.isEmpty()) {
            List<String> board = solutions.get(0);
            for (String row : board) {
                System.out.println(row);
            }
        } else {
            System.out.println("No solution found.");
        }
    }
}
