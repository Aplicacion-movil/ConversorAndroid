package com.aplicacionmovil.conversor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SelectionActivity extends AppCompatActivity {

    public static final String EXTRA_CATEGORY =
            "com.aplicacionmovil.conversor.extra.CATEGORY";

    private static final int[] CATEGORY_IMAGES = {
            0,
            R.drawable.category_length,
            R.drawable.category_temperature,
            R.drawable.category_mass,
            R.drawable.category_volume
    };

    private View rootView;
    private Spinner categorySpinner;
    private ImageView categoryImage;
    private Bitmap currentBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        rootView = findViewById(R.id.selection_root);
        categorySpinner = findViewById(R.id.category_spinner);
        categoryImage = findViewById(R.id.category_image);
        Button continueButton = findViewById(R.id.continue_button);

        configureSpinner();

        continueButton.setOnClickListener(
                view -> openConversionActivity()
        );
    }

    private void configureSpinner() {
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(
                        this,
                        R.array.conversion_categories,
                        android.R.layout.simple_spinner_item
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id
                    ) {
                        showCategoryImage(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        clearCategoryImage();
                    }
                }
        );
    }

    private void showCategoryImage(int position) {
        if (position <= 0 || position >= CATEGORY_IMAGES.length) {
            clearCategoryImage();
            return;
        }

        currentBitmap = BitmapFactory.decodeResource(
                getResources(),
                CATEGORY_IMAGES[position]
        );

        if (currentBitmap == null) {
            clearCategoryImage();

            Snackbar.make(
                    rootView,
                    R.string.image_error,
                    Snackbar.LENGTH_SHORT
            ).show();

            return;
        }

        categoryImage.setImageBitmap(currentBitmap);
    }

    private void clearCategoryImage() {
        currentBitmap = null;
        categoryImage.setImageDrawable(null);
    }

    private void openConversionActivity() {
        int position = categorySpinner.getSelectedItemPosition();

        if (position <= 0) {
            Snackbar.make(
                    rootView,
                    R.string.category_required,
                    Snackbar.LENGTH_SHORT
            ).show();

            return;
        }

        String category = categorySpinner
                .getSelectedItem()
                .toString();

        Intent intent = new Intent(
                this,
                ConversionActivity.class
        );

        intent.putExtra(EXTRA_CATEGORY, category);
        startActivity(intent);
    }
}