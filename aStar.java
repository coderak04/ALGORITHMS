import java.util.*;

class Node implements Comparable<Node> {
    int id;
    Node parent;
    int cost;
    int heuristic;

    public Node(int id, Node parent, int cost, int heuristic) {
        this.id = id;
        this.parent = parent;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost + this.heuristic, other.cost + other.heuristic);
    }
}

public class aStar {
   public static List<Integer> aStar(int startNodeId, int goalNodeId, int numNodes, int[][] adjacencyMatrix, Map<Integer, Integer> heuristic) {
    Node startNode = new Node(startNodeId, null, 0, heuristic.get(startNodeId));
    PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost + node.heuristic)); 
    Map<Integer, Integer> costs = new HashMap<>();
    openSet.add(startNode);

    while (!openSet.isEmpty()) {
        Node current = openSet.poll();

        if (current.id == goalNodeId) {
            return constructPath(current); 
        }

        for (int i = 1; i <= numNodes; i++) {
            if (adjacencyMatrix[current.id][i] != 0) {
                int childNodeId = i;
                int stepCost = adjacencyMatrix[current.id][i];
                int totalCost = current.cost + stepCost;
                int heuristicCost = heuristic.get(childNodeId);
                int estimatedCost = totalCost + heuristicCost;

                Node childNode = new Node(childNodeId, current, totalCost, heuristicCost);

                if (!costs.containsKey(childNodeId) || totalCost < costs.get(childNodeId)) {
                    costs.put(childNodeId, totalCost);
                    openSet.add(childNode);
                }
            }
        }
    }

    return null; 
}

    private static List<Integer> constructPath(Node node) {
        List<Integer> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node.id);
            node = node.parent;
        }
        return path;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of nodes
        System.out.print("Enter the number of nodes: ");
        int numNodes = scanner.nextInt();

        // Input adjacency matrix
        int[][] adjacencyMatrix = new int[numNodes + 1][numNodes + 1];
        System.out.println("Enter the adjacency matrix (0 for no edge):");
        for (int i = 1; i <= numNodes; i++) {
            for (int j = 1; j <= numNodes; j++) {
                adjacencyMatrix[i][j] = scanner.nextInt();
            }
        }

        // Input heuristic values
        Map<Integer, Integer> heuristic = new HashMap<>();
        System.out.println("Enter heuristic values for each node:");
        for (int i = 0; i < numNodes; i++) {
            System.out.print("Heuristic value for node " + (i+1) + ": ");
            heuristic.put(i+1, scanner.nextInt());
        }

        // Input start and goal nodes
        System.out.print("Enter the start node: ");
        int startNode = scanner.nextInt();
        System.out.print("Enter the goal node: ");
        int goalNode = scanner.nextInt();

        // Run A* algorithm
        List<Integer> path = aStar(startNode, goalNode, numNodes, adjacencyMatrix, heuristic);
        if (path != null) {
            int totalCost = 0;
            System.out.print("Path found: ");
            for (int i = 0; i < path.size() - 1; i++) {
                int currentNode = path.get(i);
                int nextNode = path.get(i + 1);
                totalCost += adjacencyMatrix[currentNode][nextNode];
                System.out.print(currentNode + " -> ");
            }
            System.out.println(path.get(path.size() - 1));
            System.out.println("Total cost: " + totalCost);
        } else {
            System.out.println("No path found.");
        }

        scanner.close();
    }
}
