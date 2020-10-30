package com.sis.expensiveaapp.TablayoutActivivities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sis.expensiveaapp.AddActivities.AddIncome;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper;
import com.sis.expensiveaapp.Model.Pojo;
import com.sis.expensiveaapp.R;

import java.util.ArrayList;
import java.util.List;

public class TabIncome extends Fragment {
    RecyclerView recyclerView;
    List<Pojo> pojoList;
    DatabaseHelper databaseHelper;
    IncomeAdaptor incomeAdaptor;
    TextView Money1;
  //  SwipeLayout swipeLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);
        recyclerView = view.findViewById(R.id.recylerview);
        Money1 = view.findViewById(R.id.money2);
//        swipeLayout=view.findViewById(R.id.swipe_layout);
//        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
//        swipeLayout.addDrag(SwipeLayout.DragEdge.Left,swipeLayout.findViewById(R.id.linear));
//        swipeLayout.addDrag(SwipeLayout.DragEdge.Left,swipeLayout.findViewById(R.id.linea2));

        ImageView plusActivity = view.findViewById(R.id.plusActivity);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(mLayoutManager);
        pojoList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getActivity());
        pojoList = databaseHelper.ViewIncomwData();
        incomeAdaptor = new IncomeAdaptor(pojoList, getContext());
        recyclerView.setAdapter(incomeAdaptor);

        plusActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddIncome.class));
            }
        });

        return view;
    }

    private void viewIncome() {
        Cursor cursor1 = databaseHelper.viewIncomeData();
        StringBuilder stringBuilder = new StringBuilder();
        if (cursor1.moveToLast()) {
            stringBuilder.append(cursor1.getInt(1));
        }
        Money1.setText(stringBuilder);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewIncome();
    }


}




