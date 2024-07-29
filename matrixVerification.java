import java.util.*;

public class MatrixVerification {
    static boolean freivald(int a[][], int b[][], int c[][]) {
        int n = a.length;

        // Generate a random vector r containing 0s and 1s
        int[] r = new int[n];
        for (int i = 0; i < n; i++)
            r[i] = (int) (Math.random() * 2); // Generate random number either 0 or 1

        // Calculate B*r
        int[] br = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                br[i] += b[i][j] * r[j];

        // Calculate C*r
        int[] cr = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                cr[i] += c[i][j] * r[j];

        // Calculate A*(B*r)
        int[] axbr = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                axbr[i] += a[i][j] * br[j];

        // Check if A*(B*r) - C*r = 0
        for (int i = 0; i < n; i++)
            if (axbr[i] - cr[i] != 0)
                return false;

        return true;
    }

    static boolean isProduct(int a[][], int b[][], int c[][], int k) {
        for (int i = 0; i < k; i++)
            if (!freivald(a, b, c))
                return false;
        return true;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the matrices (n x n): ");
        int n = scanner.nextInt();

        System.out.println("Enter the elements of matrix A:");
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = scanner.nextInt();

        System.out.println("Enter the elements of matrix B:");
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                b[i][j] = scanner.nextInt();

        System.out.println("Enter the elements of matrix C:");
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = scanner.nextInt();

        System.out.println("Enter the number of iterations (k):");
        int k = scanner.nextInt();

        scanner.close();

        if (isProduct(a, b, c, k))
            System.out.println("Yes, A * B = C");
        else
            System.out.println("No, A * B != C");
    }
}
