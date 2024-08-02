import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFSCycleDetection {

    static class Graph {
        int V;
        ArrayList<ArrayList<Edge>> adjList;

        Graph(int V) {
            this.V = V;
            adjList = new ArrayList<>(V);
            for (int i = 0; i <= V; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v, int weight) {
            adjList.get(u).add(new Edge(v, weight));
        }

        boolean isCycleUtil(int s, boolean[] visited, int parent) {
            Queue<Integer> q = new LinkedList<>();
            visited[s] = true;
            q.add(s);

            while (!q.isEmpty()) {
                int u = q.poll();

                for (Edge edge : adjList.get(u)) {
                    int v = edge.vertex;
                    if (!visited[v]) {
                        visited[v] = true;
                        q.add(v);
                        if (isCycleUtil(v, visited, u)) {
                            return true;
                        }
                    } else if (v != parent) { 
                        System.out.println("Cycle detected: ");
                        printCycleEdges(parent, u, v);
                        return true;
                    }
                }
            }
            return false;
        }

        boolean isCycle() {
            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    if (isCycleUtil(i, visited, -1)) {
                        return true;
                    }
                }
            }
            return false;
        }

        void printCycleEdges(int parent, int u, int v) {
            System.out.printf("%d -> %d\n", u, v);
            if (u != parent) {
                printCycleEdges(parent, parent, u);
            }
        }
    }

    static class Edge {
        int vertex;
        int weight;

        Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("BFSfinal1.txt"));

        String[] line = br.readLine().split(" ");
        int V = Integer.parseInt(line[0]);
        int E = Integer.parseInt(line[1]);

        Graph graph = new Graph(V);

        for (int i = 0; i < E; i++) {
            line = br.readLine().split(" ");
            int u = Integer.parseInt(line[0]);
            int v = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            graph.addEdge(u, v, weight);
        }

        br.close(); 

       
        if (graph.isCycle()) {
            System.out.println("Graph contains a cycle.");
        } else {
            System.out.println("Graph does not contain a cycle.");
        }
    }
}