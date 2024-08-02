import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class BFS {
    static final int N = 100000;
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited = new boolean[N];

    static void bfs(List<Integer>[] adjList, int n, int src) {
        q.add(src);
        visited[src] = true;

        while (!q.isEmpty()) {
            int parentNode = q.poll();
            System.out.println(" -> " + parentNode);
            for (int child : adjList[parentNode]) {
                if (!visited[child]) {
                    q.add(child);
                    visited[child] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        
        
        try {
            Scanner in = new Scanner(new File("BFS.txt"));

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

            System.out.println("BFS traversal:");
            bfs(adjList, n, src);

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}