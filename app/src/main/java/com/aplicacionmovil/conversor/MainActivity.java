package com.aplicacionmovil.conversor;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {

    private Spinner categorySpinner;
    private Spinner fromUnitSpinner;
    private Spinner toUnitSpinner;
    private EditText valueInput;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categorySpinner = findViewById(R.id.category_spinner);
        fromUnitSpinner = findViewById(R.id.from_unit_spinner);
        toUnitSpinner = findViewById(R.id.to_unit_spinner);
        valueInput = findViewById(R.id.value_input);
        resultText = findViewById(R.id.result_text);
        Button swapButton = findViewById(R.id.swap_button);
        Button convertButton = findViewById(R.id.convert_button);

        List<String> categories = new ArrayList<>(UnitConverter.getCategories());
        categorySpinner.setAdapter(createAdapter(categories));
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUnits(categories.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Siempre existe una categoría seleccionada.
            }
        });

        swapButton.setOnClickListener(view -> swapUnits());
        convertButton.setOnClickListener(view -> performConversion());
    }

    private ArrayAdapter<String> createAdapter(List<String> values) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                values
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    private void updateUnits(String category) {
        List<String> units = new ArrayList<>(UnitConverter.getUnits(category));
        fromUnitSpinner.setAdapter(createAdapter(units));
        toUnitSpinner.setAdapter(createAdapter(units));
        if (units.size() > 1) {
            toUnitSpinner.setSelection(1);
        }
        resultText.setText(R.string.result_placeholder);
    }

    private void swapUnits() {
        int fromPosition = fromUnitSpinner.getSelectedItemPosition();
        int toPosition = toUnitSpinner.getSelectedItemPosition();
        fromUnitSpinner.setSelection(toPosition);
        toUnitSpinner.setSelection(fromPosition);

        if (!valueInput.getText().toString().trim().isEmpty()) {
            performConversion();
        }
    }

    private void performConversion() {
        String rawValue = valueInput.getText().toString().trim();
        if (rawValue.isEmpty()) {
            valueInput.setError(getString(R.string.value_required));
            valueInput.requestFocus();
            return;
        }

        try {
            double value = Double.parseDouble(normalizeDecimal(rawValue));
            String category = categorySpinner.getSelectedItem().toString();
            String fromUnit = fromUnitSpinner.getSelectedItem().toString();
            String toUnit = toUnitSpinner.getSelectedItem().toString();
            double result = UnitConverter.convert(category, value, fromUnit, toUnit);

            resultText.setText(getString(
                    R.string.result_format,
                    formatNumber(value),
                    fromUnit,
                    formatNumber(result),
                    toUnit
            ));
            valueInput.setError(null);
        } catch (IllegalArgumentException exception) {
            valueInput.setError(getString(R.string.invalid_value));
            valueInput.requestFocus();
        }
    }

    private String normalizeDecimal(String value) {
        if (value.indexOf(',') >= 0 && value.indexOf('.') < 0) {
            return value.replace(',', '.');
        }
        return value;
    }

    private String formatNumber(double value) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.getDefault());
        DecimalFormat format = new DecimalFormat("#,##0.######", symbols);
        return format.format(value);
    }
}
