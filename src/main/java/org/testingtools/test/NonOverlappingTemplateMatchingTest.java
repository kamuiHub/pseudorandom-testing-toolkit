package org.testingtools.test;

import org.testingtools.StatisticalTestRunner;

import java.util.Random;

public class NonOverlappingTemplateMatchingTest implements RandomTest {
    private static final int TEMPLATE_LENGTH = 9; // довжина шаблону
    private static final int NUM_TEMPLATES = 8; // кількість шаблонів

    private static final String name = "Non-overlapping Template Matching Test";

    private static StatisticalTestRunner statisticalTestRunner;

    public NonOverlappingTemplateMatchingTest(StatisticalTestRunner statisticalTestRunner) {
        this.statisticalTestRunner = statisticalTestRunner;
    }


    @Override
    public boolean run() {
        double[] numbers = statisticalTestRunner.arrayNumbers();
        boolean testResult = performNonOverlappingTemplateMatchingTest(numbers);
        return testResult;
    }

    @Override
    public String name() {
        return name;
    }

    private static double[] generateNumbers() {
        Random random = new Random();
        double[] numbers = new double[statisticalTestRunner.getN()];
        for (int i = 0; i < statisticalTestRunner.getN(); i++) {
            numbers[i] = random.nextDouble(); // генерація числа з плаваючою крапкою [0, 1)
        }
        return numbers;
    }

    private static boolean performNonOverlappingTemplateMatchingTest(double[] numbers) {
        // Визначення шаблонів (прикладів) для пошуку
        double[][] templates = new double[NUM_TEMPLATES][TEMPLATE_LENGTH];
        for (int i = 0; i < NUM_TEMPLATES; i++) {
            for (int j = 0; j < TEMPLATE_LENGTH; j++) {
                templates[i][j] = Math.random(); // Замість Math.random() можна вказати власні значення шаблонів
            }
        }

        // Пошук шаблонів у випадковій послідовності
        for (double[] template : templates) {
            boolean templateFound = searchTemplate(numbers, template);
            if (templateFound) {
                return false; // Тест не пройдений, якщо знайдено хоча б один шаблон
            }
        }

        return true; // Тест пройдений, якщо не знайдено жодного шаблону
    }

    private static boolean searchTemplate(double[] numbers, double[] template) {
        for (int i = 0; i < statisticalTestRunner.getN() - TEMPLATE_LENGTH; i += TEMPLATE_LENGTH) {
            boolean match = true;
            for (int j = 0; j < TEMPLATE_LENGTH; j++) {
                if (numbers[i + j] != template[j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return true; // Знайдено шаблон у випадковій послідовності
            }
        }
        return false; // Шаблон не знайдено
    }
}

