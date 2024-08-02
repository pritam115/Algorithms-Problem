import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
public class DFS
 {
    final static int N = 10000;
    static boolean[] visited = new boolean[N];  
    static void dfsTravers(List<Integer>[] adjList, int src){
        visited[src] = true;
        System.out.print("--> "+src);
        for(int child: adjList[src]){
            if(!visited[child]){
                dfsTravers(adjList, child);
            }
        }
    }
    
    
    
    public static void main(String[] args) {
        
        try {
            Scanner in = new Scanner(new File("DFS.txt"));

            System.out.println("In Graph:");
            int n = in.nextInt();
            System.out.println("Number of nodes:" + n);
            int numberOfEdges = in.nextInt();
            System.out.println("Number of edges:" + numberOfEdges);
            

            List<Integer>[] adjList = new ArrayList[N];

            for (int i = 1; i <= n; i++)
                adjList[i] = new ArrayList<>();

//            System.out.println("Input edges as u <-> v format:");
            for (int i = 1; i <= numberOfEdges; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                adjList[u].add(v);
                adjList[v].add(u);
            }
            
            
            int src = in.nextInt();
            System.out.println("Input starting node: " + src);
            
            System.out.print("DFS travers: ");
            dfsTravers(adjList, src);
            System.out.println("");

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}