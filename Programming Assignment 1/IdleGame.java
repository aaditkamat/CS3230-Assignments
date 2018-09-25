import java.io.*;
import java.math.*;
import java.util.*;

class IdleGame {
    private static int calculateMinimum(int maxLevel, int amountRequired, int[] values, int[] costs) {
    	return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int tc = 0; tc < testCases; tc++) {
            int maxLevel = sc.nextInt();
            int amountRequired = sc.nextInt();
            int[] values = new int[maxLevel + 1];
            for (int i = 0; i <= maxLevel; i++) {
                values[i] = sc.nextInt();
            }
            int[] costs = new int[maxLevel + 1];
            for (int i = 0; i <= maxLevel; i++) {
                costs[i] = sc.nextInt();
            }
            int solution = calculateMinimum(maxLevel, amountRequired, values, costs);
            System.out.println(solution);
        }
    }
}