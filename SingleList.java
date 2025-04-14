import java.util.*;

class SingleList {
    static final int INF = Integer.MAX_VALUE;

    static int minDistance(int[] dist, boolean[] sptSet, int n) {
        int min = INF, minIndex = -1;
        for (int v = 0; v < n; v++) {
            if (!sptSet[v] && dist[v] < min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    static void shortest(List<List<int[]>> graph, int s, int n) {
        int[] dist = new int[n];
        boolean[] sptSet = new boolean[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        for (int j = 0; j < n - 1; j++) {
            int u = minDistance(dist, sptSet, n);
            if (u == -1) break;

            sptSet[u] = true;

            // Explore neighbors of vertex u
            for (int[] edge : graph.get(u)) {
                int v = edge[0]; // neighbor vertex
                int weight = edge[1]; // edge weight

                if (!sptSet[v] && dist[u] != INF && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < n; i++) {
            System.out.println(i + " \t\t " + (dist[i] == INF ? "INF" : dist[i]));
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int n = sc.nextInt();

        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter the adjacency matrix (use INF for unreachable nodes):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String input = sc.next();
                if (!input.equalsIgnoreCase("INF")) {
                    int weight = Integer.parseInt(input);
                    graph.get(i).add(new int[]{j, weight});
                }
            }
        }

        System.out.print("Enter the source vertex: ");
        int s = sc.nextInt();

        shortest(graph, s, n);

        sc.close();
    }
}
