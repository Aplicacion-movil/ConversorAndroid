package com.aplicacionmovil.conversor.domain;

import androidx.annotation.StringRes;

import com.aplicacionmovil.conversor.R;

public enum ConversionUnit {
    METER(
            ConversionCategory.LENGTH,
            R.string.unit_meter,
            "m",
            1.0
    ),
    KILOMETER(
            ConversionCategory.LENGTH,
            R.string.unit_kilometer,
            "km",
            1000.0
    ),
    CENTIMETER(
            ConversionCategory.LENGTH,
            R.string.unit_centimeter,
            "cm",
            0.01
    ),
    MILLIMETER(
            ConversionCategory.LENGTH,
            R.string.unit_millimeter,
            "mm",
            0.001
    ),
    MILE(
            ConversionCategory.LENGTH,
            R.string.unit_mile,
            "mi",
            1609.344
    ),
    FOOT(
            ConversionCategory.LENGTH,
            R.string.unit_foot,
            "ft",
            0.3048
    ),
    INCH(
            ConversionCategory.LENGTH,
            R.string.unit_inch,
            "in",
            0.0254
    ),

    CELSIUS(
            ConversionCategory.TEMPERATURE,
            R.string.unit_celsius,
            "°C",
            value -> value,
            value -> value
    ),
    FAHRENHEIT(
            ConversionCategory.TEMPERATURE,
            R.string.unit_fahrenheit,
            "°F",
            value -> (value - 32.0) * 5.0 / 9.0,
            value -> value * 9.0 / 5.0 + 32.0
    ),
    KELVIN(
            ConversionCategory.TEMPERATURE,
            R.string.unit_kelvin,
            "K",
            value -> value - 273.15,
            value -> value + 273.15
    ),

    KILOGRAM(
            ConversionCategory.MASS,
            R.string.unit_kilogram,
            "kg",
            value -> value,
            value -> value
    ),
    GRAM(
            ConversionCategory.MASS,
            R.string.unit_gram,
            "g",
            value -> value / 1000.0,
            value -> value * 1000.0
    ),
    POUND(
            ConversionCategory.MASS,
            R.string.unit_pound,
            "lb",
            value -> value * 0.45359237,
            value -> value / 0.45359237
    ),
    OUNCE(
            ConversionCategory.MASS,
            R.string.unit_ounce,
            "oz",
            value -> value * 0.028349523125,
            value -> value / 0.028349523125
    ),

    LITER(
            ConversionCategory.VOLUME,
            R.string.unit_liter,
            "L",
            value -> value,
            value -> value
    ),
    MILLILITER(
            ConversionCategory.VOLUME,
            R.string.unit_milliliter,
            "mL",
            value -> value / 1000.0,
            value -> value * 1000.0
    ),
    CUBIC_METER(
            ConversionCategory.VOLUME,
            R.string.unit_cubic_meter,
            "m³",
            value -> value * 1000.0,
            value -> value / 1000.0
    ),
    US_GALLON(
            ConversionCategory.VOLUME,
            R.string.unit_us_gallon,
            "gal US",
            value -> value * 3.785411784,
            value -> value / 3.785411784
    );

    private final ConversionCategory category;

    @StringRes
    private final int labelResId;

    private final String symbol;
    private final ConversionOperation toBaseOperation;
    private final ConversionOperation fromBaseOperation;

    ConversionUnit(
            ConversionCategory category,
            @StringRes int labelResId,
            String symbol,
            double factorToBase
    ) {
        this(
                category,
                labelResId,
                symbol,
                value -> value * factorToBase,
                value -> value / factorToBase
        );
    }

    ConversionUnit(
            ConversionCategory category,
            @StringRes int labelResId,
            String symbol,
            ConversionOperation toBaseOperation,
            ConversionOperation fromBaseOperation
    ) {
        this.category = category;
        this.labelResId = labelResId;
        this.symbol = symbol;
        this.toBaseOperation = toBaseOperation;
        this.fromBaseOperation = fromBaseOperation;
    }

    public ConversionCategory getCategory() {
        return category;
    }

    @StringRes
    public int getLabelResId() {
        return labelResId;
    }

    public String getSymbol() {
        return symbol;
    }

    double toBase(double value) {
        return toBaseOperation.apply(value);
    }

    double fromBase(double value) {
        return fromBaseOperation.apply(value);
    }

    private interface ConversionOperation {
        double apply(double value);
    }
}