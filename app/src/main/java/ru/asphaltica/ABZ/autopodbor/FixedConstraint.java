package ru.asphaltica.ABZ.autopodbor;

import ru.asphaltica.ABZ.enumerated.GrainTable;

public final class FixedConstraint {

    private final GrainTable component;
    private final double value;

    private FixedConstraint(GrainTable component, double value) {
        this.component = component;
        this.value = value;
    }

    public static FixedConstraint exact(
            GrainTable component,
            double value
    ) {
        return new FixedConstraint(component, value);
    }

    public GrainTable getComponent() {
        return component;
    }

    public double getValue() {
        return value;
    }
}
