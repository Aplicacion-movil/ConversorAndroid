package com.aplicacionmovil.conversor.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ConversionEngine {

    public List<ConversionUnit> getUnitsFor(
            ConversionCategory category
    ) {
        if (category == null) {
            return Collections.emptyList();
        }

        List<ConversionUnit> units = new ArrayList<>();

        for (ConversionUnit unit : ConversionUnit.values()) {
            if (unit.getCategory() == category) {
                units.add(unit);
            }
        }

        return Collections.unmodifiableList(units);
    }

    public double convert(
            double value,
            ConversionUnit source,
            ConversionUnit target
    ) {
        if (!isFinite(value)) {
            throw new IllegalArgumentException(
                    "El valor debe ser finito"
            );
        }

        if (source == null || target == null) {
            throw new IllegalArgumentException(
                    "Las unidades son obligatorias"
            );
        }

        if (source.getCategory() != target.getCategory()) {
            throw new IllegalArgumentException(
                    "Las unidades pertenecen a categorías diferentes"
            );
        }

        double baseValue = source.toBase(value);
        double result = target.fromBase(baseValue);

        if (!isFinite(result)) {
            throw new IllegalArgumentException(
                    "El resultado está fuera del rango admitido"
            );
        }

        return result;
    }

    private boolean isFinite(double value) {
        return !Double.isNaN(value)
                && !Double.isInfinite(value);
    }
}