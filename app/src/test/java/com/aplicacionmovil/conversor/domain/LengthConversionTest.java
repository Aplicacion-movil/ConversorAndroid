package com.aplicacionmovil.conversor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LengthConversionTest {

    private static final double TOLERANCE = 0.000001;

    private final ConversionEngine engine =
            new ConversionEngine();

    @Test
    public void getUnitsForLength_containsRequiredUnits() {
        List<ConversionUnit> units =
                engine.getUnitsFor(
                        ConversionCategory.LENGTH
                );

        assertTrue(
                units.containsAll(
                        Arrays.asList(
                                ConversionUnit.METER,
                                ConversionUnit.KILOMETER,
                                ConversionUnit.CENTIMETER,
                                ConversionUnit.MILE,
                                ConversionUnit.FOOT,
                                ConversionUnit.INCH
                        )
                )
        );

        for (ConversionUnit unit : units) {
            assertEquals(
                    ConversionCategory.LENGTH,
                    unit.getCategory()
            );
        }
    }

    @Test
    public void convert_decimalKilometersToMeters_returnsExpectedValue() {
        double result = engine.convert(
                2.5,
                ConversionUnit.KILOMETER,
                ConversionUnit.METER
        );

        assertEquals(
                2500.0,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_mileToFeet_returnsExpectedValue() {
        double result = engine.convert(
                1.0,
                ConversionUnit.MILE,
                ConversionUnit.FOOT
        );

        assertEquals(
                5280.0,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_sameUnit_preservesValue() {
        double result = engine.convert(
                42.75,
                ConversionUnit.INCH,
                ConversionUnit.INCH
        );

        assertEquals(
                42.75,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_roundTrip_preservesOriginalValue() {
        double originalValue = 3.7;

        double meters = engine.convert(
                originalValue,
                ConversionUnit.MILE,
                ConversionUnit.METER
        );

        double result = engine.convert(
                meters,
                ConversionUnit.METER,
                ConversionUnit.MILE
        );

        assertEquals(
                originalValue,
                result,
                TOLERANCE
        );
    }
}