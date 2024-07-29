import java.util.*;

public class nextFitDecreasing {

    // Sorts the weights array in descending order
    static void sortWeightsDescending(int weight[]) {
        Arrays.sort(weight);
        reverse(weight);
    }

    // Reverses the elements of an array
    static void reverse(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }

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

        // Sort the weights array in descending order
        sortWeightsDescending(weight);

        System.out.print("Enter the capacity of the bins: ");
        int c = scanner.nextInt();

        scanner.close();

        System.out.println("Number of bins required in Next Fit Decreasing: " + nextFit(weight, n, c));
    }
}
