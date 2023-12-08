package org.testingtools;

import org.testingtools.test.RandomTestType;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        double[] nums2 = generateNumbers(100); //ГЕНЕРУЄ МАСИВ ЧИСЕЛ

        StatisticalTestRunner statisticalTestRunner = new StatisticalTestRunner(nums2);
        statisticalTestRunner.print(); // ВИВОДИТЬ МАСИВ В КОНСОЛЬ
//        statisticalTestRunner.add(RandomTestType.FREQUENCY_MONOBIT_TEST);
//        statisticalTestRunner.add(RandomTestType.FREQUENCY_TEST_WITHIN_A_BLOCK);
//        statisticalTestRunner.add(RandomTestType.DISCRETE_FOURIER_TRANSFORM_TEST);
//        statisticalTestRunner.add(RandomTestType.TESTS_FOR_THE_LONGEST_RUN_OF_ONES_IN_A_BLOCK);
//        statisticalTestRunner.add(RandomTestType.BINARY_MATRIX_RANK_TEST);
//        statisticalTestRunner.add(RandomTestType.NON_OVERLAPPING_TEMPLATE_MATCHING_TEST);
//        statisticalTestRunner.add(RandomTestType.LINEAR_COMPLEXITY_TEST);
//        statisticalTestRunner.add(RandomTestType.SERIAL_TEST);
//        statisticalTestRunner.add(RandomTestType.APPROXIMATE_ENTROPY_TEST);
//        statisticalTestRunner.add(RandomTestType.RANDOM_EXCURSIONS_TEST);
        statisticalTestRunner.addAll(); // ДОДАЄ ВСІ ТЕСТИ
        System.out.println(statisticalTestRunner.result());// ВИВОДИТЬ РЕЗУЛЬТАТ ТЕСТУВАННЯ (TRUE АБО FALSE)
        statisticalTestRunner.getInfo();// ВИВОДИТЬ ІНФОРМАЦІЮ ПРО НЕПРОЙДЕНІ ТЕСТИ ЯКЩО ТАКІ Є


        // ТЕСТУВАННЯ 2 ТЕСТІВ
        StatisticalTestRunner statisticalTestRunner2 = new StatisticalTestRunner(nums2);
        statisticalTestRunner2.add(RandomTestType.SERIAL_TEST); // ДОДАЄ 1 ТЕСТ
        statisticalTestRunner2.add(RandomTestType.FREQUENCY_MONOBIT_TEST);
        System.out.println(statisticalTestRunner2.result());
        statisticalTestRunner2.getInfo();

    }

    public static double[] generateNumbers(int length) { //метод для генерації чисел
        Random random = new Random();
        double[] numbers = new double[length];
        for (int i = 0; i < length; i++) {
            numbers[i] = random.nextDouble(); // генерація числа з плаваючою крапкою [0, 1)
        }
        return numbers;
    }
}
