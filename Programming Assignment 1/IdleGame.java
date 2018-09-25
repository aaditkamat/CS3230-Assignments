import java.io.*;
import java.math.*;
import java.util.*;

class IdleGame {
    private static ArrayList<Integer> stepsList;

    private static int calculateMinimum(int maxLevel, int amountRequired, int[] values, int[] costs) {
        calculateSteps(maxLevel, amountRequired, values, costs, 0);
        stepsList.sort(Integer::compareTo);
    	return stepsList.get(0);
    }

    private static void calculateSteps(int maxLevel, int amountRequired, int[] values, int[] costs, int steps) {
        stepsList.add((int)Math.ceil(amountRequired / (float) values[0]) + steps);
        for (int i = 1; i < 1 + maxLevel; i++) {
            int sum = steps;
            sum += (int) Math.ceil((float)costs[i] / values[0]);
            sum += (int) Math.ceil((amountRequired - (sum - steps) * values[0] + costs[i]) / (float)values[i]);
            stepsList.add(sum);
        }
        for (int i = 1; i < 1 + maxLevel; i++) {
            costs[i - 1] = costs[i];
        }
        steps += (int) Math.ceil(costs[0] / (float) values[0]);
        amountRequired -= values[0] * steps;
        if (maxLevel > 0)
            amountRequired += costs[0];
        for (int i = 1; i < 1 + maxLevel; i++) {
            values[i - 1] = values[i];
        }
        if (maxLevel > 0)
            calculateSteps(--maxLevel, amountRequired, values, costs, steps);
    }

    public static void main(String[] args) {
        stepsList = new ArrayList<>();
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