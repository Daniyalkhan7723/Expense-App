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
import com.sis.expensiveaapp.DATABSE.DatabaseHelper2;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper3;
import com.sis.expensiveaapp.Model.Pojo;
import com.sis.expensiveaapp.Model.Pojo1;
import com.sis.expensiveaapp.R;
import com.sis.expensiveaapp.TablayoutActivivities.DetailsActivity;

import java.util.Calendar;
import java.util.Date;

public class AddAssets extends AppCompatActivity {
    EditText AssetAmount, AssetDescription,AssetTitle;
    Button btnAddAssets;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assets);
        toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Add Assets");
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        AssetAmount= (EditText) findViewById(R.id.assetAmount);
        AssetTitle = (EditText) findViewById(R.id.assetTitle);
        AssetDescription = (EditText) findViewById(R.id.assetDes);

        btnAddAssets = findViewById(R.id.assetButton);
        btnAddAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amountAssets = Integer.valueOf(AssetAmount.getText().toString());
                String TitleAssets=AssetTitle.getText().toString();
                String DesAssets=AssetDescription.getText().toString();
                DatabaseHelper3 db=new DatabaseHelper3(getApplicationContext());
                Pojo1 pojo2=new Pojo1(amountAssets,TitleAssets,DesAssets);
                long result=db.DataInsertingAssets(pojo2);
                if (result != -1){
                    startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                    Toast.makeText(getApplicationContext(),"Assets INSERT",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Assets Not INSERT",Toast.LENGTH_LONG).show();
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