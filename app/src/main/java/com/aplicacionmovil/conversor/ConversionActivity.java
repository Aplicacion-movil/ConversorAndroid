package com.aplicacionmovil.conversor;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aplicacionmovil.conversor.domain.ConversionCategory;
import com.aplicacionmovil.conversor.domain.ConversionEngine;
import com.aplicacionmovil.conversor.domain.ConversionUnit;
import com.aplicacionmovil.conversor.exceptions.IllegalAbsoluteZero;
import com.google.android.material.snackbar.Snackbar;
import com.aplicacionmovil.conversor.presentation.ConversionLabels;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ConversionActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY = "com.aplicacionmovil.conversor.extra.CATEGORY";

    private View rootView;
    private EditText inputValue;
    private Spinner sourceUnitSpinner;
    private Spinner targetUnitSpinner;
    private Button convertButton;
    private TextView resultView;

    private ConversionCategory category;

    private final ConversionEngine conversionEngine = new ConversionEngine();

    private List<ConversionUnit> units = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        rootView = findViewById(R.id.conversion_root);

        TextView categoryTitle = findViewById(R.id.category_title);

        inputValue = findViewById(R.id.input_value);

        sourceUnitSpinner = findViewById(R.id.source_unit_spinner);

        targetUnitSpinner = findViewById(R.id.target_unit_spinner);

        convertButton = findViewById(R.id.convert_button);

        resultView = findViewById(R.id.result_view);

        Button backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(view -> finish());

        String categoryKey = getIntent().getStringExtra(EXTRA_CATEGORY);

        category = ConversionCategory.fromKey(categoryKey);

        if (category == null) {
            categoryTitle.setText(R.string.invalid_category_title);
            setConversionFormEnabled(false);
            Snackbar.make(
                    rootView,
                    R.string.invalid_category,
                    Snackbar.LENGTH_INDEFINITE
            ).setAction(
                    R.string.back_button,
                    view -> finish()
            ).show();

            return;
        }

        String categoryLabel = getString(ConversionLabels.getCategoryLabel(category));

        categoryTitle.setText(getString(
                        R.string.conversion_title,
                        categoryLabel
                ));
        configureUnitSpinners(category);
        convertButton.setOnClickListener(view -> convertValue());
    }

    private void configureUnitSpinners(ConversionCategory category) {
        units = conversionEngine.getUnitsFor(category);

        if (units.isEmpty()) {
            setConversionFormEnabled(false);

            Snackbar.make(
                    rootView,
                    R.string.units_unavailable,
                    Snackbar.LENGTH_INDEFINITE
            ).setAction(
                    R.string.back_button,
                    view -> finish()
            ).show();

            return;
        }

        List<String> unitLabels = new ArrayList<>();

        for (ConversionUnit unit : units) {
            unitLabels.add(getString(ConversionLabels.getUnitLabel(unit)));
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        unitLabels
                );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sourceUnitSpinner.setAdapter(adapter);
        targetUnitSpinner.setAdapter(adapter);

        if (units.size() > 1) {
            targetUnitSpinner.setSelection(1);
        }
    }

    private void convertValue() {
        String rawValue = inputValue
                .getText()
                .toString()
                .trim();

        if (rawValue.isEmpty()) {
            showError(R.string.input_required);

            return;
        }

        Double value = parseInputValue(rawValue);

        if (value == null) {
            showError(R.string.invalid_input);

            return;
        }

        int sourcePosition = sourceUnitSpinner.getSelectedItemPosition();

        int targetPosition = targetUnitSpinner.getSelectedItemPosition();

        if (sourcePosition < 0
                || targetPosition < 0
                || sourcePosition >= units.size()
                || targetPosition >= units.size()) {
            showError(R.string.invalid_unit);

            return;
        }

        ConversionUnit sourceUnit = units.get(sourcePosition);

        ConversionUnit targetUnit = units.get(targetPosition);

        try {
            if (category == ConversionCategory.TEMPERATURE) {
                if ((sourceUnit == ConversionUnit.CELSIUS && value < -273.15) ||
                    (sourceUnit == ConversionUnit.FAHRENHEIT && value < -459.67) ||
                    (sourceUnit == ConversionUnit.KELVIN && value < 0.0) ||
                    (sourceUnit == ConversionUnit.RANKIRE && value < 0.0))
                    throw new IllegalAbsoluteZero("Absolute zero subpassed");
            }

            double convertedValue = conversionEngine.convert(value, sourceUnit, targetUnit);

            resultView.setText(getString(
                            R.string.result_format,
                            formatNumber(value),
                            sourceUnit.getSymbol(),
                            formatNumber(convertedValue),
                            targetUnit.getSymbol()
                    ));
        } catch (IllegalArgumentException exception) {
            showError(R.string.conversion_error);
        }
        catch (IllegalAbsoluteZero e) {
            showError(R.string.absolute_zero);
        }
    }

    private Double parseInputValue(String text) {
        String normalizedText = text.replace(',', '.');

        try {
            double value = Double.parseDouble(normalizedText);

            return (Double.isNaN(value) || Double.isInfinite(value)) ? null : value;
        } catch (NumberFormatException exception) {
            return null;
        }
    }

    private String formatNumber(double value) {
        NumberFormat format = NumberFormat.getNumberInstance(Locale.getDefault());

        format.setGroupingUsed(false);
        format.setMaximumFractionDigits(10);

        return format.format(value);
    }

    private void showError(int messageResId) {
        Snackbar.make(rootView, messageResId, Snackbar.LENGTH_LONG).show();
    }

    private void setConversionFormEnabled(boolean enabled) {
        inputValue.setEnabled(enabled);
        sourceUnitSpinner.setEnabled(enabled);
        targetUnitSpinner.setEnabled(enabled);
        convertButton.setEnabled(enabled);
    }
}