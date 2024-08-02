import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Kruskal {

    private static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge a, Edge b) {
            return Integer.compare(a.weight, b.weight);
        }
    }

    public static ArrayList<Edge> kruskalMST(ArrayList<Edge> graph, int V) {
        ArrayList<Edge> mst = new ArrayList<>();
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(V, new EdgeComparator());
        for (Edge edge : graph) {
            pq.add(edge);
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int srcParent = find(parent, edge.src);
            int destParent = find(parent, edge.dest);
            if (srcParent != destParent) {
                mst.add(edge);
                union(parent, srcParent, destParent);
            }
        }

        return mst;
    }

    private static int find(int[] parent, int i) {
        if (parent[i] == i) {
            return i;
        }
        return parent[i] = find(parent, parent[i]);
    }

    private static void union(int[] parent, int x, int y) {
        parent[x] = y;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        ArrayList<Edge> graph = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length != 3) {
                // Handle the case where the line doesn't have three elements
                System.err.println("Invalid input format: " + line);
                continue; // Skip this line and proceed with the next one
            }
            int src = Integer.parseInt(parts[0]);
            int dest = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            graph.add(new Edge(src, dest, weight));
        }
        
        int V = 7;
        ArrayList<Edge> mst = kruskalMST(graph, V);
        for (Edge edge : mst) {
            System.out.println(edge.src + " - " + edge.dest + " : " + edge.weight);
        }
    }
}