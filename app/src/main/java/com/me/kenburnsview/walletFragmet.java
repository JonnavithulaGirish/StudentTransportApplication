package com.me.kenburnsview;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class walletFragmet extends Fragment {
    Integer finalAmount;
    DatabaseReference myRef,myChildRef;
    FirebaseDatabase database;

    Button addMoney;
    public walletFragmet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View v= inflater.inflate(R.layout.fragment_wallet, container, false);
        addMoney= (Button) v.findViewById(R.id.addMoney);
        database = FirebaseDatabase.getInstance();

        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(),R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialog_wallet);
                dialog.setTitle("Enter the data");
                final EditText inputMoney=(EditText) dialog.findViewById(R.id.inputMoney);
                final Button dialogButton=(Button)dialog.findViewById(R.id.addM_btn);


                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String input=inputMoney.getText().toString();
                        if(input!=null){
                         finalAmount= Integer.parseInt(input);
                            dialog.dismiss();
                        }



                    }
                });
                dialog.show();


            }
        });




        return v;


    }

}
