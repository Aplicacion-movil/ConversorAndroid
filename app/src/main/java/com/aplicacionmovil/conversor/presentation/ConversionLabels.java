package com.aplicacionmovil.conversor.presentation;

import androidx.annotation.StringRes;

import com.aplicacionmovil.conversor.R;
import com.aplicacionmovil.conversor.domain.ConversionCategory;
import com.aplicacionmovil.conversor.domain.ConversionUnit;

import java.util.EnumMap;
import java.util.Map;

public final class ConversionLabels {

    private static final Map<ConversionCategory, Integer>
            CATEGORY_LABELS =
            new EnumMap<>(ConversionCategory.class);

    private static final Map<ConversionUnit, Integer>
            UNIT_LABELS =
            new EnumMap<>(ConversionUnit.class);

    static {
        CATEGORY_LABELS.put(
                ConversionCategory.LENGTH,
                R.string.category_length
        );
        CATEGORY_LABELS.put(
                ConversionCategory.TEMPERATURE,
                R.string.category_temperature
        );
        CATEGORY_LABELS.put(
                ConversionCategory.MASS,
                R.string.category_mass
        );
        CATEGORY_LABELS.put(
                ConversionCategory.VOLUME,
                R.string.category_volume
        );
        CATEGORY_LABELS.put(
                ConversionCategory.SPEED,
                R.string.category_speed
        );
        CATEGORY_LABELS.put(
                ConversionCategory.PRESSURE,
                R.string.category_pressure
        );

        UNIT_LABELS.put(
                ConversionUnit.METER,
                R.string.unit_meter
        );
        UNIT_LABELS.put(
                ConversionUnit.KILOMETER,
                R.string.unit_kilometer
        );
        UNIT_LABELS.put(
                ConversionUnit.CENTIMETER,
                R.string.unit_centimeter
        );
        UNIT_LABELS.put(
                ConversionUnit.MILLIMETER,
                R.string.unit_millimeter
        );
        UNIT_LABELS.put(
                ConversionUnit.MILE,
                R.string.unit_mile
        );
        UNIT_LABELS.put(
                ConversionUnit.FOOT,
                R.string.unit_foot
        );
        UNIT_LABELS.put(
                ConversionUnit.INCH,
                R.string.unit_inch
        );

        UNIT_LABELS.put(
                ConversionUnit.CELSIUS,
                R.string.unit_celsius
        );
        UNIT_LABELS.put(
                ConversionUnit.FAHRENHEIT,
                R.string.unit_fahrenheit
        );
        UNIT_LABELS.put(
                ConversionUnit.KELVIN,
                R.string.unit_kelvin
        );
        UNIT_LABELS.put(
                ConversionUnit.RANKIRE,
                R.string.unit_rankire
        );

        UNIT_LABELS.put(
                ConversionUnit.STONE,
                R.string.unit_stone
        );
        UNIT_LABELS.put(
                ConversionUnit.KILOGRAM,
                R.string.unit_kilogram
        );
        UNIT_LABELS.put(
                ConversionUnit.HECTOGRAM,
                R.string.unit_hectogram
        );
        UNIT_LABELS.put(
                ConversionUnit.DECAGRAM,
                R.string.unit_decagram
        );
        UNIT_LABELS.put(
                ConversionUnit.GRAM,
                R.string.unit_gram
        );
        UNIT_LABELS.put(
                ConversionUnit.DECIGRAM,
                R.string.unit_decigram
        );
        UNIT_LABELS.put(
                ConversionUnit.CENTIGRAM,
                R.string.unit_centigram
        );
        UNIT_LABELS.put(
                ConversionUnit.MILIGRAM,
                R.string.unit_miligram
        );
        UNIT_LABELS.put(
                ConversionUnit.MICROGRAM,
                R.string.unit_microgram
        );
        UNIT_LABELS.put(
                ConversionUnit.POUND,
                R.string.unit_pound
        );
        UNIT_LABELS.put(
                ConversionUnit.OUNCE,
                R.string.unit_ounce
        );

        UNIT_LABELS.put(
                ConversionUnit.LITER,
                R.string.unit_liter
        );
        UNIT_LABELS.put(
                ConversionUnit.MILLILITER,
                R.string.unit_milliliter
        );
        UNIT_LABELS.put(
                ConversionUnit.CUBIC_METER,
                R.string.unit_cubic_meter
        );
        UNIT_LABELS.put(
                ConversionUnit.US_GALLON,
                R.string.unit_us_gallon
        );

        UNIT_LABELS.put(
                ConversionUnit.METER_PER_SECOND,
                R.string.unit_meter_per_second
        );
        UNIT_LABELS.put(
                ConversionUnit.KILOMETER_PER_HOUR,
                R.string.unit_kilometer_per_hour
        );
        UNIT_LABELS.put(
                ConversionUnit.MILE_PER_HOUR,
                R.string.unit_mile_per_hour
        );

        UNIT_LABELS.put(
                ConversionUnit.PASCAL,
                R.string.unit_pascal
        );
        UNIT_LABELS.put(
                ConversionUnit.BAR,
                R.string.unit_bar
        );
        UNIT_LABELS.put(
                ConversionUnit.ATMOSPHERE,
                R.string.unit_atmosphere
        );
    }

    private ConversionLabels() {
    }

    @StringRes
    public static int getCategoryLabel(
            ConversionCategory category
    ) {
        Integer labelResId =
                CATEGORY_LABELS.get(category);

        if (labelResId == null) {
            throw new IllegalArgumentException(
                    "La categoría no tiene una etiqueta registrada"
            );
        }

        return labelResId;
    }

    @StringRes
    public static int getUnitLabel(
            ConversionUnit unit
    ) {
        Integer labelResId = UNIT_LABELS.get(unit);

        if (labelResId == null) {
            throw new IllegalArgumentException(
                    "La unidad no tiene una etiqueta registrada"
            );
        }

        return labelResId;
    }
}
