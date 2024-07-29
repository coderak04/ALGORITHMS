import java.util.*;

class subset {
    static boolean isSubsetSum(int[] nums, int n, int target, List<Integer> subset) {
        boolean[][] dp = new boolean[n + 1][target + 1];

        // If target is 0, then answer is True for all sets
        for (int i = 0; i <= n; i++)
            dp[i][0] = true;

        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < nums[i - 1])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
            }
        }

        if (!dp[n][target])
            return false;

        // Reconstruct the subset
        int i = n;
        int j = target;
        while (i > 0 && j > 0) {
            if (dp[i][j] && !dp[i - 1][j]) {
                subset.add(nums[i - 1]);
                j -= nums[i - 1];
            }
            i--;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();
        
        int[] nums = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
 
        System.out.print("Enter the target sum: ");
        int target = scanner.nextInt();
        
        List<Integer> subset = new ArrayList<>();
        if (isSubsetSum(nums, n, target, subset)) {
            System.out.println("Subset with the given sum exists: " + subset);
        } else {
            System.out.println("Subset with the given sum does not exist");
        }
        
        scanner.close();
    }
}
