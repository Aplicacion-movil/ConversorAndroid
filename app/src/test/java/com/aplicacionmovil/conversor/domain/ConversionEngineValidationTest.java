package com.aplicacionmovil.conversor.domain;

import static org.junit.Assert.assertNull;

import org.junit.Test;

public class ConversionEngineValidationTest {

    private final ConversionEngine engine =
            new ConversionEngine();

    @Test
    public void fromKey_unknownCategory_returnsNull() {
        ConversionCategory category =
                ConversionCategory.fromKey("unknown");

        assertNull(category);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUnitsFor_nullCategory_throwsException() {
        engine.getUnitsFor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void convert_nanValue_throwsException() {
        engine.convert(
                Double.NaN,
                ConversionUnit.METER,
                ConversionUnit.KILOMETER
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void convert_positiveInfinity_throwsException() {
        engine.convert(
                Double.POSITIVE_INFINITY,
                ConversionUnit.METER,
                ConversionUnit.KILOMETER
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void convert_negativeInfinity_throwsException() {
        engine.convert(
                Double.NEGATIVE_INFINITY,
                ConversionUnit.METER,
                ConversionUnit.KILOMETER
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void convert_nullSourceUnit_throwsException() {
        engine.convert(
                1.0,
                null,
                ConversionUnit.METER
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void convert_nullTargetUnit_throwsException() {
        engine.convert(
                1.0,
                ConversionUnit.METER,
                null
        );
    }
}