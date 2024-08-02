import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Edge2 {
    int src, dest, weight;

    public Edge2(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Kruskal {

    private static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge a, Edge b) {
            return Integer.compare(b.weight, a.weight); // Compare in reverse order for maximum spanning tree
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
        int V = Integer.parseInt(br.readLine()); // Read the number of vertices
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            int src = Integer.parseInt(parts[0]);
            int dest = Integer.parseInt(parts[1]);
            int weight = Integer.parseInt(parts[2]);
            graph.add(new Edge(src, dest, weight));
        }
        ArrayList<Edge> mst = kruskalMST(graph, V);

        // Remove the maximum weight edge to find the second maximum spanning tree
        Edge max1 = mst.remove(0);
        Edge max2 = mst.remove(0);
        Edge max3 = mst.remove(0);

        System.out.println("Third maximum spanning tree:");
        System.out.println("Edge 1: " + max1.src + " - " + max1.dest + " : " + max1.weight);
        System.out.println("Edge 2: " + max2.src + " - " + max2.dest + " : " + max2.weight);
        System.out.println("Edge 3: " + max3.src + " - " + max3.dest + " : " + max3.weight);
    }
}
