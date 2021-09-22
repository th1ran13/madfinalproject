package com.example.madproject1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

import java.util.HashMap;
import java.util.Map;


public class TicketAdapter extends FirebaseRecyclerAdapter<MovieTicketModel , TicketAdapter.MyViewHolder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public TicketAdapter(@NonNull @NotNull FirebaseRecyclerOptions<MovieTicketModel> options) {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, final int position, @NonNull @NotNull MovieTicketModel model) {

        holder.movieName.setText(model.getMovieName());
        holder.time.setText(model.getTime());
        holder.movieDate.setText(model.getMovieDate());

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.time.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setExpanded(true , 1200)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText time = view.findViewById(R.id.txt_time);
                EditText movieDate = view.findViewById(R.id.txt_date);

                Button btnupdate = view.findViewById(R.id.btn_update);

                time.setText(model.getTime());
                movieDate.setText(model.getMovieDate());

                dialogPlus.show();

                btnupdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String , Object> map = new HashMap<>();
                        map.put("time" , time.getText().toString());
                        map.put("movieDate" , movieDate.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Tickets")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.time.getContext() , "Updated Successfully" , Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull @NotNull Exception e) {
                                        Toast.makeText(holder.time.getContext() , "Failed to Update" , Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.movieName.getContext());
                builder.setTitle("Are you sure ?");
                builder.setMessage("Deleted data can't be undo");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Tickets")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText (holder.time.getContext() , "Canceled" , Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ticket , parent , false);
        return new MyViewHolder(view);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        //ImageView qrImg;

        TextView movieName , time , movieDate;

        Button btn_edit , btn_delete;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //qrImg = (ImageView)itemView.findViewById(R.id.qrImg);
            movieName = (TextView)itemView.findViewById(R.id.m_name);
            time = (TextView)itemView.findViewById(R.id.m_time);
            movieDate = (TextView)itemView.findViewById(R.id.m_date);

            btn_edit = (Button)itemView.findViewById(R.id.btn_edit1);
           btn_delete = (Button)itemView.findViewById(R.id.btn_delete1);
        }
    }


}
