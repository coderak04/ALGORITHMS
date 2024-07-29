import java.util.*;

public class PrimMST {
    // Function to find the Minimum Spanning Tree using Prim's algorithm
    public static void primMST(int[][] graph) {
        int numVertices = graph.length;

        // Array to store the parent of each vertex in the MST
        int[] parent = new int[numVertices];

        // Array to store the key values (weights) of each vertex
        int[] key = new int[numVertices];

        // Array to keep track of whether a vertex is included in the MST or not
        boolean[] mstSet = new boolean[numVertices];

        // Initialize key values to infinity and mstSet to false
        for (int i = 0; i < numVertices; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Start with the first vertex
        key[0] = 0;
        parent[0] = -1; // The first vertex is the root of the MST

        // Build the MST
        for (int count = 0; count < numVertices - 1; count++) {
            // Find the vertex with the minimum key value that is not yet included in the MST
            int u = minKey(key, mstSet);

            // Include the selected vertex in the MST
            mstSet[u] = true;

            // Update key values and parent pointers for the adjacent vertices of the selected vertex
            for (int v = 0; v < numVertices; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the edges of the MST
        printMST(parent, graph);
    }

    // Helper function to find the vertex with the minimum key value
    private static int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < key.length; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    // Helper function to print the edges of the MST
    private static void printMST(int[] parent, int[][] graph) {
        int total=0;
        System.out.println("Edges of the Minimum Spanning Tree:");
        for (int v = 1; v < graph.length; v++) {
            System.out.println(parent[v] + " - " + v + "   Weight: " + graph[parent[v]][v]);
            total+=graph[parent[v]][v];
        }
        System.out.println("Minimum Spanning Cost= "+total);
    }

    // Example usage with user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        int[][] graph = new int[numVertices][numVertices];

        System.out.println("Enter the weighted adjacency matrix of the graph:");

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        primMST(graph);

        scanner.close();
    }
}
