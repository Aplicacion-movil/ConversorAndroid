package com.aplicacionmovil.conversor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ConversionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        View rootView = findViewById(R.id.conversion_root);
        TextView categoryTitle = findViewById(R.id.category_title);
        Button backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(view -> finish());

        String category = getIntent().getStringExtra(
                SelectionActivity.EXTRA_CATEGORY
        );

        if (!isValidCategory(category)) {
            categoryTitle.setText(R.string.invalid_category_title);

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

        categoryTitle.setText(
                getString(R.string.conversion_title, category)
        );
    }

    private boolean isValidCategory(String category) {
        if (category == null) {
            return false;
        }

        String[] categories = getResources().getStringArray(
                R.array.conversion_categories
        );

        // La posición 0 contiene "Selecciona una categoría".
        for (int index = 1; index < categories.length; index++) {
            if (category.equals(categories[index])) {
                return true;
            }
        }

        return false;
    }
}