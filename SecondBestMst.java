
import java.util.*;
 
public class SecondBestMst {

  static int[] parent = new int[100005];

  static List<Integer> present = new ArrayList<>();
  static int edg;
 
  static class Edge implements Comparable<Edge> {
    int src, dest, weight;
 
    Edge(int src, int dest, int weight) {
      this.src = src;
      this.dest = dest;
      this.weight = weight;
    }
    public int compareTo(Edge other) {
      return this.weight - other.weight;
    }
  }
 
  static void initialise(int n) {

    for (int i = 1; i <= n; i++)
      parent[i] = i;
  }
  static int find(int x) {
    if (parent[x] == x)
      return x;
    return parent[x] = find(parent[x]);
  }
  static int union1(int i, int sum, Edge[] edges) {
    int x, y;
    x = find(edges[i].src);
    y = find(edges[i].dest);
    if (x != y) {
      parent[x] = y;
      present.add(i);
      sum += edges[i].weight;
    }
    return sum;
  }
  static int union2(int i, int sum, Edge[] edges) {
    int x, y;
    x = find(edges[i].src);
    y = find(edges[i].dest);
    if (x != y) {
      parent[x] = y;
      sum += edges[i].weight;
      edg++;
    }
    return sum;
  }
 
  public static void main(String[] args) {
    int V = 5, E = 8;
    initialise(V);
    int[] source = {1, 3, 2, 3, 2, 5, 1, 3};
    int[] destination = {3, 4, 4, 2, 5, 4, 2, 5};
    int[] weights = {75, 51, 19, 95, 42, 31, 9, 66};
    Edge[] edges = new Edge[E];
    for (int i = 0; i < E; i++) {
      edges[i] = new Edge(source[i], destination[i], weights[i]);
    }

    Arrays.sort(edges);
 
    int sum = 0;
    for (int i = 0; i < E; i++) {
      sum = union1(i, sum, edges);
    }

    System.out.println("MST: " + sum);

    int sec_best_mst = Integer.MAX_VALUE;

    sum = 0;    
    int j;
    for (j = 0; j < present.size(); j++) {
      initialise(V);
      edg = 0;
      for (int i = 0; i < E; i++) {

        if (i == present.get(j))
          continue;
        sum = union2(i, sum, edges);
      }
 
      if (edg != V - 1) {
        sum = 0;
        continue;
      }
 
      if (sec_best_mst > sum)
        sec_best_mst = sum;
      sum = 0;
    }
 
    System.out.println("Second Best MST: " + sec_best_mst);
  }
}
    

