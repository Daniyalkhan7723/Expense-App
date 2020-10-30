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
import com.sis.expensiveaapp.AddActivities.AddLiability;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper2;
import com.sis.expensiveaapp.Model.Pojo;
import com.sis.expensiveaapp.Model.Pojo1;
import com.sis.expensiveaapp.R;
import com.sis.expensiveaapp.TablayoutActivivities.DetailsActivity;

import java.util.UUID;

public class LiabilityUpdates extends AppCompatActivity {
    EditText LiaUpdateText, descUpdeLia;
    Button btnUpdateLia;
    public static DatePicker liaUpdateDatePicker;
    Toolbar toolbarUpdate2;
    DatabaseHelper2 bd1;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liability_updates);
        Pojo1 p1= (Pojo1) getIntent().getExtras().getSerializable("Expenses");
        toolbarUpdate2 = findViewById(R.id.toolbarupdated2);
        toolbarUpdate2.setTitle("Update Liability");
        setSupportActionBar(toolbarUpdate2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        LiaUpdateText = (EditText) findViewById(R.id.liabilityupdate);
        descUpdeLia = (EditText) findViewById(R.id.liabilityDesUpdate);
        liaUpdateDatePicker = findViewById(R.id.liaUpdatedDatePicker);
        btnUpdateLia = findViewById(R.id.liaUpdateButton);
        LiaUpdateText.setText(String.valueOf(p1.getAmount()));
        id=p1.getId();
        descUpdeLia.setText(p1.getDescription());

        btnUpdateLia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int LiaIncome =  Integer.valueOf(LiaUpdateText.getText().toString());
                String LiaDes= descUpdeLia.getText().toString();
                int day= liaUpdateDatePicker.getDayOfMonth();
                int month= liaUpdateDatePicker.getMonth();
                int year= liaUpdateDatePicker.getYear();
                String date=month+" " +day+", "+year;
                String id = UUID.randomUUID().toString();
                Pojo1 pojo=new Pojo1(LiaIncome,id,LiaDes,date);
                bd1 = new DatabaseHelper2(getApplicationContext());
                long result1=bd1.updateLia(pojo);
                if (result1 != -1){
                    //startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    Toast.makeText(LiabilityUpdates.this, "Liability Updated", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Liability Not Updated",Toast.LENGTH_LONG).show();
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