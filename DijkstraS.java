import java.util.*;

public class DijkstraS {
    // Function to find the shortest paths from a single source node to all other nodes
    public static void dijkstra(int[][] graph, int source) {
        int numVertices = graph.length;

        // Array to store the shortest distances from the source node
        int[] distance = new int[numVertices];

        // Array to keep track of whether a vertex is included in the shortest path or not
        boolean[] visited = new boolean[numVertices];

        // Initialize distance values to infinity and visited array to false
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(visited, false);

        // The distance from the source to itself is 0
        distance[source] = 0;

        // Priority queue to store vertices based on their distance from the source
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> a.distance - b.distance);

        // Add the source node to the priority queue
        priorityQueue.add(new Node(source, 0));

        // Process vertices until the priority queue is empty
        while (!priorityQueue.isEmpty()) {
            // Extract the vertex with the minimum distance
            int u = priorityQueue.poll().vertex;

            // Mark the extracted vertex as visited
            visited[u] = true;

            // Update the distances of adjacent vertices
            for (int v = 0; v < numVertices; v++) {
                if (!visited[v] && graph[u][v] != 0 && distance[u] != Integer.MAX_VALUE &&
                        distance[u] + graph[u][v] < distance[v]) {
                    distance[v] = distance[u] + graph[u][v];
                    priorityQueue.add(new Node(v, distance[v]));
                }
            }
        }

        // Print the shortest distances from the source node
        printShortestDistances(distance, source);
    }

    // Helper function to print the shortest distances
    private static void printShortestDistances(int[] distance, int source) {
        System.out.println("Shortest distances from the source node " + source + ":");

        for (int i = 0; i < distance.length; i++) {
            System.out.println("To node " + i + ": " + distance[i]);
        }
    }

    // Node class to represent vertices and their distances
    static class Node {
        int vertex;
        int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    // Example usage with user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();

        int[][] graph = new int[numVertices][numVertices];

        System.out.println("Enter the weighted adjacency matrix of the graph (use 0 for no connection):");

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter the source node: ");
        int sourceNode = scanner.nextInt();

        dijkstra(graph, sourceNode);

        scanner.close();
    }
}
