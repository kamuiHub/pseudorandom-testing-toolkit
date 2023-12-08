package org.testingtools.test;

import org.testingtools.StatisticalTestRunner;

import java.util.Arrays;

public class FrequencyTestWithinBlock implements RandomTest {
    private static final String name = "Frequency Test within a Block";

    private static final int M = 20; // розмір блоку
    private static StatisticalTestRunner statisticalTestRunner;

    public FrequencyTestWithinBlock(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }

    @Override
    public boolean run() {
        double[] numbers = statisticalTestRunner.arrayNumbers();
        boolean testResult = performFrequencyTestWithinBlock(numbers, M);
        return testResult;
    }

    @Override
    public String name() {
        return name;
    }

    private static boolean performFrequencyTestWithinBlock(double[] numbers, int blockSize) {
        int numBlocks = statisticalTestRunner.getN() / blockSize;
        int[] onesCounts = new int[numBlocks];

        for (int i = 0; i < numBlocks; i++) {
            double[] block = Arrays.copyOfRange(numbers, i * blockSize, (i + 1) * blockSize);
            onesCounts[i] = countOnes(block);
        }

        // Тут можна додатково обробити і оцінити результат тесту
        // В цьому прикладі просто перевіряємо, чи всі блоки мають приблизно однакову кількість одиниць
        int averageOnesCount = Arrays.stream(onesCounts).sum() / numBlocks;
        int tolerance = blockSize / 2; // Припустимий толеранс для середньої кількості одиниць в блоку
        return Arrays.stream(onesCounts).allMatch(count -> Math.abs(count - averageOnesCount) <= tolerance);
    }

    private static int countOnes(double[] numbers) {
        int count = 0;
        for (double number : numbers) {
            if (number >= 0.5) {
                count++;
            }
        }
        return count;
    }
}
