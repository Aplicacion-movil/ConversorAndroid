package com.aplicacionmovil.conversor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class VolumeConversionTest {

    private static final double TOLERANCE = 0.000001;

    private final ConversionEngine engine =
            new ConversionEngine();

    @Test
    public void getUnitsForVolume_containsRequiredUnits() {
        List<ConversionUnit> units =
                engine.getUnitsFor(
                        ConversionCategory.VOLUME
                );

        assertTrue(
                units.containsAll(
                        Arrays.asList(
                                ConversionUnit.LITER,
                                ConversionUnit.MILLILITER,
                                ConversionUnit.US_GALLON
                        )
                )
        );

        for (ConversionUnit unit : units) {
            assertEquals(
                    ConversionCategory.VOLUME,
                    unit.getCategory()
            );
        }
    }

    @Test
    public void convert_decimalLitersToMilliliters_returnsExpectedValue() {
        double result = engine.convert(
                2.5,
                ConversionUnit.LITER,
                ConversionUnit.MILLILITER
        );

        assertEquals(
                2500.0,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_gallonToLiters_returnsExpectedValue() {
        double result = engine.convert(
                1.0,
                ConversionUnit.US_GALLON,
                ConversionUnit.LITER
        );

        assertEquals(
                3.785411784,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_sameUnit_preservesValue() {
        double result = engine.convert(
                42.75,
                ConversionUnit.MILLILITER,
                ConversionUnit.MILLILITER
        );

        assertEquals(
                42.75,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_roundTrip_preservesOriginalValue() {
        double originalValue = 2.75;

        double milliliters = engine.convert(
                originalValue,
                ConversionUnit.US_GALLON,
                ConversionUnit.MILLILITER
        );

        double result = engine.convert(
                milliliters,
                ConversionUnit.MILLILITER,
                ConversionUnit.US_GALLON
        );

        assertEquals(
                originalValue,
                result,
                TOLERANCE
        );
    }
}