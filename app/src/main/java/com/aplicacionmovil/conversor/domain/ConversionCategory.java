package com.aplicacionmovil.conversor.domain;

public enum ConversionCategory {
    LENGTH("length"),
    TEMPERATURE("temperature"),
    MASS("mass"),
    VOLUME("volume");

    private final String key;

    ConversionCategory(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
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