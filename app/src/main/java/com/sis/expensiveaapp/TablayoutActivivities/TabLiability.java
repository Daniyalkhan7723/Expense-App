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

import com.sis.expensiveaapp.AddActivities.AddLiability;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper2;
import com.sis.expensiveaapp.Model.Pojo1;
import com.sis.expensiveaapp.R;

import java.util.ArrayList;
import java.util.List;

public class TabLiability extends Fragment {
    RecyclerView recyclerView3;
    List<Pojo1> pojoList1;
    DatabaseHelper2 databaseHelper2;
    liabilityAdaptor laAdaptor;
    TextView Money4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liability, container, false);
        recyclerView3 = view.findViewById(R.id.recylerview3);

        ImageView plusActivity=view.findViewById(R.id.plusActivity);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        Money4=view.findViewById(R.id.money2);
        recyclerView3.setLayoutManager(mLayoutManager);
        pojoList1 = new ArrayList<>();

        databaseHelper2 = new DatabaseHelper2(getActivity());
        pojoList1 = databaseHelper2.ViewDataLiability();
        laAdaptor = new liabilityAdaptor(pojoList1,getContext());
        recyclerView3.setAdapter(laAdaptor);

        plusActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddLiability.class));
            }
        });

        return view;
    }
    private void viewLiability() {
        Cursor cursor3 = databaseHelper2.viewLiabilityData();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor3.moveToLast()) {
            stringBuilder.append(cursor3.getInt(1));
        }
        Money4.setText(stringBuilder);
    }
    @Override
    public void onStart() {
        super.onStart();
        viewLiability();
    }
}
