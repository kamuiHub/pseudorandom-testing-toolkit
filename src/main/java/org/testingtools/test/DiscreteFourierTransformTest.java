package org.testingtools.test;

import org.testingtools.StatisticalTestRunner;

public class DiscreteFourierTransformTest implements RandomTest {
    private static final String name = "Discrete Fourier Transform (Spectral) Test";
    private static StatisticalTestRunner statisticalTestRunner;

    public DiscreteFourierTransformTest(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }

    @Override
    public boolean run() {
        double[] numbers = statisticalTestRunner.arrayNumbers();
        boolean testResult = performDiscreteFourierTransformTest(numbers);
        return testResult;
    }

    @Override
    public String name() {
        return name;
    }

    private static boolean performDiscreteFourierTransformTest(double[] numbers) {
        // Обчислення дискретного перетворення Фур'є
        double[] real = new double[statisticalTestRunner.getN()];
        double[] imag = new double[statisticalTestRunner.getN()];

        for (int k = 0; k < statisticalTestRunner.getN(); k++) {
            for (int n = 0; n < statisticalTestRunner.getN(); n++) {
                double angle = 2 * Math.PI * k * n / statisticalTestRunner.getN();
                real[k] += numbers[n] * Math.cos(angle);
                imag[k] -= numbers[n] * Math.sin(angle);
            }
        }

        // Обчислення амплітуд для кожної частоти
        double[] amplitudes = new double[statisticalTestRunner.getN()];
        for (int k = 0; k < statisticalTestRunner.getN(); k++) {
            amplitudes[k] = Math.sqrt(real[k] * real[k] + imag[k] * imag[k]) / statisticalTestRunner.getN();
        }

        // В цьому прикладі просто перевіряємо, чи найвища амплітуда менше за певний поріг
        double threshold = 1.0; // Поріг для амплітуди
        double maxAmplitude = getMaxAmplitude(amplitudes);
        return maxAmplitude < threshold;
    }

    private static double getMaxAmplitude(double[] amplitudes) {
        double maxAmplitude = 0;
        for (double amplitude : amplitudes) {
            if (amplitude > maxAmplitude) {
                maxAmplitude = amplitude;
            }
        }
        return maxAmplitude;
    }
}

