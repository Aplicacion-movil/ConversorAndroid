package com.aplicacionmovil.conversor.domain;

/**
 * Catálogo de unidades admitidas por el motor.
 *
 * <p>Unidades de referencia:</p>
 * <ul>
 *     <li>Longitud: metro.</li>
 *     <li>Temperatura: grado Celsius.</li>
 *     <li>Masa y peso: kilogramo.</li>
 *     <li>Volumen: litro.</li>
 *     <li>Velocidad: metro por segundo.</li>
 * </ul>
 *
 * <p>En las categorías lineales, {@code factorToBase} representa
 * cuántas unidades de referencia contiene una unidad registrada.
 * La conversión multiplica por el factor de origen y divide por el
 * factor de destino.</p>
 *
 * <p>Las escalas de temperatura se modelan mediante una escala y
 * un desplazamiento respecto a Celsius.</p>
 */

public enum ConversionUnit {
    METER(
            ConversionCategory.LENGTH,
            "m",
            1.0
    ),
    KILOMETER(
            ConversionCategory.LENGTH,
            "km",
            1000.0
    ),
    CENTIMETER(
            ConversionCategory.LENGTH,
            "cm",
            0.01
    ),
    MILLIMETER(
            ConversionCategory.LENGTH,
            "mm",
            0.001
    ),
    MILE(
            ConversionCategory.LENGTH,
            "mi",
            1609.344
    ),
    FOOT(
            ConversionCategory.LENGTH,
            "ft",
            0.3048
    ),
    INCH(
            ConversionCategory.LENGTH,
            "in",
            0.0254
    ),

    CELSIUS(
            ConversionCategory.TEMPERATURE,
            "°C",
            1.0,
            0.0
    ),
    FAHRENHEIT(
            ConversionCategory.TEMPERATURE,
            "°F",
            5.0 / 9.0,
            32.0
    ),
    KELVIN(
            ConversionCategory.TEMPERATURE,
            "K",
            1.0,
            273.15
    ),
    RANKIRE(
            ConversionCategory.TEMPERATURE,
            "R",
            0.0,
            491.67
    ),

    STONE(
            ConversionCategory.MASS,
            "st",
            6.35029
    ),
    KILOGRAM(
            ConversionCategory.MASS,
            "kg",
            1.0
    ),
    HECTOGRAM(
            ConversionCategory.MASS,
            "hg",
            0.1
    ),
    DECAGRAM(
            ConversionCategory.MASS,
            "dkg",
            0.01
    ),
    GRAM(
            ConversionCategory.MASS,
            "g",
            0.001
    ),
    DECIGRAM(
            ConversionCategory.MASS,
            "dg",
            0.0001
    ),
    CENTIGRAM(
            ConversionCategory.MASS,
            "cg",
            0.00001
    ),
    MILIGRAM(
            ConversionCategory.MASS,
            "mg",
            0.000001
    ),
    MICROGRAM(
            ConversionCategory.MASS,
            "mcg",
            0.0000001
    ),
    POUND(
            ConversionCategory.MASS,
            "lb",
            0.45359237
    ),
    OUNCE(
            ConversionCategory.MASS,
            "oz",
            0.028349523125
    ),

    WINE_BARREL(
            ConversionCategory.VOLUME,
            "wb",
            255.0
    ),
    LITER(
            ConversionCategory.VOLUME,
            "L",
            1.0
    ),
    WINESKIN(
            ConversionCategory.VOLUME,
            "ws",
            0.75
    ),
    BEER_MUG(
            ConversionCategory.VOLUME,
            "bm",
            0.5
    ),
    WINE_CUP(
            ConversionCategory.VOLUME,
            "wc",
            0.15
    ),
    SHOT(
            ConversionCategory.VOLUME,
            "s",
            0.02957
    ),
    MILLILITER(
            ConversionCategory.VOLUME,
            "mL",
            0.001
    ),
    CUBIC_METER(
            ConversionCategory.VOLUME,
            "m³",
            1000.0
    ),
    US_GALLON(
            ConversionCategory.VOLUME,
            "gal US",
            3.785411784
    ),

    METER_PER_SECOND(
            ConversionCategory.SPEED,
            "m/s",
            1.0
    ),
    KILOMETER_PER_HOUR(
            ConversionCategory.SPEED,
            "km/h",
            1.0 / 3.6
    ),
    MILE_PER_HOUR(
            ConversionCategory.SPEED,
            "mph",
            0.44704
    ),

    CENTURY(
            ConversionCategory.TIME,
            "c",
            3155695200.0
    ),
    DECADE(
            ConversionCategory.TIME,
            "de",
            315569520.0
    ),
    YEAR(
            ConversionCategory.TIME,
            "y",
            31536000.0
    ),
    MONTH(
            ConversionCategory.TIME,
            "mo",
            2592000.0
    ),
    WEEK(
            ConversionCategory.TIME,
            "w",
            604800.0
    ),
    DAY(
            ConversionCategory.TIME,
            "d",
            86400.0
    ),
    HOUR(
            ConversionCategory.TIME,
            "h",
            3600.0
    ),
    MINUTE(
            ConversionCategory.TIME,
            "m",
            60.0
    ),
    SECOND(
            ConversionCategory.TIME,
            "s",
            1.0
    ),
    MILISECOND(
            ConversionCategory.TIME,
            "ms",
            0.001
    ),
    MICROSECOND(
            ConversionCategory.TIME,
            "mcs",
            0.000001
    ),
    NANOSECOND(
            ConversionCategory.TIME,
            "ns",
            0.000000001
    ),

    PETABYTE(
            ConversionCategory.COMPUTING,
            "PB",
            9007199254740992.0
    ),
    TERABYTE(
            ConversionCategory.COMPUTING,
            "TB",
            8796093022208.0
    ),
    GIGABYTE(
            ConversionCategory.COMPUTING,
            "GB",
            8589934592.0
    ),
    MEGABYTE(
            ConversionCategory.COMPUTING,
            "MB",
            8388608.0
    ),
    KILOBYTE(
            ConversionCategory.COMPUTING,
            "KB",
            8192.0
    ),
    BYTE(
            ConversionCategory.COMPUTING,
            "B",
            8.0
    ),
    BIT(
            ConversionCategory.COMPUTING,
            "b",
            1.0
    ),

    SQUARE_MILE(
            ConversionCategory.AREA,
            "mi²",
            2589988.11
    ),
    SQUARE_KILOMETER(
            ConversionCategory.AREA,
            "km²",
            1000000.0
    ),
    SQUARE_HECTOMETER(
            ConversionCategory.AREA,
            "hm²",
            10000.0
    ),
    ACRE(
            ConversionCategory.AREA,
            "ac",
            4046.8564224
    ),
    SQUARE_DECAMETER(
            ConversionCategory.AREA,
            "dam²",
            100.0
    ),
    SQUARE_METER(
            ConversionCategory.AREA,
            "m²",
            1.0
    ),
    SQUARE_FOOT(
            ConversionCategory.AREA,
            "ft²",
            0.09290304
    ),
    SQUARE_YARD(
            ConversionCategory.AREA,
            "yd²",
            0.83612736
    ),
    SQUARE_DECIMETER(
            ConversionCategory.AREA,
            "bm²",
            0.01
    ),
    SQUARE_INCH(
            ConversionCategory.AREA,
            "in²",
            0.00064516
    ),
    SQUARE_CENTIMETER(
            ConversionCategory.AREA,
            "cm²",
            0.0001
    ),
    SQUARE_MILIMETER(
            ConversionCategory.AREA,
            "mm²",
            0.000001
    );

    private final ConversionCategory category;


    private final String symbol;
    private final ConversionOperation toBaseOperation;
    private final ConversionOperation fromBaseOperation;

    /**
     * Registra una unidad lineal mediante su factor respecto a la
     * unidad de referencia de la categoría.
     *
     * @param category categoría a la que pertenece la unidad
     * @param symbol símbolo utilizado para mostrar resultados
     * @param factorToBase unidades de referencia contenidas en una unidad
     */

    ConversionUnit(ConversionCategory category, String symbol, double factorToBase) {
        this(
                category,
                symbol,
                value -> value * factorToBase,
                value -> value / factorToBase
        );
    }

    /**
     * Registra una escala con transformación afín respecto a la
     * unidad de referencia.
     *
     * <p>La conversión hacia la referencia utiliza:
     * {@code (value - offset) * scaleToBase}.</p>
     *
     * @param category categoría de temperatura
     * @param symbol símbolo utilizado para mostrar resultados
     * @param scaleToBase escala aplicada al convertir a la referencia
     * @param offset desplazamiento de la escala respecto a la referencia
     */

    ConversionUnit(ConversionCategory category, String symbol, double scaleToBase, double offset) {
        this(
                category,
                symbol,
                value -> (value - offset) * scaleToBase,
                value -> value / scaleToBase + offset
        );
    }

    ConversionUnit(ConversionCategory category, String symbol, ConversionOperation toBaseOperation, ConversionOperation fromBaseOperation) {
        this.category = category;
        this.symbol = symbol;
        this.toBaseOperation = toBaseOperation;
        this.fromBaseOperation = fromBaseOperation;
    }

    public ConversionCategory getCategory() {
        return category;
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
