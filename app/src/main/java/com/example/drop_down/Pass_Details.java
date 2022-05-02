package com.example.drop_down;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Pass_Details extends AppCompatActivity {
  TextView tv, tv15, tv17;
  boolean[] selectday;
  ArrayList<Integer> daylist = new ArrayList<>();
  String[] dayArray = {"Mon", "Tue", "Wed", "Thu" , "Fri" , "Sat" , "Sun" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_details);
        getSupportActionBar().hide();


        tv = findViewById(R.id.Tv);
        tv15 = findViewById(R.id.tV15);
        tv17 = findViewById(R.id.tV17);
        tv17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pass_Details.this,Maps_Activity.class);
                startActivity(intent);
            }
        });

        selectday = new boolean[dayArray.length];

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Pass_Details.this);
                builder.setTitle("Select Day");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(dayArray, selectday, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i, boolean b) {
                        if(b){
                            //when checkbox is selected
                            //add position in day list
                            daylist.add(i);
                            Collections.sort(daylist);
                        }else{
                            daylist.remove(i);
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for(int j =0; j<daylist.size(); j++){
                            stringBuilder.append(dayArray[daylist.get(j)]);
                            if(j!= daylist.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        tv.setText(stringBuilder.toString());

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for(int j=0; j<selectday.length; j++ ){
                            selectday[j] = false;
                            daylist.clear();
                            tv.setText("");
                        }
                    }
                });
                builder.show();
            }
        });

    }
}