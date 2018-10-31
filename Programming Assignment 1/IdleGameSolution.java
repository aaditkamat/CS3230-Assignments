import java.io.*;
import java.math.*;
import java.util.*;

class IdleGameSolution {
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

    private static int calculateMinimum(int maxLevel, int amountRequired, int[] values, int[] costs) {
    	int[][] memo = new int[maxLevel + 1][values[maxLevel] + 1]; // declare memoization table
        for (int[] row : memo) {
            Arrays.fill(row, -1); // initialise table to -1 to indicate answer not memoized
        }
    	return recurse(0, 0, amountRequired, values, costs, memo); // calculate answer starting at level 0 with 0 cookies
    }

    private static int recurse(int currentLevel, int currentAmount, int amountRequired, int[] values, int[] costs, int[][] memo) {
		if (currentAmount >= amountRequired) return 0; // no taps needed as already reached required amount
    	if (memo[currentLevel][currentAmount] >= 0) return memo[currentLevel][currentAmount]; // return answer if already memoized
    	int bestAnswer = (amountRequired - currentAmount + values[currentLevel] - 1) / values[currentLevel]; // number of taps needed if don't upgrade anymore
    	for (int level = currentLevel + 1; level < values.length; ++level) {
    		int taps = Math.max((costs[level] - currentAmount + values[currentLevel] - 1) / values[currentLevel], 0); // minimum number of taps to reach cost to upgrade to level
    		bestAnswer = Math.min(bestAnswer, taps + recurse(level, currentAmount + taps * values[currentLevel] - costs[level], amountRequired, values, costs, memo)); // recursion
    	}
    	return memo[currentLevel][currentAmount] = bestAnswer; // memoize answer
    }
}