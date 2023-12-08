package org.testingtools.test;

import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import org.testingtools.StatisticalTestRunner;

public class ApproximateEntropyTest implements RandomTest {
    private static final String name = "Approximate Entropy Test";
    private final StatisticalTestRunner statisticalTestRunner;

    public ApproximateEntropyTest(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }

    @Override
    public boolean run() {
        double[] numbers = statisticalTestRunner.arrayNumbers();
        int n = numbers.length;

        // Довжина шаблону (зазвичай 10)
        int m = 10;

        // Значення r
        int r = 5;

        // Обчислення апроксимованої ентропії для двох значень m
        double[] approxEntropy = new double[2];
        for (int i = 0; i < 2; i++) {
            approxEntropy[i] = calculateApproximateEntropy(numbers, m + i, r);
        }

        // Визначення порогового значення (може бути налаштовано)
        double threshold = calculateThreshold(n, m, r);

        // Порівняння результатів з пороговим значенням
        return !Double.isNaN(approxEntropy[0]) && !Double.isNaN(approxEntropy[1]) &&
                approxEntropy[0] >= threshold && approxEntropy[1] >= threshold;
    }

    @Override
    public String name() {
        return name;
    }

    private double calculateApproximateEntropy(double[] data, int m, int r) {
        int n = data.length;
        int[] count = new int[1 << m];
        double[] phi = new double[n - m + 1];

        for (int i = 0; i < n - m + 1; i++) {
            int pattern = 0;
            for (int j = 0; j < m; j++) {
                pattern = (pattern << 1) + (data[i + j] >= 0.5 ? 1 : 0);
            }
            count[pattern]++;
        }

        for (int i = 0; i < n - m + 1; i++) {
            int pattern = 0;
            for (int j = 0; j < m; j++) {
                pattern = (pattern << 1) + (data[i + j] >= 0.5 ? 1 : 0);
            }

            double probability = (count[pattern] - r) / (double) (n - m + 1 - r);

            // Уникнення обчислення логарифмів для нульових або дуже малих значень
            phi[i] = probability > 0 ? Math.log(probability) : 0;
        }

        double sumPhi = 0.0;
        for (double value : phi) {
            sumPhi += value;
        }

        return sumPhi / (n - m + 1);
    }

    private double calculateThreshold(int n, int m, int r) {
        // Використовуйте квантиль для визначення порогового значення
        Percentile percentile = new Percentile();
        double[] thresholds = new double[n - m - r];
        for (int i = 0; i < thresholds.length; i++) {
            thresholds[i] = Math.log(0.05) * (n - m + 1 - r + i);
        }
        return percentile.evaluate(thresholds);
    }
}
