package com.aplicacionmovil.conversor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aplicacionmovil.conversor.domain.ConversionCategory;
import com.aplicacionmovil.conversor.domain.ConversionEngine;
import com.aplicacionmovil.conversor.domain.ConversionUnit;
import com.aplicacionmovil.conversor.presentation.ConversionLabels;
import com.google.android.material.snackbar.Snackbar;

import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class FineConversionActivity extends AppCompatActivity {
    public static final String EXTRA_CATEGORY = "com.aplicacionmovil.conversor.extra.CATEGORY";

    private View rootView;
    private EditText inputSpeed;
    private EditText inputAlcohol;
    private Switch nobelDriver;
    private Button convertButton;
    private TextView resultView;

    private ConversionCategory category;

    private final ConversionEngine conversionEngine = new ConversionEngine();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fine_conversion);

        rootView = findViewById(R.id.conversion_root);

        TextView categoryTitle = findViewById(R.id.category_title);

        inputSpeed = findViewById(R.id.input_speed);
        inputAlcohol = findViewById(R.id.input_alcohol);

        nobelDriver = findViewById(R.id.novel_driver);

        convertButton = findViewById(R.id.convert_button);

        resultView = findViewById(R.id.result_view);

        Button backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(view -> finish());

        String categoryKey = getIntent().getStringExtra(EXTRA_CATEGORY);

        category = ConversionCategory.fromKey(categoryKey);

        String categoryLabel = getString(ConversionLabels.getCategoryLabel(category));

        categoryTitle.setText(getString(R.string.conversion_title, categoryLabel));
        convertButton.setOnClickListener(view -> convertValue());
    }

    private void convertValue() {
        String rawSpeed = inputSpeed.getText().toString().trim();
        String rawAlcohol = inputAlcohol.getText().toString().trim();

        if (rawSpeed.isEmpty() || rawAlcohol.isEmpty()) {
            showError(R.string.input_required);

            return;
        }

        Double speed = parseInputValue(rawSpeed);
        Double alcohol = parseInputValue(rawAlcohol);

        if (speed == null || alcohol == null) {
            showError(R.string.invalid_input);

            return;
        }

        try {
            String value = conversionEngine.convert(speed.intValue(), alcohol, nobelDriver.isChecked());

            resultView.setText(value);
        } catch (IllegalArgumentException exception) {
            showError(R.string.conversion_error);
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

    private void showError(int messageResId) {
        Snackbar.make(rootView, messageResId, Snackbar.LENGTH_LONG).show();
    }
}