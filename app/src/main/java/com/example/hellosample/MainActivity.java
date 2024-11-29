package com.example.hellosample;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.function.Supplier;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etName = findViewById(R.id.etName);
        this.tvOutput = findViewById(R.id.tvOutput);

        Supplier<String> supplyEtName;
        Supplier<String> supplyTvOutput;

        TextView btClick = findViewById(R.id.btClick);
        supplyEtName = () -> {
            return etName.getText().toString();
        };
        supplyTvOutput = () -> {
            return etName.getText().toString() + "さんこんにちは";
        };
        btClick.setOnClickListener(new ButtonClickListener(supplyEtName, supplyTvOutput));

        TextView btClear = findViewById(R.id.btClear);
        supplyEtName = () -> {
            return "";
        };
        supplyTvOutput = () -> {
            return "";
        };
        btClear.setOnClickListener(new ButtonClickListener(supplyEtName, supplyTvOutput));
    }

    private class ButtonClickListener implements View.OnClickListener {

        private final Supplier<String> supplyEtName;
        private final Supplier<String> supplyTvOutput;

        public ButtonClickListener(
                Supplier<String> supplyEtName,
                Supplier<String> supplyTvOutput) {
            this.supplyEtName = supplyEtName;
            this.supplyTvOutput = supplyTvOutput;
        }

        @Override
        public void onClick(View view) {
            etName.setText(supplyEtName.get());
            tvOutput.setText(supplyTvOutput.get());
        }
    }
}