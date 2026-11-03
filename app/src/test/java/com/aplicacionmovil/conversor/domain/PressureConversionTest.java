package com.aplicacionmovil.conversor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PressureConversionTest {

    private static final double TOLERANCE = 0.000001;

    private final ConversionEngine engine =
            new ConversionEngine();

    @Test
    public void getUnitsForPressure_containsRequiredUnits() {
        List<ConversionUnit> units =
                engine.getUnitsFor(
                        ConversionCategory.PRESSURE
                );

        assertTrue(
                units.containsAll(
                        Arrays.asList(
                                ConversionUnit.PASCAL,
                                ConversionUnit.BAR,
                                ConversionUnit.ATMOSPHERE
                        )
                )
        );

        for (ConversionUnit unit : units) {
            assertEquals(
                    ConversionCategory.PRESSURE,
                    unit.getCategory()
            );
        }
    }

    @Test
    public void convert_barToPascals_returnsExpectedValue() {
        double result = engine.convert(
                1.0,
                ConversionUnit.BAR,
                ConversionUnit.PASCAL
        );

        assertEquals(
                100000.0,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_decimalPascalsToBar_returnsExpectedValue() {
        double result = engine.convert(
                250000.0,
                ConversionUnit.PASCAL,
                ConversionUnit.BAR
        );

        assertEquals(
                2.5,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_atmosphereToPascals_returnsExpectedValue() {
        double result = engine.convert(
                1.0,
                ConversionUnit.ATMOSPHERE,
                ConversionUnit.PASCAL
        );

        assertEquals(
                101325.0,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_pascalsToAtmosphere_returnsExpectedValue() {
        double result = engine.convert(
                50662.5,
                ConversionUnit.PASCAL,
                ConversionUnit.ATMOSPHERE
        );

        assertEquals(
                0.5,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_barToAtmosphere_returnsExpectedValue() {
        double result = engine.convert(
                1.01325,
                ConversionUnit.BAR,
                ConversionUnit.ATMOSPHERE
        );

        assertEquals(
                1.0,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_sameUnit_preservesValue() {
        double result = engine.convert(
                42.75,
                ConversionUnit.PASCAL,
                ConversionUnit.PASCAL
        );

        assertEquals(
                42.75,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_roundTrip_preservesOriginalValue() {
        double originalValue = 1.234;

        double atmospheres = engine.convert(
                originalValue,
                ConversionUnit.BAR,
                ConversionUnit.ATMOSPHERE
        );

        double result = engine.convert(
                atmospheres,
                ConversionUnit.ATMOSPHERE,
                ConversionUnit.BAR
        );

        assertEquals(
                originalValue,
                result,
                TOLERANCE
        );
    }
}
