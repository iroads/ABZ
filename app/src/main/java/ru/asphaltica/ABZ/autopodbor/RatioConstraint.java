package ru.asphaltica.ABZ.autopodbor;

import ru.asphaltica.ABZ.enumerated.GrainTable;

/**
 * Ограничение на соотношение долей двух компонентов смеси:
 * {@code minRatio <= w[componentA] / w[componentB] <= maxRatio}.
 *
 * Используется вместе с {@link GradationFit} для задания требований вида
 * "доля щебня 2-4 должна быть от 0.5 до 2 раз больше доли отсева 0-4".
 *
 * Реализуется как линейные ограничения (деление раскрывается через
 * умножение на константу — солверу не нужно решать нелинейную задачу):
 * <pre>
 *   w[A] - minRatio * w[B] >= 0
 *   maxRatio * w[B] - w[A] >= 0
 * </pre>
 * Если {@code minRatio == maxRatio} — задаётся точное (жёсткое) соотношение.
 */
public final class RatioConstraint {

    final GrainTable componentA;
    final GrainTable componentB;

    final double minRatio;
    final double maxRatio;

    private RatioConstraint(GrainTable componentA, GrainTable componentB, double minRatio, double maxRatio) {
        if (minRatio > maxRatio) {
            throw new IllegalArgumentException("minRatio не может быть больше maxRatio");
        }
        this.componentA = componentA;
        this.componentB = componentB;
        this.minRatio = minRatio;
        this.maxRatio = maxRatio;
    }

    /** Точное (жёсткое) соотношение: w[componentA] = ratio * w[componentB]. */
    public static RatioConstraint exact(GrainTable componentA, GrainTable componentB, double ratio) {
        return new RatioConstraint(componentA, componentB, ratio, ratio);
    }

    /** Диапазон: minRatio <= w[componentA] / w[componentB] <= maxRatio. */
    public static RatioConstraint range(GrainTable componentA, GrainTable componentB, double minRatio, double maxRatio) {
        return new RatioConstraint(componentA, componentB, minRatio, maxRatio);
    }

    /** Только нижняя граница: w[componentA] / w[componentB] >= minRatio. */
    public static RatioConstraint atLeast(GrainTable componentA, GrainTable componentB, double minRatio) {
        return new RatioConstraint(componentA, componentB, minRatio, Double.POSITIVE_INFINITY);
    }

    /** Только верхняя граница: w[componentA] / w[componentB] <= maxRatio. */
    public static RatioConstraint atMost(GrainTable componentA, GrainTable componentB, double maxRatio) {
        return new RatioConstraint(componentA, componentB, 0.0, maxRatio);
    }

    public GrainTable getComponentA() {
        return componentA;
    }

    public GrainTable getComponentB() {
        return componentB;
    }

    public double getMinRatio() {
        return minRatio;
    }

    public double getMaxRatio() {
        return maxRatio;
    }

    @Override
    public String toString() {
        if (minRatio == maxRatio) {
            return componentA.getDescription() + " / " + componentB.getDescription() + " = " + minRatio;
        }
        return minRatio + " <= " + componentA.getDescription() + " / " + componentB.getDescription() + " <= " + maxRatio;
    }
}
