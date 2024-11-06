import java.util.ArrayList;
import java.util.List;

public class Knapsack01 {

    public static int[] knapsack01(int n, int[] values, int[] weights, int W) {
        int[][] dp = new int[n + 1][W + 1];

        // Build the dp table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Find the selected items
        List<Integer> selectedItems = new ArrayList<>();
        int maxValue = dp[n][W];
        int w = W;

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(i - 1);
                w -= weights[i - 1];
            }
        }

        // Convert the selected items list to array
        int[] result = new int[selectedItems.size() + 1];
        result[0] = maxValue;
        for (int i = 0; i < selectedItems.size(); i++) {
            result[i + 1] = selectedItems.get(selectedItems.size() - i - 1);
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 3;
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int W = 50;

        int[] result = knapsack01(n, values, weights, W);
        System.out.println("Maximum value: " + result[0]);
        System.out.print("Selected items: ");
        for (int i = 1; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}

