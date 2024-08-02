import java.util.*;

class Graph {
    private int V;
    private LinkedList<Integer> adj[];

    public Graph(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList();
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    public List<Integer> primMST() {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        int[] key = new int[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        for (int i = 0; i < V - 1; i++) {
            int u = -1;
            for (int j = 0; j < V; j++) {
                if (!visited[j] && (u == -1 || key[j] < key[u])) {
                    u = j;
                }
            }

            visited[u] = true;
            result.add(parent[u]);
            result.add(u);

            for (int v : adj[u]) {
                if (!visited[v] && key[v] > 0) {
                    parent[v] = u;
                    key[v] = 0;
                }
            }
        }

        return result;
    }

    public boolean isCyclic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isCyclic'");
    }
}

 class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(9);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(3, 8);
        graph.addEdge(4, 8);
        graph.addEdge(5, 8);
        graph.addEdge(6, 8);
        graph.addEdge(7, 8);

        List<Integer> result = graph.primMST();

        int sum = 0;
        for (int i = 0; i < result.size(); i += 2) {
            int x = result.get(i);
            int y = result.get(i + 1);
            if (x % 10 == 0 || y % 10 == 0) {
                sum += x % 10 == 0 ? x % 100 + x % 10 : y % 100 + y % 10;
            }
        }

        System.out.println("Edges that construct the Minimum Spanning tree are:");
        for (int i = 0; i < result.size(); i += 2) {
            System.out.println(result.get(i) + " -- " + result.get(i + 1));
        }

        System.out.println("The minimum cost is: " + sum);
    }
}
