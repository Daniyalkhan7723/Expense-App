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
import com.sis.expensiveaapp.AddActivities.AddLiability;
import com.sis.expensiveaapp.R;

public class LiabilitySheetDialogue extends BottomSheetDialogFragment {
    Button LiaAdd, LiaUpdate;
    ImageView finish;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.liabilitysheetlayout,container,false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
    private void init(View view) {
        LiaAdd = view.findViewById(R.id.addliability);
        LiaUpdate = view.findViewById(R.id.editliability);
        finish=view.findViewById(R.id.finish);
        LiaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddLiability.class);
                Pair[] pairs=new Pair[1];
                pairs[0] = new Pair<View,String>(LiaAdd,"transition_addincome");
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

        LiaUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),AddLiability.class);
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
