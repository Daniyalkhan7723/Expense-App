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

import com.sis.expensiveaapp.AddActivities.AddAssets;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper3;
import com.sis.expensiveaapp.Model.Pojo1;
import com.sis.expensiveaapp.R;

import java.util.ArrayList;
import java.util.List;

public class TabAssets extends Fragment {
    RecyclerView recyclerView4;
    List<Pojo1> pojoList1;
    DatabaseHelper3 databaseHelper3;
    liabilityAdaptor assetAdaptor;
    TextView Money3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liability, container, false);
        recyclerView4 = view.findViewById(R.id.recylerview3);
        Money3=view.findViewById(R.id.money2);
        ImageView plusActivity=view.findViewById(R.id.plusActivity);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView4.setLayoutManager(mLayoutManager);
        pojoList1 = new ArrayList<>();
        databaseHelper3 = new DatabaseHelper3(getActivity());
        pojoList1 = databaseHelper3.ViewDataLiability();
        assetAdaptor = new liabilityAdaptor(pojoList1,getContext());
        recyclerView4.setAdapter(assetAdaptor);

        plusActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddAssets.class));
            }
        });
        return view;
    }
    private void viewAssets() {
        Cursor cursor4 = databaseHelper3.viewAssetsData();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor4.moveToLast()) {
            stringBuilder.append(cursor4.getInt(1));
        }
        Money3.setText(stringBuilder);
    }
    public void onStart() {
        super.onStart();
        viewAssets();
    }
}
