package org.testingtools.test;

import org.testingtools.StatisticalTestRunner;

public enum RandomTestType {
    /**
     * Цей тест оцінює, чи рівномірно розподілені біти в послідовності.
     * Він рахує кількість одиничних та нульових бітів і порівнює їх з очікуваним розподілом для випадкової послідовності.
     */
    FREQUENCY_MONOBIT_TEST,

    /**
     * Цей тест подібний до FREQUENCY_MONOBIT_TEST, але використовується для блоків бітів, а не для всієї послідовності.
     * Він оцінює частоту одиничних бітів у кожному блоку.
     */
    FREQUENCY_TEST_WITHIN_A_BLOCK,

    /**
     * Цей тест перевіряє, чи є найдовший блок одиничних бітів в послідовності настільки довгим, щоб викликати підозру на відсутність випадковості.
     */
    TESTS_FOR_THE_LONGEST_RUN_OF_ONES_IN_A_BLOCK,

    /**
     * Цей тест визначає ранг бінарної матриці, побудованої на основі послідовності. Використовується для оцінки складності послідовності.
     */
    BINARY_MATRIX_RANK_TEST,

    /**
     * Цей тест використовує дискретне перетворення Фур'є для аналізу частотного складу послідовності та оцінки її випадковості.
     */
    DISCRETE_FOURIER_TRANSFORM_TEST,

    /**
     * Цей тест оцінює кількість збігів з заданим шаблоном у неперекриваючихся блоках послідовності.
     */
    NON_OVERLAPPING_TEMPLATE_MATCHING_TEST,

    /**
     * Цей тест вимірює лінійну складність послідовності, що вказує на те, наскільки складно виразити її за допомогою лінійного рекуррентного виразу.
     */
    LINEAR_COMPLEXITY_TEST,

    /**
     * Цей тест визначає частоту виникнення конкретних шаблонів бітів у послідовності та порівнює їх з очікуваним розподілом.
     */
    SERIAL_TEST,

    /**
     * Цей тест оцінює апроксимовану ентропію, яка вказує на рівень непередбачуваності частини послідовності.
     */
    APPROXIMATE_ENTROPY_TEST,

    /**
     * Цей тест визначає кількість великих випадкових відхилень (екскурсій) від нульової лінії у послідовності.
     */
    RANDOM_EXCURSIONS_TEST;

    public RandomTest getInstance(StatisticalTestRunner statisticalTestRunner) {
        switch (this) {
            case FREQUENCY_MONOBIT_TEST -> {
                return new FrequencyMonobitTest(statisticalTestRunner);
            }
            case FREQUENCY_TEST_WITHIN_A_BLOCK -> {
                return new FrequencyTestWithinBlock(statisticalTestRunner);
            }
            case DISCRETE_FOURIER_TRANSFORM_TEST -> {
                return new DiscreteFourierTransformTest(statisticalTestRunner);
            }
            case TESTS_FOR_THE_LONGEST_RUN_OF_ONES_IN_A_BLOCK -> {
                return new LongestRunOnesTest(statisticalTestRunner);
            }
            case BINARY_MATRIX_RANK_TEST -> {
                return new BinaryMatrixRankTest(statisticalTestRunner);
            }
            case NON_OVERLAPPING_TEMPLATE_MATCHING_TEST -> {
                return new NonOverlappingTemplateMatchingTest(statisticalTestRunner);
            }
            case LINEAR_COMPLEXITY_TEST -> {
                return new LinearComplexityTest(statisticalTestRunner);
            }
            case SERIAL_TEST -> {
                return new SerialTest(statisticalTestRunner);
            }
            case APPROXIMATE_ENTROPY_TEST -> {
                return new ApproximateEntropyTest(statisticalTestRunner);
            }
            case RANDOM_EXCURSIONS_TEST -> {
                return new RandomExcursionsTest(statisticalTestRunner);
            }
            default -> {
                return null;
            }
        }
    }
}
