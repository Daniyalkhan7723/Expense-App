package com.sis.expensiveaapp.UpdateActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.sis.expensiveaapp.Activities.MainActivity;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper;
import com.sis.expensiveaapp.Model.Pojo;
import com.sis.expensiveaapp.R;
import com.sis.expensiveaapp.TablayoutActivivities.DetailsActivity;

import java.util.UUID;

public class IncomeUpdate extends AppCompatActivity {
    EditText incomeUpdateText, descUpdateIncome;
    Button btnUpdateIncome;
    public static DatePicker incomeUpdateDatePicker;
    Toolbar toolbarUpdate1;
    DatabaseHelper database;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_update);
        Pojo model= (Pojo) getIntent().getExtras().getSerializable("Expense");

        toolbarUpdate1 = findViewById(R.id.toolbarupdate1);
        toolbarUpdate1.setTitle("Update Income");
        setSupportActionBar(toolbarUpdate1);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        incomeUpdateText = (EditText) findViewById(R.id.amountupdate);
        descUpdateIncome = (EditText) findViewById(R.id.icomeDesUpdate);
        incomeUpdateDatePicker = findViewById(R.id.UpdatedDatePicker);
        btnUpdateIncome = findViewById(R.id.IncomeUpdateButton);
        incomeUpdateText.setText(String.valueOf(model.getAmount()));
        id=model.getId();
        descUpdateIncome.setText(model.getDescription());
        btnUpdateIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int TotalIncome =  Integer.valueOf(incomeUpdateText.getText().toString());
                String IncomeDes= descUpdateIncome.getText().toString();
                int day= incomeUpdateDatePicker.getDayOfMonth();
                int month= incomeUpdateDatePicker.getMonth();
                int year= incomeUpdateDatePicker.getYear();
                String date=month+" " +day+", "+year;
                String id = UUID.randomUUID().toString();
                Pojo pojo=new Pojo(TotalIncome,id,IncomeDes,date);
                database = new DatabaseHelper(getApplicationContext());

                long result1=database.updateExpense(pojo);
                if (result1 != -1){
                     //startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    Toast.makeText(IncomeUpdate.this, "Income Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Income Not Updated",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}