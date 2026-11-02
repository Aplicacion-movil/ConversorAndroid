package com.aplicacionmovil.conversor.domain;

import androidx.annotation.StringRes;

import com.aplicacionmovil.conversor.R;

public enum ConversionCategory {
    LENGTH("length", R.string.category_length),
    TEMPERATURE(
            "temperature",
            R.string.category_temperature
    ),
    MASS("mass", R.string.category_mass),
    VOLUME("volume", R.string.category_volume);

    private final String key;

    @StringRes
    private final int labelResId;

    ConversionCategory(
            String key,
            @StringRes int labelResId
    ) {
        this.key = key;
        this.labelResId = labelResId;
    }

    public String getKey() {
        return key;
    }

    @StringRes
    public int getLabelResId() {
        return labelResId;
    }

    public static ConversionCategory fromKey(String key) {
        if (key == null) {
            return null;
        }

        for (ConversionCategory category : values()) {
            if (category.key.equals(key)) {
                return category;
            }
        }

        return null;
    }
}