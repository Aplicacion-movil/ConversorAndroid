package com.aplicacionmovil.conversor.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Motor común que filtra unidades y convierte valores pasando
 * siempre por la unidad de referencia de la categoría.
 *
 * <p>No contiene fórmulas por parejas de unidades.</p>
 */

public final class ConversionEngine {
    public List<ConversionUnit> getUnitsFor(ConversionCategory category) {
        if (category == null)
            throw new IllegalArgumentException("La categoría es obligatoria");

        List<ConversionUnit> units = new ArrayList<>();

        for (ConversionUnit unit : ConversionUnit.values()) {
            if (unit.getCategory() == category)
                units.add(unit);
        }

        return Collections.unmodifiableList(units);
    }

    /**
     * Convierte un valor entre dos unidades de la misma categoría.
     *
     * @throws IllegalArgumentException si el valor no es finito,
     *         falta alguna unidad, las categorías son incompatibles
     *         o el resultado no es finito
     */

    public double convert(double value, ConversionUnit source, ConversionUnit target) {
        if (!isFinite(value))
            throw new IllegalArgumentException("El valor debe ser finito");

        if (source == null || target == null)
            throw new IllegalArgumentException("Las unidades son obligatorias");

        if (source.getCategory() != target.getCategory())
            throw new IllegalArgumentException("Las unidades pertenecen a categorías diferentes");

        double baseValue = source.toBase(value);
        double result = target.fromBase(baseValue);

        if (!isFinite(result))
            throw new IllegalArgumentException("El resultado está fuera del rango admitido");

        return result;
    }

    public String convert(int speed, double alcohol, boolean novel) {
        if (!isFinite(speed) || !isFinite(alcohol))
            throw new IllegalArgumentException("El valor debe ser finito");

        double result = 0.0;

        if (speed > 10 && speed <= 20) {
            result += 100.0;
        }
        else if (speed > 20 && speed <= 30) {
            result += 300.0;
        }
        else if (speed > 30 && speed <= 40) {
            result += 400.0;
        }
        else if (speed > 40 && speed <= 50) {
            result += 500.0;
        }
        else if (speed > 50 && speed <= 80) {
            result += 600.0;
        }
        else if (speed > 80) {
            return "Delito penal";
        }
        else if (speed < 0)
            throw new IllegalArgumentException("El resultado está fuera del rango admitido");

        if (novel) {
            if (alcohol > 0.15 && alcohol <= 0.3) {
                result += 500.0;
            }
            else if (alcohol > 0.3 && alcohol <= 0.6) {
                result += 1000.0;
            }
            else if (alcohol > 0.60) {
                return "Delito penal";
            }
            else if (alcohol < 0.0)
                throw new IllegalArgumentException("El resultado está fuera del rango admitido");
        }
        else {
            if (alcohol > 0.25 && alcohol <= 0.5) {
                result += 500.0;
            }
            else if (alcohol > 0.5 && alcohol <= 0.6) {
                result += 1000.0;
            }
            else if (alcohol > 0.60) {
                return "Delito penal.";
            }
            else if (alcohol < 0.0)
                throw new IllegalArgumentException("El resultado está fuera del rango admitido");
        }

        return String.valueOf(result) + '€';
    }

    private boolean isFinite(double value) {
        return !Double.isNaN(value) && !Double.isInfinite(value);
    }
}