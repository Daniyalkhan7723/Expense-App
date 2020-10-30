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

public class AddSaving extends AppCompatActivity {
    EditText savingAmountText, savingDescText;
    Button btnAddSaving;
    public static DatePicker datePickerText7;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saving);
        toolbar=findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Add Saving");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        savingAmountText = (EditText) findViewById(R.id.savingamountAdd);
        savingDescText = (EditText) findViewById(R.id.savingDes);
        datePickerText7=findViewById(R.id.SavingdatePicker);
        btnAddSaving = findViewById(R.id.SavingAddButton);
        btnAddSaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int TotalSaving = Integer.valueOf(savingAmountText.getText().toString());
                String SavingDes=savingDescText.getText().toString();
//                Date date= Calendar.getInstance().getTime();
                int day=datePickerText7.getDayOfMonth();
                int month=datePickerText7.getMonth();
                int year=datePickerText7.getYear();
                String date1=month+" " +day+", "+year;
                String id = UUID.randomUUID().toString();
                DatabaseHelper db=new DatabaseHelper(getApplicationContext());
                Pojo pojo=new Pojo(TotalSaving,SavingDes,date1);
                //instance of db helper class
                long result=db.DataInsertSaving(pojo);
                if (result != -1){

                    startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    Toast.makeText(AddSaving.this, "Saving is Insert", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Saving Not Insert",Toast.LENGTH_LONG).show();
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