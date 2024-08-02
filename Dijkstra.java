import java.io.*;
import java.util.*;

class Dijkstra {
    private static final int V = 5;

    int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    void printSolution(int dist[], int n) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    void dijkstra(int graph[][], int src) {
        int dist[] = new int[V];
        Boolean sptSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);

            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist, V);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("graph2.txt"));
        int graph[][] = new int[V][V];

        for (int i = 0; i < V; i++) {
            String[] line = reader.readLine().split(" ");
            for (int j = 0; j < V; j++) {
                graph[i][j] = Integer.parseInt(line[j]);
            }
        }

        Dijkstra t = new Dijkstra();
        t.dijkstra(graph, 0);
    }
}