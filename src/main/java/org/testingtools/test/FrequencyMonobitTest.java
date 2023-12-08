package org.testingtools.test;

import org.apache.commons.math3.special.Erf;
import org.testingtools.StatisticalTestRunner;

public class FrequencyMonobitTest implements RandomTest {
    private static final String name = "Frequency (Monobit) Test";

    private static StatisticalTestRunner statisticalTestRunner;

    public FrequencyMonobitTest(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }

    @Override
    public boolean run() {
        double[] numbers = statisticalTestRunner.arrayNumbers();
        int onesCount = countOnes(numbers);
        double pValue = calculatePValue(onesCount);
        return pValue >= 0.01;
    }

    @Override
    public String name() {
        return name;
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

    private static double calculatePValue(int onesCount) {
        double s = Math.abs(onesCount - statisticalTestRunner.getN() / 2) / Math.sqrt(statisticalTestRunner.getN() / 4.0);
        return Erf.erfc(s / Math.sqrt(2));
    }
}

