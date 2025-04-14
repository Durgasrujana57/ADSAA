import java.util.*;

public class BiconnectedComponentsMatrix {
    private int time = 0;
    private List<int[]> edgeStack = new ArrayList<>();

    // Adding an edge to the adjacency matrix
    public static void addEdge(int[][] matrix, int u, int v) {
        matrix[u][v] = 1;
        matrix[v][u] = 1;
    }

    // DFS function for finding BCC
    private void DFS(int[][] matrix, int u, int parent, int[] disc, int[] low, boolean[] visited, int V) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v = 0; v < V; v++) {
            if (matrix[u][v] == 1) { // There is an edge between u and v
                if (!visited[v]) {
                    children++;
                    edgeStack.add(new int[]{u, v});
                    DFS(matrix, v, u, disc, low, visited, V);

                    low[u] = Math.min(low[u], low[v]);

                    if ((parent == -1 && children > 1) || (parent != -1 && low[v] >= disc[u])) {
                        printBCC(u, v);
                    }
                } else if (v != parent && disc[v] < disc[u]) {
                    low[u] = Math.min(low[u], disc[v]);
                    edgeStack.add(new int[]{u, v});
                }
            }
        }
    }

    // Print the BCC (biconnected component)
    private void printBCC(int u, int v) {
        System.out.print("Biconnected Component: ");
        while (!edgeStack.isEmpty()) {
            int[] edge = edgeStack.remove(edgeStack.size() - 1);
            System.out.print("[" + edge[0] + "-" + edge[1] + "] ");
            if (edge[0] == u && edge[1] == v) break;
        }
        System.out.println();
    }

    // Function to find all BCCs in the graph
    public void findBCC(int[][] matrix, int V) {
        int[] disc = new int[V], low = new int[V];
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFS(matrix, i, -1, disc, low, visited, V);
                if (!edgeStack.isEmpty()) printBCC(-1, -1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        // Initialize adjacency matrix
        int[][] matrix = new int[V][V];

        // Input number of edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(matrix, u, v); // Add edge to adjacency matrix
        }

        // Finding and printing biconnected components
        System.out.println("Biconnected Components:");
        new BiconnectedComponents().findBCC(matrix, V);
    }
}
