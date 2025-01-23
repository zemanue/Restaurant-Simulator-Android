package com.example.restaurantsimulator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SimulatorActivity extends AppCompatActivity {

    Restaurant myRestaurant = new Restaurant();

    TextView textViewDay, textViewHour, textViewOpenedClosed;
    EditText editTextNumClients;

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

        // BASIC INFO SECTION (DAY, HOUR, OPENED/CLOSED)
        textViewDay = findViewById(R.id.textViewDay);
        textViewDay.setText("Día: " + myRestaurant.getDay());

        textViewHour = findViewById(R.id.textViewHour);
        textViewHour.setText("Hora: " + myRestaurant.getCurrentHour() + ":00 ");

        textViewOpenedClosed = findViewById(R.id.textViewOpenedClosed);
        textViewOpenedClosed.setText((myRestaurant.isOpened()) ? "Abierto" : "Cerrado");

        // CLIENTS SECTION
        editTextNumClients = findViewById(R.id.editTextNumClients);

        // TABLE INITIALIZATION
        final int[] TABLE_CAPACITIES = {2, 2, 2, 2, 2, 4, 4, 4, 10, 6, 2, 2, 6, 4, 8, 12};
        final boolean[] TABLE_AVAILABILITIES = {false, false, false, true, true, true, false, false, true, true, false, false, false, true, true, true};

        myRestaurant.initializeTables(TABLE_CAPACITIES, TABLE_AVAILABILITIES);

        // TABLE BUTTONS SETUP
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        for (int i = 0; i < myRestaurant.getTableList().size(); i++) {
            Table table = myRestaurant.getTableList().get(i);
            ImageButton imageButton = (ImageButton) tableLayout.findViewWithTag("table_" + (i + 1));
            if (imageButton != null) {
                imageButton.setOnClickListener(v -> showTableInfo(table)); // Pass the Table object
            }
        }

    }

    public void confirmNumClients(View v) {
        String textFromEditText = editTextNumClients.getText().toString();
        int numClients = Integer.parseInt(textFromEditText);
        // Provisional utility to test if it works
        Toast.makeText(this, String.valueOf(numClients), Toast.LENGTH_SHORT).show();
    }

    public void showTableInfo(Table table) {
        String tableInfo = table.getDetails();
        Toast.makeText(this, tableInfo, Toast.LENGTH_LONG).show();
    }
}