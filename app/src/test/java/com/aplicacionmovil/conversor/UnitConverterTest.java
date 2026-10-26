package com.aplicacionmovil.conversor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class UnitConverterTest {

    private static final double DELTA = 0.000001;

    @Test
    public void convertsKilometersToMiles() {
        double result = UnitConverter.convert(
                UnitConverter.LENGTH,
                5.0,
                "Kilómetros",
                "Millas"
        );

        assertEquals(3.106856, result, DELTA);
    }

    @Test
    public void convertsPoundsToKilograms() {
        double result = UnitConverter.convert(
                UnitConverter.MASS,
                10.0,
                "Libras",
                "Kilogramos"
        );

        assertEquals(4.5359237, result, DELTA);
    }

    @Test
    public void convertsCelsiusToFahrenheit() {
        double result = UnitConverter.convert(
                UnitConverter.TEMPERATURE,
                20.0,
                "Celsius",
                "Fahrenheit"
        );

        assertEquals(68.0, result, DELTA);
    }

    @Test
    public void convertsGallonsToLiters() {
        double result = UnitConverter.convert(
                UnitConverter.VOLUME,
                2.0,
                "Galones (EE. UU.)",
                "Litros"
        );

        assertEquals(7.570823568, result, DELTA);
    }

    @Test
    public void rejectsUnknownCategories() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UnitConverter.convert("Tiempo", 1.0, "Horas", "Minutos")
        );
    }

    @Test
    public void rejectsNonFiniteValues() {
        assertThrows(
                IllegalArgumentException.class,
                () -> UnitConverter.convert(
                        UnitConverter.LENGTH,
                        Double.POSITIVE_INFINITY,
                        "Metros",
                        "Kilómetros"
                )
        );
    }
}
