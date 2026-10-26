package com.aplicacionmovil.conversor;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Motor de conversión independiente de Android.
 *
 * <p>Las categorías lineales convierten primero a una unidad base. La temperatura se
 * trata aparte porque sus escalas también contienen un desplazamiento.</p>
 */
public final class UnitConverter {

    public static final String LENGTH = "Longitud";
    public static final String MASS = "Masa";
    public static final String TEMPERATURE = "Temperatura";
    public static final String VOLUME = "Volumen";

    private static final Map<String, Map<String, Double>> CATEGORIES = createCategories();

    private UnitConverter() {
        // Clase de utilidad.
    }

    public static Set<String> getCategories() {
        return Collections.unmodifiableSet(CATEGORIES.keySet());
    }

    public static Set<String> getUnits(String category) {
        Map<String, Double> units = CATEGORIES.get(category);
        if (units == null) {
            throw new IllegalArgumentException("Categoría desconocida: " + category);
        }
        return Collections.unmodifiableSet(units.keySet());
    }

    public static double convert(String category, double value, String fromUnit, String toUnit) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("El valor debe ser un número finito");
        }

        Map<String, Double> units = CATEGORIES.get(category);
        if (units == null || !units.containsKey(fromUnit) || !units.containsKey(toUnit)) {
            throw new IllegalArgumentException("Categoría o unidad desconocida");
        }

        if (TEMPERATURE.equals(category)) {
            return convertTemperature(value, fromUnit, toUnit);
        }

        double baseValue = value * units.get(fromUnit);
        return baseValue / units.get(toUnit);
    }

    private static double convertTemperature(double value, String fromUnit, String toUnit) {
        double celsius;
        switch (fromUnit) {
            case "Celsius":
                celsius = value;
                break;
            case "Fahrenheit":
                celsius = (value - 32.0) * 5.0 / 9.0;
                break;
            case "Kelvin":
                celsius = value - 273.15;
                break;
            default:
                throw new IllegalArgumentException("Unidad de temperatura desconocida");
        }

        switch (toUnit) {
            case "Celsius":
                return celsius;
            case "Fahrenheit":
                return celsius * 9.0 / 5.0 + 32.0;
            case "Kelvin":
                return celsius + 273.15;
            default:
                throw new IllegalArgumentException("Unidad de temperatura desconocida");
        }
    }

    private static Map<String, Map<String, Double>> createCategories() {
        Map<String, Map<String, Double>> categories = new LinkedHashMap<>();

        Map<String, Double> length = new LinkedHashMap<>();
        length.put("Metros", 1.0);
        length.put("Kilómetros", 1_000.0);
        length.put("Centímetros", 0.01);
        length.put("Millas", 1_609.344);
        length.put("Pies", 0.3048);
        categories.put(LENGTH, length);

        Map<String, Double> mass = new LinkedHashMap<>();
        mass.put("Kilogramos", 1.0);
        mass.put("Gramos", 0.001);
        mass.put("Libras", 0.45359237);
        mass.put("Onzas", 0.028349523125);
        categories.put(MASS, mass);

        Map<String, Double> temperature = new LinkedHashMap<>();
        temperature.put("Celsius", 1.0);
        temperature.put("Fahrenheit", 1.0);
        temperature.put("Kelvin", 1.0);
        categories.put(TEMPERATURE, temperature);

        Map<String, Double> volume = new LinkedHashMap<>();
        volume.put("Litros", 1.0);
        volume.put("Mililitros", 0.001);
        volume.put("Galones (EE. UU.)", 3.785411784);
        volume.put("Tazas", 0.2365882365);
        categories.put(VOLUME, volume);

        return Collections.unmodifiableMap(categories);
    }
}
