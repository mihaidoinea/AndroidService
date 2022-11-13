package ro.csie.en.androidservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etFirst, etSecond, etResult;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirst = findViewById(R.id.etFirst);
        etSecond = findViewById(R.id.etSecond);
        etResult = findViewById(R.id.etResult);
        button = findViewById(R.id.button);

        button.setOnClickListener(view -> {

        });

    }
}