import java.util.*;

class Knapsack {

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int knapSack(int W, int wt[], int val[], int n, boolean selected[]) {
        int[][] dp = new int[n + 1][W + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (wt[i - 1] <= w) {
                    dp[i][w] = max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find selected items
        int res = dp[n][W];
        int w = W;
        for (int i = n; i > 0 && res > 0; i--) {
            if (res != dp[i - 1][w]) {
                selected[i - 1] = true;
                res -= val[i - 1];
                w -= wt[i - 1];
            }
        }

        return dp[n][W];
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int profit[] = new int[n];
        int weight[] = new int[n];

        System.out.println("Enter the profits for each item:");
        for (int i = 0; i < n; i++) {
            profit[i] = scanner.nextInt();
        }

        System.out.println("Enter the weights for each item:");
        for (int i = 0; i < n; i++) {
            weight[i] = scanner.nextInt();
        }

        System.out.print("Enter the knapsack capacity: ");
        int W = scanner.nextInt();
        scanner.close();

        boolean selected[] = new boolean[n];
        int totalProfit = knapSack(W, weight, profit, n, selected);

        System.out.println("Max Profit: " + totalProfit);
        System.out.println("Items used:");
        for (int i = 0; i < n; i++) {
            if (selected[i]) {
                System.out.println("Item " + (i + 1) + ": Profit = " + profit[i] + ", Weight = " + weight[i]);
            }
        }
    }
}
