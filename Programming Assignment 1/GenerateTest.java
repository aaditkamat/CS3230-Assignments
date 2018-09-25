import java.util.Random;

public class GenerateTest{
    public static void main(String[] args) {
        Random rng = new Random();
        int testCases = 3;
        System.out.println(testCases);
        for (int j = 0; j < testCases; j++) {
            int maxLevel = rng.nextInt(10) + 1, amountRequired = rng.nextInt(10) + 1;
            System.out.println(maxLevel);
            System.out.println(amountRequired);
            int[] costs = new int[maxLevel + 1];
            int[] values = new int[maxLevel + 1];
            for (int i = 0; i< maxLevel; i++) {
                if (i == 0) {
                    values[i] = rng.nextInt(10 / maxLevel) + 1;
                    costs[i] = 0;
                }
                else {
                    values[i] = rng.nextInt(10/ maxLevel) + values[i - 1] + 1;
                    costs[i] = rng.nextInt(10 / maxLevel) + costs[i - 1] + 1;
                }
            }
            for (int i = 0; i <= maxLevel; i++) {
                System.out.print(values[i] + " ");
            }
            System.out.println();
            for (int i = 0; i <= maxLevel; i++) {
                System.out.print(costs[i] + " ");
            }
        }
    }
}