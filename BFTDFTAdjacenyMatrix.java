import java.util.*;
class BFTDFTAdjacenyMatrix{
       static void bfs(int[][] adj ,int s, boolean[] visited) {
           Queue<Integer> q= new LinkedList<>();
           visited[s]=true;
           q.add(s);
           while(!q.isEmpty()) {
              int curr=q.poll();
              System.out.print(curr+" ");
              for(int i=0;i<adj.length;i++) {
                 if(adj[curr][i]==1&&!visited[i]) {
                     visited[i]=true;
                     q.add(i);
                 }
               }
           }
        }
        static void BFT(int[][] adj) {
           int v=adj.length;
           boolean[] visited = new boolean[v];
           for(int i=0;i<v;i++) {
              if(!visited[i]) {
                 bfs(adj,i,visited);
              }
           }
        }
       static void dfs(int[][] adj,int v,boolean[] visited) {
           visited[v]=true;
           System.out.print(v+" ");
           for(int i=0;i<adj.length;i++) {
               if(adj[v][i]==1 && !visited[i]) {
                   dfs(adj,i,visited);
               }    
            }
       }
       static void DFT(int[][] adj) {
           int v=adj.length;
           boolean[] visited = new boolean[v];
           for(int i=0;i<v;i++) {
              if(!visited[i]) {
                 dfs(adj,i,visited); 
              }
           }
        }
       public static void main(String args[]) {
             int v=6;
             int [][] adj = new int[v][v];
             adj[0][1]=1; adj[1][0]=1;
             adj[0][2]=1; adj[2][0]=1;
             adj[1][3]=1; adj[3][1]=1;
             adj[1][4]=1; adj[4][1]=1;
             adj[2][4]=1; adj[4][2]=1;
             adj[5][5]=1;  
             System.out.println("BFT using adjaceny matrix");
             BFT(adj);
             System.out.println("\nDFS using adjaceny matrix");
             DFT(adj);
      }
 }                                      
