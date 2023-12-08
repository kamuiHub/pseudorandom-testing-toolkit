package org.testingtools;

import org.testingtools.test.RandomTest;
import org.testingtools.test.RandomTestType;

import java.util.*;

public class StatisticalTestRunner {

    private double[] numbers;

    private Set<RandomTest> tests = new HashSet<>();

    private List<RandomTest> failedTests = new ArrayList<>();

    public StatisticalTestRunner(double[] numbers) {
        this.numbers = numbers;
    }

    public void print() {
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + ",");
        }
        System.out.println();
    }

    public double[] arrayNumbers() {
        return numbers;
    }

    public int getN() {
        return numbers.length;
    }

    public void add(RandomTestType randomTestType) {
        RandomTest currentTest = randomTestType.getInstance(this);
        tests.add(currentTest);
    }

    public void addAll() {
        for (RandomTestType current : RandomTestType.values()) {
            RandomTest currentTest = current.getInstance(this);
            tests.add(currentTest);
        }
    }

    public boolean result() {
        for (RandomTest current : tests) {
            if (current.run() == false) {
                failedTests.add(current);
            }
        }
        if (failedTests.size() > 0) {
            return false;
        }
        return true;
    }

    public void getInfo() {
        for (RandomTest current : failedTests) {
            System.out.println(current.name() + " - failed");
        }
    }
}
