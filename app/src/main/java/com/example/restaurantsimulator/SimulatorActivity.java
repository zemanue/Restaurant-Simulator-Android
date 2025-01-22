package com.example.restaurantsimulator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SimulatorActivity extends AppCompatActivity {

    private static final int openingHour = 13;
    private static int currentHour = openingHour;
    private static final int closingHour = 21;
    private static int day = 1;

    TextView textViewDay, textViewHour, textViewOpenedClosed;
    EditText editTextNumClients;
    ImageButton imageButtonTable1, imageButtonTable2, imageButtonTable3, imageButtonTable4,
            imageButtonTable5, imageButtonTable6, imageButtonTable7, imageButtonTable8,
            imageButtonTable9, imageButtonTable10, imageButtonTable11,imageButtonTable12,
            imageButtonTable13, imageButtonTable14, imageButtonTable15, imageButtonTable16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_simulator);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewDay = findViewById(R.id.textViewDay);
        textViewDay.setText("Día: " + day);

        textViewHour = findViewById(R.id.textViewHour);
        textViewHour.setText("Hora: " + currentHour + ":00 ");

        textViewOpenedClosed = findViewById(R.id.textViewOpenedClosed);
        textViewOpenedClosed.setText("Abierto");

        editTextNumClients = findViewById(R.id.editTextNumClients);

        // Buttons of all tables
        imageButtonTable1 = findViewById(R.id.imageButtonTable1);
        imageButtonTable2 = findViewById(R.id.imageButtonTable2);
        imageButtonTable3 = findViewById(R.id.imageButtonTable3);
        imageButtonTable4 = findViewById(R.id.imageButtonTable4);
        imageButtonTable5 = findViewById(R.id.imageButtonTable5);
        imageButtonTable6 = findViewById(R.id.imageButtonTable6);
        imageButtonTable7 = findViewById(R.id.imageButtonTable7);
        imageButtonTable8 = findViewById(R.id.imageButtonTable8);
        imageButtonTable9 = findViewById(R.id.imageButtonTable9);
        imageButtonTable10 = findViewById(R.id.imageButtonTable10);
        imageButtonTable11 = findViewById(R.id.imageButtonTable11);
        imageButtonTable12 = findViewById(R.id.imageButtonTable12);
        imageButtonTable13 = findViewById(R.id.imageButtonTable13);
        imageButtonTable14 = findViewById(R.id.imageButtonTable14);
        imageButtonTable15 = findViewById(R.id.imageButtonTable15);
        imageButtonTable16 = findViewById(R.id.imageButtonTable16);


    }

    public void confirmNumClients(View v) {
        String textFromEditText = editTextNumClients.getText().toString();
        int numClients = Integer.parseInt(textFromEditText);
        // Provisional utility to test if it works
        Toast.makeText(this, String.valueOf(numClients), Toast.LENGTH_SHORT).show();
    }

    public void showTableInfo(View v) {
        String tableNumber = v.getContentDescription().toString();
        Toast.makeText(this, tableNumber, Toast.LENGTH_LONG).show();
    }
}