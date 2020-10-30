package com.sis.expensiveaapp.BottomSheets;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.sis.expensiveaapp.AddActivities.AddIncome;
import com.sis.expensiveaapp.UpdateActivities.IncomeUpdate;
import com.sis.expensiveaapp.R;

public class
IncomeSheetDialogue extends BottomSheetDialogFragment {
    Button incomeAdd,incomeUpdate;
    ImageView finish;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.incomesheetlayout, container, false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
        private void init(View view) {
            incomeAdd = view.findViewById(R.id.addincome);
            incomeUpdate = view.findViewById(R.id.editIncome);
            finish=view.findViewById(R.id.finish);
            incomeAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), AddIncome.class);
                    Pair[] pairs=new Pair[1];
                    pairs[0] = new Pair<View,String>(incomeAdd,"transition_addincome");
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(getActivity(),pairs);
                        startActivity(i,options.toBundle());
                        dismiss();
                    }
                    else {
                        startActivity(i);

                    }

                }
            });

            incomeUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), IncomeUpdate.class);
                    startActivity(i);
                }
            });

            finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });

        }
}
