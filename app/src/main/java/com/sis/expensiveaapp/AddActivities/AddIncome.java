package com.sis.expensiveaapp.AddActivities;

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

public class AddIncome extends AppCompatActivity {
    EditText incomeText, descText;
    Button btnAddIncome;
    public static DatePicker datePickerText;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        toolbar=findViewById(R.id.toolbar2);
        toolbar.setTitle("Add Income");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        incomeText = (EditText) findViewById(R.id.amountAdd);
        descText = (EditText) findViewById(R.id.icomeDes);
        datePickerText=findViewById(R.id.datePicker);
        btnAddIncome = findViewById(R.id.IncomeAddButton);

        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int TotalIncome =  Integer.valueOf(incomeText.getText().toString());
                String IncomeDes=descText.getText().toString();
//                Date date= Calendar.getInstance().getTime();
                    int day=datePickerText.getDayOfMonth();
                    int month=datePickerText.getMonth();
                    int year=datePickerText.getYear();
                    String date=month+" " +day+", "+year;
                    String id = UUID.randomUUID().toString();
                DatabaseHelper db=new DatabaseHelper(getApplicationContext());
                Pojo pojo=new Pojo(TotalIncome,IncomeDes,date);
                long result=db.DataInsertIncome(pojo);
                if (result != -1){
                    //startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    Toast.makeText(AddIncome.this, "DataInsert", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Income Not INSERT",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            finish(); // close this activity and return to preview activity (if there is any)
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }




}