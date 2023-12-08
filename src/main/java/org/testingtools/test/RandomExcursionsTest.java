package org.testingtools.test;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.testingtools.StatisticalTestRunner;

public class RandomExcursionsTest implements RandomTest {
    private static final String name = "Random Excursions Test";
    private final StatisticalTestRunner statisticalTestRunner;

    public RandomExcursionsTest(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }

    @Override
    public boolean run() {
        double[] numbers = statisticalTestRunner.arrayNumbers();
        int n = numbers.length;

        // Кількість бітів в послідовності, яку будемо аналізувати (зазвичай 8)
        int blockSize = 8;

        // Визначення порогового значення (може бути налаштовано)
        double threshold = calculateThreshold(n, blockSize);

        // Перевірка, чи випадкові відхилення перетинають порогове значення
        int state = 0;
        int x = 0;

        for (double number : numbers) {
            x += (number >= 0.5) ? 1 : -1;

            if (x == 0) {
                state = 0;
            } else if (Math.abs(x) == blockSize) {
                state++;
                x = 0;
            }
        }

        return state <= threshold;
    }

    @Override
    public String name() {
        return name;
    }

    private double calculateThreshold(int n, int blockSize) {
        // Визначення порогового значення (може бути налаштовано)
        NormalDistribution normalDistribution = new NormalDistribution();

        // Кількість випробувань
        int numTrials = (n / blockSize) - 1;

        // Використовуємо 95% квантиль розподілу Стьюдента
        double quantile = normalDistribution.inverseCumulativeProbability(1 - 0.05 / (2 * numTrials));

        // Розрахунок порогового значення
        return Math.sqrt(2 * numTrials) * quantile + numTrials;
    }
}
