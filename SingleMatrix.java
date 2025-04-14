import java.util.*;

class SingleMatrix {
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

    static void shortest(int[][] graph, int s, int n) {
        int[] dist = new int[n];
        boolean[] sptSet = new boolean[n];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        for (int count = 0; count < n - 1; count++) {
            int u = minDistance(dist, sptSet, n);
            if (u == -1) break;

            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the picked vertex.
            for (int v = 0; v < n; v++) {
                if (!sptSet[v] && graph[u][v] != INF && dist[u] != INF 
                    && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
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

        int[][] graph = new int[n][n];

        System.out.println("Enter the adjacency matrix (use INF for unreachable nodes):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String input = sc.next();
                if (input.equalsIgnoreCase("INF")) {
                    graph[i][j] = INF;
                } else {
                    graph[i][j] = Integer.parseInt(input);
                }
            }
        }

        System.out.print("Enter the source vertex: ");
        int s = sc.nextInt();

        shortest(graph, s, n);

        sc.close();
    }
}


Enter the number of vertices: 4
Enter the adjacency matrix (use INF for unreachable nodes):
0 5 INF 10
INF 0 3 INF
INF INF 0 1
INF INF INF 0
Enter the source vertex: 0


Vertex 	 Distance from Source
0 		 0
1 		 5
2 		 8
3 		 9
