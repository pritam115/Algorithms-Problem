import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class DFSTopologicalSort {
    
    static final int N = 100000;
    static List<Integer>[] adjList = new ArrayList[N];
    static boolean[] visited = new boolean[N];
    static Stack<Integer> stack = new Stack<>();

    static void dfs(int node) {
        visited[node] = true;

        for (int neighbor : adjList[node]) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }

        stack.push(node);
    }

    static void topologicalSort(int n) {
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        System.out.println("Topological Order:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File("dfsTopo.txt"));

            int nodes = in.nextInt(); 
            int edges = in.nextInt(); 

            for (int i = 1; i <= nodes; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < edges; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                adjList[u].add(v);
            }

            topologicalSort(nodes);

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}