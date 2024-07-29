import java.util.*;

public class vertexCover {
    static class Graph {
        int V; // Number of vertices
        LinkedList<Integer>[] adj; // Adjacency list representation

        Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList<>();
        }

        void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v); // Since the graph is undirected
        }

        // Greedy algorithm to find vertex cover
        void vertexCover() {
            boolean[] visited = new boolean[V];
            for (int u = 0; u < V; u++) {
                if (!visited[u]) {
                    for (int v : adj[u]) {
                        if (!visited[v]) {
                            visited[u] = true;
                            visited[v] = true;
                            break;
                        }
                    }
                }
            }

            // Print the vertex cover
            System.out.println("Vertex Cover: ");
            for (int i = 0; i < V; i++) {
                if (visited[i])
                    System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int V = 7; // Number of vertices
        Graph graph = new Graph(V);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);

        graph.vertexCover();
    }
}
