package com.sis.expensiveaapp.TablayoutActivivities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sis.expensiveaapp.AddActivities.AddSaving;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper;
import com.sis.expensiveaapp.Model.Pojo;
import com.sis.expensiveaapp.R;

import java.util.ArrayList;
import java.util.List;

public class TabSaving extends Fragment {
    RecyclerView recyclerView2;
    List<Pojo> pojoList1;
    DatabaseHelper databaseHelper;
    IncomeAdaptor SavingAdaptor;
    TextView Money2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saving, container, false);
        recyclerView2 = view.findViewById(R.id.recylerview2);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);


        ImageView plusActivity=view.findViewById(R.id.plusActivity);

        Money2=view.findViewById(R.id.money2);
        recyclerView2.setLayoutManager(mLayoutManager);
        pojoList1 = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getActivity());
        pojoList1 = databaseHelper.ViewSavingData();
        SavingAdaptor = new IncomeAdaptor(pojoList1,getContext());
        recyclerView2.setAdapter(SavingAdaptor);

        plusActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddSaving.class));
            }
        });

        return view;
    }
    private void viewSaving() {
        Cursor cursor2 = databaseHelper.viewSavingData();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor2.moveToLast()) {
            stringBuilder.append(cursor2.getInt(1));
        }
        Money2.setText(stringBuilder);
    }
    @Override
    public void onStart() {
        super.onStart();
        viewSaving();
    }
}
