package com.aplicacionmovil.conversor.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SpeedConversionTest {

    private static final double TOLERANCE = 0.000001;

    private final ConversionEngine engine =
            new ConversionEngine();

    @Test
    public void getUnitsForSpeed_containsRequiredUnits() {
        List<ConversionUnit> units =
                engine.getUnitsFor(
                        ConversionCategory.SPEED
                );

        assertTrue(
                units.containsAll(
                        Arrays.asList(
                                ConversionUnit.KILOMETER_PER_HOUR,
                                ConversionUnit.METER_PER_SECOND,
                                ConversionUnit.MILE_PER_HOUR
                        )
                )
        );

        for (ConversionUnit unit : units) {
            assertEquals(
                    ConversionCategory.SPEED,
                    unit.getCategory()
            );
        }
    }

    @Test
    public void convert_kilometersPerHourToMetersPerSecond_returnsExpectedValue() {
        double result = engine.convert(
                36.0,
                ConversionUnit.KILOMETER_PER_HOUR,
                ConversionUnit.METER_PER_SECOND
        );

        assertEquals(
                10.0,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_metersPerSecondToKilometersPerHour_returnsExpectedValue() {
        double result = engine.convert(
                10.0,
                ConversionUnit.METER_PER_SECOND,
                ConversionUnit.KILOMETER_PER_HOUR
        );

        assertEquals(
                36.0,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_milesPerHourToMetersPerSecond_returnsExpectedValue() {
        double result = engine.convert(
                1.0,
                ConversionUnit.MILE_PER_HOUR,
                ConversionUnit.METER_PER_SECOND
        );

        assertEquals(
                0.44704,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_decimalValue_returnsExpectedValue() {
        double result = engine.convert(
                2.5,
                ConversionUnit.METER_PER_SECOND,
                ConversionUnit.KILOMETER_PER_HOUR
        );

        assertEquals(
                9.0,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_sameUnit_preservesValue() {
        double result = engine.convert(
                42.75,
                ConversionUnit.MILE_PER_HOUR,
                ConversionUnit.MILE_PER_HOUR
        );

        assertEquals(
                42.75,
                result,
                TOLERANCE
        );
    }

    @Test
    public void convert_roundTrip_preservesOriginalValue() {
        double originalValue = 65.5;

        double metersPerSecond = engine.convert(
                originalValue,
                ConversionUnit.MILE_PER_HOUR,
                ConversionUnit.METER_PER_SECOND
        );

        double result = engine.convert(
                metersPerSecond,
                ConversionUnit.METER_PER_SECOND,
                ConversionUnit.MILE_PER_HOUR
        );

        assertEquals(
                originalValue,
                result,
                TOLERANCE
        );
    }
}
