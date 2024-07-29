import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class KruskalAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of vertices and edges
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        Edge[] edges = new Edge[E];

        // Input the edges and their weights
        for (int i = 0; i < E; i++) {
            System.out.println("Enter details for edge " + (i + 1) + ":");
            System.out.print("Source vertex: ");
            int src = scanner.nextInt()-1;
            System.out.print("Destination vertex: ");
            int dest = scanner.nextInt()-1;
            System.out.print("Weight: ");
            int weight = scanner.nextInt();

            edges[i] = new Edge(src, dest, weight);
        }

        // Find and print the minimum spanning tree
        Edge[] result = kruskalsMST(edges, V);
        int total=0;
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : result) {
            System.out.println("(" + (edge.src + 1) + " - " + (edge.dest + 1) + ") : " + edge.weight);
            total+=edge.weight;
        }
        System.out.println("Minimum Spanning Cost= "+total);

        scanner.close();
    }

    private static Edge[] kruskalsMST(Edge[] edges, int V) {
        // Sort edges in ascending order of their weights
        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));

        Edge[] result = new Edge[V - 1];
        UnionFind uf = new UnionFind(V);

        int index = 0;
        int i = 0;
        while (index < V - 1) {
            Edge nextEdge = edges[i++];
            int x = uf.find(nextEdge.src);
            int y = uf.find(nextEdge.dest);

            // Include the edge in the result if it doesn't create a cycle
            if (x != y) {
                result[index++] = nextEdge;
                uf.union(x, y);
            }
        }

        return result;
    }

    static class UnionFind {
        int[] parent, rank;

        UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int vertex) {
            if (parent[vertex] != vertex) {
                parent[vertex] = find(parent[vertex]);
            }
            return parent[vertex];
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }
}
