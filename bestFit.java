import java.util.*;

public class bestFit {

    // Returns list of bins where each bin contains the items it stores
    static List<List<Integer>> bestFit(int weight[], int n, int c) {
        // Initialize list of bins
        List<List<Integer>> bins = new ArrayList<>();
        bins.add(new ArrayList<>()); // Add the first bin

        // Place items one by one
        for (int i = 0; i < n; i++) {
            int minSpace = Integer.MAX_VALUE;
            int bestBinIndex = -1;

            // Find the bin with the smallest remaining space to fit the current item
            for (int j = 0; j < bins.size(); j++) {
                int binCapacity = c - bins.get(j).stream().mapToInt(w -> w).sum();
                if (weight[i] <= binCapacity && binCapacity < minSpace) {
                    minSpace = binCapacity;
                    bestBinIndex = j;
                }
            }

            // If there is no suitable bin, create a new bin
            if (bestBinIndex == -1) {
                List<Integer> newBin = new ArrayList<>();
                newBin.add(weight[i]);
                bins.add(newBin);
            } else {
                // Add the current item to the best fit bin
                bins.get(bestBinIndex).add(weight[i]);
            }
        }
        return bins;
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

        List<List<Integer>> bins = bestFit(weight, n, c);
        System.out.println("Number of bins required in Best Fit: " + bins.size());
        System.out.println("Items in each bin:");
        for (int i = 0; i < bins.size(); i++) {
            System.out.print("Bin " + (i + 1) + ": ");
            List<Integer> bin = bins.get(i);
            for (int item : bin) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}
