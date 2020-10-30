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
import com.sis.expensiveaapp.AddActivities.AddSaving;
import com.sis.expensiveaapp.R;

public class SavingSheetDialogue extends BottomSheetDialogFragment {
    Button SavingAdd,SavingEdit;
    ImageView finish;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.savingsheetlayout,container,false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
    private void init(View view) {
        SavingAdd=view.findViewById(R.id.addsaving);
        SavingEdit=view.findViewById(R.id.addsaving);
        finish=view.findViewById(R.id.finish2);
        SavingAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddSaving.class);
                Pair[] pairs=new Pair[1];
                pairs[0] = new Pair<View,String>(SavingAdd,"transition_addsaving");
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
        SavingEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddSaving .class);
                startActivity(intent);
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
