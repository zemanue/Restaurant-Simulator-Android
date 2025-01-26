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
    // Create a Restaurant object with default values
    Restaurant myRestaurant = new Restaurant();

    TextView textViewDay, textViewHour, textViewOpenedClosed;
    EditText editTextNumClients, editTextTableNumber;

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

        // CLIENTS AND TABLE SECTION
        editTextNumClients = findViewById(R.id.editTextNumClients);
        editTextTableNumber = findViewById(R.id.editTextTableNumber);

        // TABLE INITIALIZATION
        final int[] TABLE_CAPACITIES = {2, 2, 2, 2, 2, 4, 4, 4, 10, 6, 2, 2, 6, 4, 8, 12};
        final boolean[] TABLE_PROXIMITY_TO_WINDOWS = {false, false, false, true, true, true, false, false, true, true, false, false, false, true, true, true};

        myRestaurant.initializeTables(TABLE_CAPACITIES, TABLE_PROXIMITY_TO_WINDOWS);

        // TABLE BUTTONS SETUP
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        for (int i = 0; i < myRestaurant.getTableList().size(); i++) {
            Table table = myRestaurant.getTableList().get(i);
            ImageButton imageButton = tableLayout.findViewWithTag("table_" + (i + 1));
            if (imageButton != null) {
                imageButton.setOnClickListener(v -> showTableInfo(table)); // Pass the Table object
            }
        }

    }

    public void confirmNumClients(View v) {
        String textFromEditText = editTextNumClients.getText().toString();
        int numClients = Integer.parseInt(textFromEditText);
        int chosenTable = myRestaurant.findSuitableTable(numClients);

        if (chosenTable > 0) {
            Toast.makeText(this, "Mesa ocupada: " + chosenTable, Toast.LENGTH_SHORT).show();
            ImageButton imageButton = findViewById(R.id.tableLayout).findViewWithTag("table_" + chosenTable);
            imageButton.setImageResource(R.drawable.occupied_table);
        } else {
            Toast.makeText(this, "No quedan mesas disponibles para este número de clientes.", Toast.LENGTH_SHORT).show();
        }
    }

    public void confirmFreeTable(View v) {
        String textFromEditText = editTextTableNumber.getText().toString();
        int tableNumber = Integer.parseInt(textFromEditText);
        Table table = myRestaurant.getTableList().get(tableNumber - 1);
        if (table.freeTable(false)) {
            Toast.makeText(this, "Mesa liberada: " + tableNumber, Toast.LENGTH_SHORT).show();
            ImageButton imageButton = findViewById(R.id.tableLayout).findViewWithTag("table_" + tableNumber);
            imageButton.setImageResource(R.drawable.unoccupied_table);
        } else {
            Toast.makeText(this, "Esta mesa ya está libre " + tableNumber, Toast.LENGTH_SHORT).show();
        }

    }

    public void showTableInfo(Table table) {
        String tableInfo = table.getDetails();
        Toast.makeText(this, tableInfo, Toast.LENGTH_LONG).show();
    }
}