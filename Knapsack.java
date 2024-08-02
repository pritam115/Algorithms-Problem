import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Knapsack {
    // A utility function that returns the maximum of two integers
    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static void printSelectedItems(int W, int wt[], int val[], int n, int P[][]) {
        int i = n;
        int w = W;

        while (i > 0 && w > 0) {
            if (P[i][w] != P[i - 1][w]) {
                System.out.println("Item " + i + " with value " + val[i - 1] + " and weight " + wt[i - 1]);
                w = w - wt[i - 1];
            }
            i--;
        }
    }
 // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSack(int W, int wt[], int val[], int n) {
        int i, w;
        int P[][] = new int[n + 1][W + 1];
 // Build table K[][] in a bottom-up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    P[i][w] = 0;
                else if (wt[i - 1] <= w)
                    P[i][w] = max(val[i - 1] + P[i - 1][w - wt[i - 1]], P[i - 1][w]);
                else
                    P[i][w] = P[i - 1][w];
            }
        }

        // Print selected items
        System.out.println("Selected Items:");
        printSelectedItems(W, wt, val, n, P);

        return P[n][W];
    }

    // main method
    public static void main(String args[]) {
        try {
            File file = new File("knapsack.txt"); 
            Scanner sc = new Scanner(file);

            int n = sc.nextInt();
            int W = sc.nextInt();
            int val[] = new int[n];
            int wt[] = new int[n];

//            System.out.println("Enter the values of items");
            for (int i = 0; i < n; i++) {
                val[i] = sc.nextInt();
            }

//            System.out.println("Enter the weights of items");
            for (int i = 0; i < n; i++) {
                wt[i] = sc.nextInt();
            }

            System.out.println("Maximum total profit = " + knapSack(W, wt, val, n));

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}