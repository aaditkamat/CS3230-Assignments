import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class IdleGame {
    private static int[][] statesTable;

    private static int getOptimumState(int[] states) {
        int bestState = Integer.MAX_VALUE;
        for (int i = 0; i < states.length; i++) {
            if (states[i] < bestState) {
                bestState = states[i];
            }
        }
        return bestState;
    }
    private static int calculateMinimum(int maxLevel, int amountRequired, int[] values, int[] costs) {
       statesTable = new int[maxLevel + 1][values[maxLevel]];
       for (int i = 0; i <= maxLevel; i++) {
           for (int j = 0; j < values[maxLevel]; j++) {
               statesTable[i][j] = -1;
           }
       }
       return calculateState(0, 0, maxLevel, amountRequired, values, costs);
    }

    private static int calculateState(int level, int startAmount, int maxLevel, int amountRequired, int[] values, int[] costs) {
        if (statesTable[level][startAmount] != -1) {
            return statesTable[level][startAmount];
        }
        int[] possibleStates = new int[maxLevel - level];
        for (int nextLevel = level + 1; nextLevel <= maxLevel; nextLevel++) {
            int steps = calculateSteps(costs[nextLevel] - startAmount, values[level]);
            possibleStates[nextLevel - level - 1] = steps + 
                calculateState(nextLevel, startAmount + steps * values[level] - costs[nextLevel], 
                maxLevel, amountRequired, values, costs);
        }
        int optimumState = Math.min(getOptimumState(possibleStates), calculateSteps(amountRequired - startAmount, values[level]));
        statesTable[level][startAmount] = optimumState;
        return optimumState;
    }
    private static int calculateSteps(int amountRequired, int value) {
        return (int) (Math.ceil((double) amountRequired / value));
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
            System.out.println(calculateMinimum(maxLevel, amountRequired, values, costs));
        }
        sc.close();
    }
}
