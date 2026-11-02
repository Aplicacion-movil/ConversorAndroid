package com.aplicacionmovil.conversor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import java.util.List;

public class ConversionEngineTest {

    private final ConversionEngine engine =
            new ConversionEngine();

    @Test
    public void getUnitsFor_returnsOnlyUnitsOfCategory() {
        for (ConversionCategory category
                : ConversionCategory.values()) {

            List<ConversionUnit> units =
                    engine.getUnitsFor(category);

            assertFalse(units.isEmpty());

            for (ConversionUnit unit : units) {
                assertEquals(
                        category,
                        unit.getCategory()
                );
            }
        }
    }

    @Test
    public void convert_kilometersToMeters_returnsExpectedValue() {
        double result = engine.convert(
                1.0,
                ConversionUnit.KILOMETER,
                ConversionUnit.METER
        );

        assertEquals(1000.0, result, 0.000001);
    }

    @Test
    public void convert_celsiusToFahrenheit_returnsExpectedValue() {
        double result = engine.convert(
                0.0,
                ConversionUnit.CELSIUS,
                ConversionUnit.FAHRENHEIT
        );

        assertEquals(32.0, result, 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convert_unitsFromDifferentCategories_throwsException() {
        engine.convert(
                1.0,
                ConversionUnit.METER,
                ConversionUnit.KILOGRAM
        );
    }
}