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
import com.sis.expensiveaapp.UpdateActivities.IncomeUpdate;
import com.sis.expensiveaapp.DATABSE.DatabaseHelper;
import com.sis.expensiveaapp.Model.Pojo;
import com.sis.expensiveaapp.R;

import java.util.List;

public class IncomeAdaptor extends RecyclerView.Adapter<IncomeAdaptor.myViewHolder> {
    List<Pojo> pojoList;
    Context context;


    public IncomeAdaptor(List<Pojo> pojoList, Context context) {
        this.pojoList = pojoList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipelayout, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Pojo p =pojoList.get(position);
        holder.IncomeText.setText(Integer.toString(p.getAmount()));
        holder.Description.setText(p.getDescription());
        holder.Date.setText(p.getDate());
        holder.swipeLayout.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setMessage("Are You sure to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHelper databaseHelper=new DatabaseHelper(context);
                        long result = databaseHelper.deleteIncome(p.getId());
                        if (result!=-1) {
                            Toast.makeText(context, "Data is Deleted", Toast.LENGTH_SHORT).show();
                            pojoList.remove(p);
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
        holder.swipeLayout.findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, IncomeUpdate.class);
                intent.putExtra("Expense",p);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pojoList.size();
    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView IncomeText, Description, Date;
        SwipeLayout swipeLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            IncomeText = itemView.findViewById(R.id.text1);
            Description = itemView.findViewById(R.id.description);
            Date = itemView.findViewById(R.id.text3);
            swipeLayout = itemView.findViewById(R.id.swipe_layout);
            swipeLayout.addDrag(SwipeLayout.DragEdge.Right, swipeLayout.findViewById(R.id.linear));
            swipeLayout.addDrag(SwipeLayout.DragEdge.Left, swipeLayout.findViewById(R.id.linea2));
//            Delete();
        }

//        private void Delete() {
//
//
//        }
//    }
//
    }
}
