import java.util.*;

public class karatsubaMultiplication {

    // Function to multiply two numbers using Karatsuba algorithm
    public static long karatsubaMultiply(long x, long y) {
        // Base case: If either x or y is single digit, return their product
        if (x < 10 || y < 10) {
            return x * y;
        }

        // Calculate the number of digits in the numbers
        int maxLength = Math.max(Long.toString(x).length(), Long.toString(y).length());
        int m = (maxLength / 2) + (maxLength % 2); // Middle index for splitting the digits

        // Splitting the numbers into halves
        long high1 = x / (long) Math.pow(10, m);
        long low1 = x % (long) Math.pow(10, m);
        long high2 = y / (long) Math.pow(10, m);
        long low2 = y % (long) Math.pow(10, m);

        // Recursively calculate three products needed
        long z0 = karatsubaMultiply(low1, low2);
        long z1 = karatsubaMultiply((low1 + high1), (low2 + high2));
        long z2 = karatsubaMultiply(high1, high2);

        // Using the formula: z2 * 10^(2*m) + (z1 - z2 - z0) * 10^m + z0
        return (z2 * (long) Math.pow(10, 2 * m)) + ((z1 - z2 - z0) * (long) Math.pow(10, m)) + z0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the first integer: ");
        long x = scanner.nextLong();
        System.out.print("Enter the second integer: ");
        long y = scanner.nextLong();

        long result = karatsubaMultiply(x, y);
        System.out.println("Multiplication result: " + result);
    }
}
