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
import com.sis.expensiveaapp.DATABSE.DatabaseHelper2;
import com.sis.expensiveaapp.Model.Pojo1;
import com.sis.expensiveaapp.R;
import com.sis.expensiveaapp.TablayoutActivivities.DetailsActivity;

import java.util.UUID;

public class AddLiability extends AppCompatActivity {
    EditText LiaAmount, LiaDest,LiaTitle;
    Button btnAddIncome;
    public static DatePicker LiaDatePickerText;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_liability);
        toolbar=findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Add Liability");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        LiaAmount = (EditText) findViewById(R.id.liaAmount);
        LiaDest = (EditText) findViewById(R.id.liaDes);
//        LiaTitle = (EditText) findViewById(R.id.liabTitile);
        LiaDatePickerText=findViewById(R.id.liaDatePicker);
        btnAddIncome = findViewById(R.id.liaAddButton);

        btnAddIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int AmountLia =  Integer.valueOf(LiaAmount.getText().toString());
 //               String LiabTitle=LiaTitle.getText().toString();
                String LiabDes=LiaDest.getText().toString();
                int day=LiaDatePickerText.getDayOfMonth();
                int month=LiaDatePickerText.getMonth();
                int year=LiaDatePickerText.getYear();
                String date=month+" " +day+", "+year;
                String id = UUID.randomUUID().toString();
             //   Date date= Calendar.getInstance().getTime();
                DatabaseHelper2 db=new DatabaseHelper2(getApplicationContext());

                Pojo1 pojo1=new Pojo1(AmountLia,LiabDes,date);
                long result1=db.DataInsertingLiability(pojo1);
                if (result1 != -1){
                    //startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    Toast.makeText(AddLiability.this, "Liability Insert", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Liability Not INSERT",Toast.LENGTH_LONG).show();
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