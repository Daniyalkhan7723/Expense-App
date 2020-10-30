package com.sis.expensiveaapp.TablayoutActivivities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.daimajia.swipe.SwipeLayout;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper2;
import com.sis.expensiveaapp.Model.Pojo;
import com.sis.expensiveaapp.Model.Pojo1;
import com.sis.expensiveaapp.R;
import com.sis.expensiveaapp.UpdateActivities.IncomeUpdate;
import com.sis.expensiveaapp.UpdateActivities.LiabilityUpdates;

import java.util.List;

public class liabilityAdaptor extends RecyclerView.Adapter<liabilityAdaptor.myViewHolder> {
    List<Pojo1> List1;
    Context context;

    public liabilityAdaptor(List<Pojo1> list1, Context context) {
        List1 = list1;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview3,parent,false);
        return new myViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Pojo1 p1 =List1.get(position);
       holder.IncomeText.setText(Integer.toString(p1.getAmount()));
       holder.Description.setText(p1.getDescription());
        holder.Date.setText(p1.getDate());

        holder.swipeLayout1.findViewById(R.id.deleting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
            }
        });
        holder.swipeLayout1.findViewById(R.id.deleting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Are You sure to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper2 databaseHelper2=new DatabaseHelper2(context);
                        int result = databaseHelper2.deleteLiability(p1.getId());
                        if (result > 0) {
                            Toast.makeText(context, "Data is Deleted", Toast.LENGTH_SHORT).show();
                            List1.remove(p1);
//                            that show data is changed or refreshed in array
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "SomeThingWrong", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builder.setNegativeButton("No",null);
                builder.show();

            }
        });
        holder.swipeLayout1.findViewById(R.id.updating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent=new Intent(context, LiabilityUpdates.class);
                intent.putExtra("Expenses",p1);
                context.startActivity(intent);
            }
        });


    }
    @Override
    public int getItemCount() {
        return List1.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView IncomeText,Description,Date;
        SwipeLayout swipeLayout1;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            IncomeText=itemView.findViewById(R.id.textview10);
            Description=itemView.findViewById(R.id.newdescription);
            Date=itemView.findViewById(R.id.datin);
            swipeLayout1=itemView.findViewById(R.id.swipe_layout1);
            swipeLayout1.addDrag(SwipeLayout.DragEdge.Right,swipeLayout1.findViewById(R.id.linear3));
            swipeLayout1.addDrag(SwipeLayout.DragEdge.Left,swipeLayout1.findViewById(R.id.linea4));



        }
    }
}
