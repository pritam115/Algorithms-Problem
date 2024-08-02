import java.io.*;
import java.util.*;

class GraphCycleDetection {
    private int V;
    private LinkedList<Integer>[] adjList;

    @SuppressWarnings("unchecked")
    GraphCycleDetection(int vertices) {
        V = vertices;
        adjList = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adjList[i] = new LinkedList<>();
    }

    void addEdge(int src, int dest) {
        adjList[src].add(dest);
    }

    boolean isCyclic() {
        boolean[] visited = new boolean[V];
        boolean[] inStack = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (isCyclicUtil(i, visited, inStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclicUtil(int v, boolean[] visited, boolean[] inStack) {
        if (!visited[v]) {
            visited[v] = true;
            inStack[v] = true;

            for (int neighbor : adjList[v]) {
                if (!visited[neighbor] && isCyclicUtil(neighbor, visited, inStack)) {
                    return true;
                } else if (inStack[neighbor]) {
                    System.out.println("Cycle found at edge: " + v + " -> " + neighbor);
                    return true;
                }
            }
        }
        inStack[v] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Prompt user for the file name
            System.out.print("Enter the file name containing BFS graph data: ");
            String fileName = scanner.nextLine();

            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);

            int vertices = fileReader.nextInt();
            GraphCycleDetection graph = new GraphCycleDetection(vertices);

            while (fileReader.hasNext()) {
                int src = fileReader.nextInt();
                int dest = fileReader.nextInt();
                graph.addEdge(src, dest);
            }
            fileReader.close();

            if (graph.isCyclic()) {
                System.out.println("The graph contains a cycle.");
            } else {
                System.out.println("The graph doesn't contain a cycle.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        } finally {
            // Close the scanner
            scanner.close();
        }
    }
}