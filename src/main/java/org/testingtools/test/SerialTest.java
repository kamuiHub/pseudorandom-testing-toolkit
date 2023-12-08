package org.testingtools.test;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.testingtools.StatisticalTestRunner;

public class SerialTest implements RandomTest {
    private static final String name = "Serial Test";
    private final StatisticalTestRunner statisticalTestRunner;

    public SerialTest(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }

    @Override
    public boolean run() {
        double[] numbers = statisticalTestRunner.arrayNumbers();
        int n = numbers.length;

        // Кількість біт для аналізу (зазвичай 2)
        int k = 2;

        // Кількість можливих комбінацій k біт
        int numCombinations = 1 << k;

        // Очікувана ймовірність для різних комбінацій
        double expectedProbability = 1.0 / numCombinations;

        // Підрахунок кількості кожної комбінації
        int[] count = new int[numCombinations];
        for (int i = 0; i < n - k + 1; i++) {
            int index = 0;
            for (int j = 0; j < k; j++) {
                index = (index << 1) + (numbers[i + j] >= 0.5 ? 1 : 0);
            }
            count[index]++;
        }

        // Розрахунок статистичного критерію
        double statistic = 0.0;
        for (int i = 0; i < numCombinations; i++) {
            double deviation = count[i] - expectedProbability * n;
            statistic += (deviation * deviation) / (expectedProbability * n);
        }

        // Визначення кількості ступенів свободи та критичного значення
        int degreesOfFreedom = numCombinations - 1;
        ChiSquaredDistribution chiSquaredDistribution = new ChiSquaredDistribution(degreesOfFreedom);
        double criticalValue = chiSquaredDistribution.inverseCumulativeProbability(0.95);

        // Порівняння статистики з критичним значенням
        return statistic <= criticalValue;
    }

    @Override
    public String name() {
        return name;
    }
}
