package org.testingtools.test;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.testingtools.StatisticalTestRunner;

public class BinaryMatrixRankTest implements RandomTest {
    private static final int MATRIX_SIZE = 32; // розмір квадратної бінарної матриці
    private static final String name = "Binary Matrix Rank Test";
    private static StatisticalTestRunner statisticalTestRunner;

    public BinaryMatrixRankTest(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }

    @Override
    public boolean run() {
        double[] numbers = statisticalTestRunner.arrayNumbers();
        boolean testResult = performBinaryMatrixRankTest(numbers);
        return testResult;
    }

    @Override
    public String name() {
        return name;
    }

    private static boolean performBinaryMatrixRankTest(double[] numbers) {
        int[][] binaryMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        int count = 0;

        // Заповнення бінарної матриці
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                binaryMatrix[i][j] = (numbers[count] >= 0.5) ? 1 : 0;
                count = (count + 1) % statisticalTestRunner.getN();
            }
        }

        // Обчислення рангу бінарної матриці з використанням Apache Commons Math
        int rank = calculateRank(binaryMatrix);

        // В цьому прикладі перевіряємо, чи ранг матриці не менший половини її розміру
        return rank >= MATRIX_SIZE / 2;
    }

    private static int calculateRank(int[][] matrix) {
        // Перетворення бінарної матриці на матрицю з плаваючою крапкою
        double[][] realMatrix = new double[MATRIX_SIZE][MATRIX_SIZE];
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                realMatrix[i][j] = matrix[i][j];
            }
        }

        // Створення об'єкту бібліотеки Apache Commons Math для матриці
        Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(realMatrix);

        // Використання SVD (Singular Value Decomposition) для обчислення рангу
        SingularValueDecomposition svd = new SingularValueDecomposition(array2DRowRealMatrix);
        double[] singularValues = svd.getSingularValues();

        // Рахуємо ненульові сингулярні значення
        int rank = 0;
        for (double singularValue : singularValues) {
            if (singularValue > 1e-12) {
                rank++;
            }
        }

        return rank;
    }
}
