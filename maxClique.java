import java.util.*;

public class maxClique {

    public static boolean isClique(int[][] graph, List<Integer> clique) {
        for (int i = 0; i < clique.size(); i++) {
            for (int j = i + 1; j < clique.size(); j++) {
                if (graph[clique.get(i)][clique.get(j)] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void maximumClique(int[][] graph, int vertexCount, List<Integer> currentClique, List<Integer> maxClique, int currentVertex) {
        if (currentVertex == vertexCount) {
            if (currentClique.size() > maxClique.size() && isClique(graph, currentClique)) {
                maxClique.clear();
                maxClique.addAll(currentClique);
            }
            return;
        }

        currentClique.add(currentVertex);
        maximumClique(graph, vertexCount, currentClique, maxClique, currentVertex + 1);

        currentClique.remove(currentClique.size() - 1);
        maximumClique(graph, vertexCount, currentClique, maxClique, currentVertex + 1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int N = scanner.nextInt();
        int[][] graph = new int[N][N];

        scanner.nextLine(); // consume newline character
        System.out.println("Enter the edges separated by space (node1 node2): ");
        while (true) {
            String edgeInput = scanner.nextLine();
            if (edgeInput.equals("")) {
                break;
            }
            String[] edge = edgeInput.split("\\s+");
            int node1 = Integer.parseInt(edge[0]);
            int node2 = Integer.parseInt(edge[1]);
            graph[node1][node2] = 1;
            graph[node2][node1] = 1; // Assuming undirected graph
        }

        List<Integer> maxClique = new ArrayList<>();
        List<Integer> currentClique = new ArrayList<>();
        maximumClique(graph, N, currentClique, maxClique, 0);

        System.out.println("Size of Maximum Clique: " + maxClique.size());
        System.out.println("Maximum Clique: " + maxClique);
    }
}
