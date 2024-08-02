import java.util.Scanner;

public class LCS {
    public static String longestCommonSubsequence(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        int[][] LCS = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    LCS[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    LCS[i][j] = 1 + LCS[i - 1][j - 1];
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

        // Traceback to find the actual LCS
        int i = m, j = n;
        StringBuilder lcs = new StringBuilder();

        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcs.insert(0, X.charAt(i - 1));
                i--;
                j--;
            } else if (LCS[i - 1][j] > LCS[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter first String: ");
        String X = in.nextLine();

        System.out.println("Enter second String: ");
        String Y = in.nextLine();

        String lcs = longestCommonSubsequence(X, Y);
        System.out.println("Length: " + lcs.length());
        System.out.println("Longest Common Subsequence: " + lcs);
    }
}