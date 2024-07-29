import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class nextFIT {

    // Returns number of bins required using Next Fit online algorithm
    static int nextFit(int weight[], int n, int c) {
        int bins = 0; // Initialize the number of bins
        int binCapacity = c; // Initialize the capacity of the current open bin

        // Place items one by one
        for (int i = 0; i < n; i++) {
            // If the current item fits into the open bin, place it
            if (weight[i] <= binCapacity) {
                binCapacity -= weight[i]; // Reduce the remaining capacity of the open bin
            } else {
                bins++; // Close the current bin and open a new bin
                binCapacity = c - weight[i]; // Initialize the capacity of the new open bin
            }
        }
        return bins + 1; // Add 1 to account for the last open bin
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int weight[] = new int[n];
        System.out.println("Enter the weights of the items:");
        for (int i = 0; i < n; i++)
            weight[i] = scanner.nextInt();

        System.out.print("Enter the capacity of the bins: ");
        int c = scanner.nextInt();

        scanner.close();

        System.out.println("Number of bins required in Next Fit: " + nextFit(weight, n, c));
    }
}
