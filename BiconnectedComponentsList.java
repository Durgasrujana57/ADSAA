import java.util.*;

public class BiconnectedComponentsList {
    private int time = 0;
    private List<int[]> edgeStack = new ArrayList<>();

    // Adding an edge to the adjacency list
    public static void addEdge(List<List<Integer>> adjList, int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    // DFS function for finding BCC
    private void DFS(List<List<Integer>> adjList, int u, int parent, int[] disc, int[] low, boolean[] visited) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adjList.get(u)) {
            if (!visited[v]) {
                children++;
                edgeStack.add(new int[]{u, v});
                DFS(adjList, v, u, disc, low, visited);

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
    public void findBCC(List<List<Integer>> adjList, int V) {
        int[] disc = new int[V], low = new int[V];
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFS(adjList, i, -1, disc, low, visited);
                if (!edgeStack.isEmpty()) printBCC(-1, -1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of vertices
        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        // Initialize adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Input number of edges
        System.out.print("Enter number of edges: ");
        int E = sc.nextInt();

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(adjList, u, v); // Add edge to adjacency list
        }

        // Finding and printing biconnected components
        System.out.println("Biconnected Components:");
        new BiconnectedComponentsList().findBCC(adjList, V);
    }
}
Enter number of vertices: 5
Enter number of edges: 5
Enter edges (u v):
1 0
0 2
2 1
0 3
3 4
Biconnected Components:
Biconnected Component: [3-4] [0-3] 
Biconnected Component: [2-1] [0-2] [1-0] 
