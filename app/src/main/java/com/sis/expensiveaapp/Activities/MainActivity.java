package com.sis.expensiveaapp.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;
import com.sis.expensiveaapp.BottomSheets.AssetsSheetDialogue;
import com.sis.expensiveaapp.BottomSheets.IncomeSheetDialogue;
import com.sis.expensiveaapp.BottomSheets.LiabilitySheetDialogue;
import com.sis.expensiveaapp.BottomSheets.SavingSheetDialogue;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper2;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper3;
import com.sis.expensiveaapp.Model.Pojo;
import com.sis.expensiveaapp.R;
import com.sis.expensiveaapp.TablayoutActivivities.DetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    DatabaseHelper2 databaseHelper2;
    DatabaseHelper3 databaseHelper3;
    AnyChartView anyChartView;
    TextView textViewIncome, textViewSaving,textLiability,textAssets;
    List<Pojo> ls;

    String[] expenses={"Income","Saving","Liability","Assets"};
    int[] amounts={500,1000,3000,2000};

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        databaseHelper2 = new DatabaseHelper2(this);
        databaseHelper3 = new DatabaseHelper3(this);
        textViewIncome = findViewById(R.id.show_income);
        textViewSaving = findViewById(R.id.show_saving);
        textLiability = findViewById(R.id.textLiability);
        textAssets = findViewById(R.id.textAsset);
        anyChartView=findViewById(R.id.anyChart);

        LinearLayout IncomeButton = findViewById(R.id.income);
        LinearLayout SavingButton = findViewById(R.id.saving);
        LinearLayout LiabilityButton = findViewById(R.id.liability1);
        LinearLayout AssetsButton = findViewById(R.id.asset2);
        ls = new ArrayList<>();
        IncomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IncomeSheetDialogue incomeSheetDialogue = new IncomeSheetDialogue();
                incomeSheetDialogue.show(getSupportFragmentManager(), "BottomSheet");
            }
        });
        SavingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavingSheetDialogue savingSheetDialogue = new SavingSheetDialogue();
                savingSheetDialogue.show(getSupportFragmentManager(), "BottomSheet");
            }
        });
        LiabilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            LiabilitySheetDialogue liabilitySheetDialogue =new LiabilitySheetDialogue();
                liabilitySheetDialogue.show(getSupportFragmentManager(), "BottomSheet");
   //             startActivity(new Intent(getApplicationContext(), DetailsActivity.class));


            }
        });
        AssetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AssetsSheetDialogue assetsSheetDialogue = new AssetsSheetDialogue();
                assetsSheetDialogue.show(getSupportFragmentManager(), "BottomSheet");
            }
        });

        viewIncome();
        viewSaving();
        viewLiability();
        setUpPieChart();
//        drawpiew();
    }

    private void setUpPieChart() {
        Pie pie= AnyChart.pie();
        List<DataEntry> dataEntery =new ArrayList<>();

        for(int i=0;i<expenses.length;i++){
            dataEntery.add(new ValueDataEntry(expenses[i],amounts[i]));
        }
        pie.data(dataEntery);
        anyChartView.setChart(pie);


    }


    private void viewIncome() {
        Cursor cursor1 = databaseHelper.viewIncomeData();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor1.moveToLast()) {
            stringBuilder.append("PKR " + cursor1.getInt(1));
        }
        textViewIncome.setText(stringBuilder);
    }

    private void viewSaving() {
        Cursor cursor2 = databaseHelper.viewSavingData();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor2.moveToLast()) {
            stringBuilder.append("PKR : " + cursor2.getInt(1));
        }
        textViewSaving.setText(stringBuilder);
    }
    private void viewLiability() {
        Cursor cursor3 = databaseHelper2.viewLiabilityData();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor3.moveToLast()) {
            stringBuilder.append("PKR " + cursor3.getInt(1));
        }
        textLiability.setText(stringBuilder);
    }
    private void viewAssets() {
        Cursor cursor4 = databaseHelper3.viewAssetsData();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor4.moveToLast()) {
            stringBuilder.append("PKR : " + cursor4.getInt(1));
        }
        textAssets.setText(stringBuilder);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewIncome();
        viewSaving();
        viewLiability();
        viewAssets();
    }

    public void DetailActivity(View view) {
        startActivity(new Intent(getApplicationContext(),DetailsActivity.class));
    }

    //    private void drawpiew() {
//        AnimatedPieView mAnimatedPieView = findViewById(R.id.animatedPieView);
//        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
//        config.startAngle(-90)// Starting angle offset
//                .addData(new SimplePieInfo(30, Color.parseColor("#29CF4E"), "Title1"))//Data (bean that implements the IPieInfo interface)
//                .addData(new SimplePieInfo(18.0f, Color.parseColor("#29CF4E"), "Title2")).drawText(true)
//      .duration(2000);// draw pie animation duration
//
// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
//        mAnimatedPieView.applyConfig(config);
//        mAnimatedPieView.start();



}