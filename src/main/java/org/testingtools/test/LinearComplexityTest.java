package org.testingtools.test;

import org.testingtools.StatisticalTestRunner;

import java.util.Arrays;

public class LinearComplexityTest implements RandomTest {
    private static final String name = "Linear Complexity Test";
    private final StatisticalTestRunner statisticalTestRunner;

    public LinearComplexityTest(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }

    @Override
    public boolean run() {
        double[] pseudoRandomNumbers = statisticalTestRunner.arrayNumbers();
        int minL = 50;
        int complexity = linearComplexityTest(pseudoRandomNumbers);
        return complexity >= minL;
    }

    @Override
    public String name() {
        return name;
    }

    private int linearComplexityTest(double[] data) {
        int n = data.length;
        int m = n / 2;
        int[] b = new int[m + 2];
        int[] c = new int[m + 2];

        b[0] = 2;
        c[0] = 1;

        int L = 0;
        int m1 = 0;

        for (int i = 1; i <= m; i++) {
            long bits = Double.doubleToLongBits(data[i - 1]);
            int d = (int) (bits ^ (bits >>> 32));

            for (int j = 1; j <= L; j++) {
                d ^= c[j] * (bits >>> (32 - j));
            }

            d = Math.abs(d);

            if (d != 0) {
                int[] t = Arrays.copyOf(c, c.length);
                for (int k = 1; k <= m; k++) {
                    if (b[k] == 1) {
                        c[k] ^= t[k];
                    }
                }
                if (2 * m1 <= i) {
                    m1 = i - m1;
                    L = i;
                    System.arraycopy(c, 0, b, 0, c.length);
                }
            }
        }
        return L;
    }
}
