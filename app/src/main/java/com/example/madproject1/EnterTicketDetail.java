package com.example.madproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class EnterTicketDetail extends AppCompatActivity {


    TextView tv_date , price_show , tv_price , tv_time_show;
    DatePickerDialog.OnDateSetListener setListener;

    EditText et_movieName , et_userName , et_noTicket;
    Button btn_add , btn_checkout , btn_apply_price , btn_apply_time , btn_ticket;
    RadioGroup radioGroup , radioGroupTime;
    RadioButton radioButton ,radioButtonTime;
    int totPrice;
    int noOfTicket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_ticket_detail);

        radioGroup = findViewById(R.id.group_price);
        tv_price = findViewById(R.id.tv_price);
        btn_apply_price = findViewById(R.id.btn_apply_price);
        price_show = findViewById(R.id.price_show);
        btn_apply_time = findViewById(R.id.btn_apply_time);
        radioGroupTime = findViewById(R.id.group_time);
        tv_time_show = findViewById(R.id.tv_time_show);
        btn_ticket = findViewById(R.id.btn_ticket);


        btn_ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ShowTickets.class));
                finish();
            }
        });

        btn_apply_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioIdTime = radioGroupTime.getCheckedRadioButtonId();
                radioButtonTime = findViewById(radioIdTime);
                tv_time_show.setText(radioButtonTime.getText());


            }
        });

        btn_apply_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioId);
                tv_price.setText(radioButton.getText());
            }
        });

        //checkout button
        btn_checkout =  findViewById(R.id.btn_checkout);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_noTicket.getText().toString().isEmpty()){
                    et_noTicket.setError("Enter number of Tickets");
                    return;
                }
                if(price_show.getText().toString().isEmpty()){
                    price_show.setError("Apply Price");
                    return;
                }
                noOfTicket = Integer.parseInt(et_noTicket.getText().toString());
                int price = Integer.parseInt(tv_price.getText().toString());
                totPrice = totalPrice(price ,noOfTicket );
                //totPrice = noOfTicket * price;
                price_show.setText("Rs " + String.valueOf(totPrice));
            }
        });


//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.price_one:
//                         noOfTicket = Integer.parseInt(et_noTicket.getText().toString());
//
//                    case R.id.price_two:
//                        noOfTicket = Integer.parseInt(et_noTicket.getText().toString());
//
//                    case R.id.price_three:
//                        noOfTicket = Integer.parseInt(et_noTicket.getText().toString());
//
//                }
//            }
//        });

//        price_show = findViewById(R.id.price_show);
//        btn_checkout = findViewById(R.id.btn_checkout);
//        btn_checkout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                price_show.setText(totPrice);
//            }
//        });

        et_movieName = findViewById(R.id.et_movieName);
        et_userName = findViewById(R.id.et_userName);
        et_noTicket = findViewById(R.id.et_noTicket);
        btn_add = findViewById(R.id.btn_add);
        tv_date = findViewById(R.id.tv_date);

        //pay btn
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_movieName.getText().toString().isEmpty()){
                    et_movieName.setError("Enter movie Name");
                    return;
                }
                else if(et_userName.getText().toString().isEmpty()){
                    et_userName.setError("Enter theater Name");
                    return;
                }
                else if(price_show.getText().toString().isEmpty()){
                    price_show.setError("Checkout Total Price");
                }
                else{
                    insertData();
                }


            }
        });

        //Calender
        //tv_date = findViewById(R.id.tv_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        EnterTicketDetail.this , android.R.style.Theme_Holo_Light_Dialog_MinWidth , setListener , year , month , day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month +1;
                String date = day + " / " +month+ " / " +year;
                tv_date.setText(date);

            }
        };

    }

    public void checkBtntime(View v){

        int radioIdTime = radioGroupTime.getCheckedRadioButtonId();
        radioButtonTime = findViewById(radioIdTime);

        Toast.makeText(EnterTicketDetail.this , "Selected " + radioButtonTime.getText() , Toast.LENGTH_SHORT ).show();

    }

    public void checkBtn(View v){
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(EnterTicketDetail.this , "Selected " + radioButton.getText() , Toast.LENGTH_SHORT).show();

    }

    private void insertData(){
        Map<String , Object> map = new HashMap<>();
        map.put("movieName" , et_movieName.getText().toString());
        map.put("theater" , et_userName.getText().toString());
        map.put("movieDate" , tv_date.getText().toString());
        map.put("time" , tv_time_show.getText().toString());
        map.put("noOfTickets" , et_noTicket.getText().toString());
        map.put("Price" , totPrice);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure ?");
        builder.setMessage("Payment can't reversed");

        builder.setPositiveButton("Make Payment", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                FirebaseDatabase.getInstance().getReference().child("Tickets").push()
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EnterTicketDetail.this , "Payment Completed" , Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull  Exception e) {
                                Toast.makeText(EnterTicketDetail.this , "Failed" , Toast.LENGTH_SHORT).show();
                            }
                        });


            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText (EnterTicketDetail.this , "Canceled" , Toast.LENGTH_SHORT).show();
            }
        });

        builder.show();

//        FirebaseDatabase.getInstance().getReference().child("Tickets").push()
//                .setValue(map)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(EnterTicketDetail.this , "Payment Completed" , Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull  Exception e) {
//                        Toast.makeText(EnterTicketDetail.this , "Failed" , Toast.LENGTH_SHORT).show();
//                    }
//                });
    }

    protected int totalPrice(int price , int noOfticket){
        return price*noOfticket;
    }

}