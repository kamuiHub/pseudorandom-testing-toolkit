package org.testingtools.test;

import org.testingtools.StatisticalTestRunner;

public class LongestRunOnesTest implements RandomTest {
    private static final int MIN_RUN_LENGTH = 26; // мінімальна довжина ряду

    private static final String name = "Longest Run of Ones in a Block";
    private static StatisticalTestRunner statisticalTestRunner;

    public LongestRunOnesTest(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }

    @Override
    public boolean run() {
        double[] numbers = statisticalTestRunner.arrayNumbers();
        boolean testResult = performLongestRunOnesTest(numbers);
        return testResult;
    }

    @Override
    public String name() {
        return name;
    }

    private static boolean performLongestRunOnesTest(double[] numbers) {
        int longestRun = 0;
        int currentRun = 0;

        for (int i = 0; i < statisticalTestRunner.getN(); i++) {
            if (numbers[i] >= 0.5) {
                currentRun++;
            } else {
                longestRun = Math.max(longestRun, currentRun);
                currentRun = 0;
            }
        }

        // Перевірка найдовшого ряду одиниць у кінці послідовності
        longestRun = Math.max(longestRun, currentRun);

        // В цьому прикладі просто перевіряємо, чи найдовший ряд одиниць не перевищує задану мінімальну довжину
        return longestRun < MIN_RUN_LENGTH;
    }
}

