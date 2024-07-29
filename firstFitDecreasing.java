import java.util.*;

public class firstFit {

    // Returns list of bins where each bin contains the items it stores
    static List<List<Integer>> firstFit(int weight[], int n, int c) {
        // Initialize list of bins
        List<List<Integer>> bins = new ArrayList<>();
        bins.add(new ArrayList<>()); // Add the first bin

        // Place items one by one
        for (int i = 0; i < n; i++) {
            boolean placed = false;
            // Try to place the current item in existing bins
            for (List<Integer> bin : bins) {
                int binCapacity = c - bin.stream().mapToInt(w -> w).sum();
                if (weight[i] <= binCapacity) {
                    bin.add(weight[i]);
                    placed = true;
                    break;
                }
            }
            // If the item couldn't be placed in any existing bin, create a new bin
            if (!placed) {
                List<Integer> newBin = new ArrayList<>();
                newBin.add(weight[i]);
                bins.add(newBin);
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

        // Sort the weights array in decreasing order
        Arrays.sort(weight, Collections.reverseOrder());

        System.out.print("Enter the capacity of the bins: ");
        int c = scanner.nextInt();

        scanner.close();

        List<List<Integer>> bins = firstFit(weight, n, c);
        System.out.println("Number of bins required in First Fit: " + bins.size());
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
