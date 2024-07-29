import java.util.Random;
import java.util.Scanner;

//MillerRabin Algorithm
public class primalityTest{

    // This function calculates (a^b) % c
    private static long power(long a, long b, long c) {
        long result = 1;
        a = a % c;

        while (b > 0) {
            if (b % 2 == 1)
                result = (result * a) % c;

            b = b >> 1;
            a = (a * a) % c;
        }
        return result;
    }

    // This function performs Miller-Rabin primality test
    private static boolean millerTest(long d, long n) {
        Random rand = new Random();
        long a = 2 + rand.nextInt((int) (n - 4));

        long x = power(a, d, n);

        if (x == 1 || x == n - 1)
            return true;

        while (d != n - 1) {
            x = (x * x) % n;
            d *= 2;

            if (x == 1)
                return false;
            if (x == n - 1)
                return true;
        }
        return false;
    }

    // This function checks if a given number is prime or not
    public static boolean isPrime(long n, int k) {
        if (n <= 1 || n == 4)
            return false;
        if (n <= 3)
            return true;

        long d = n - 1;
        while (d % 2 == 0)
            d /= 2;

        for (int i = 0; i < k; i++)
            if (!millerTest(d, n))
                return false;

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a number to test for primality:");
        long number = scanner.nextLong();

        System.out.println("Enter the number of iterations:");
        int iterations = scanner.nextInt();

        scanner.close();

        if (isPrime(number, iterations))
            System.out.println(number + " is prime.");
        else
            System.out.println(number + " is not prime.");
    }
}
