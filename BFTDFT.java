import java.util.*;
public class BFTDFT {
      private static void BFT(List<List<Integer>>adj) {
         boolean[] visited= new boolean[adj.size()];
         for(int i=0;i<adj.size();i++) {
             if(!visited[i]) {
                 BFs(adj,i,visited);
             }
          }
       }
       private static void BFs(List<List<Integer>>adj,int s,boolean[] visited) {
           Queue<Integer> q=new LinkedList<>();
           visited[s]=true;
           q.add(s);
           while(!q.isEmpty()) {
              int curr=q.poll();
              System.out.print(curr+" ");
              for(int x:adj.get(curr)) {
                   if(!visited[x]) {
                       visited[x]=true;
                       q.add(x);
                   }
              }
           }
       }
       public static void addEdge(List<List<Integer>>adj,int u,int v) {
             adj.get(u).add(v);
             adj.get(v).add(u);
       }      
       private static void DFT(List<List<Integer>>adj) {
             boolean[] visited = new boolean[adj.size()];
             for(int i=0;i<adj.size();i++) {
                if(!visited[i]) {
                        DFs(adj,i,visited);
                }
             }
       } 
       private static void DFs(List<List<Integer>>adj,int s,boolean[] visited) {
              visited[s]=true;
              System.out.print(s+" ");
              for(int x:adj.get(s)) {
                 if(!visited[x]) {
                     DFs(adj,x,visited);
                 }
              }
       }
       public static void main(String args[]) {
              int v=6;
              List<List<Integer>>adj=new ArrayList<>(v);
              for(int i=0;i<v;i++) {
                 adj.add(new ArrayList<>());
              }
              addEdge(adj,0,5);
              addEdge(adj,0,4);
              addEdge(adj,3,4);
              addEdge(adj,1,2);
              System.out.println("BFT using adjacency List");
              BFT(adj);
              System.out.println("\nDFT using adjacency List");
              DFT(adj);   
       }
   }                                                       
