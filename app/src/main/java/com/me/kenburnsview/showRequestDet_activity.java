package com.me.kenburnsview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class showRequestDet_activity extends AppCompatActivity {
    FirebaseDatabase  database;
    DatabaseReference myRef;
    ListView passDet;
    ArrayList<request_check_pojo> requests=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database=FirebaseDatabase.getInstance();
        Intent intent=getIntent();
        String U_name=intent.getStringExtra("Noti_Name");
        String u_from=intent.getStringExtra("from");
        String u_to=intent.getStringExtra("to");
        String u_date=intent.getStringExtra("date");
        int u_seats=intent.getIntExtra("seats_ava",10000);
        myRef=database.getReference("Cabpool").child(U_name).child("Requests");
        Listen();

        setContentView(R.layout.activity_show_request_det);
        TextView name=(TextView)findViewById(R.id.setUname_addreq);
        name.setText("Hello "+U_name+",");
        TextView from=(TextView)findViewById(R.id.from_addReq);
        TextView to=(TextView)findViewById(R.id.to_addReq);
        TextView  date=(TextView)findViewById(R.id.date_addReq);
        TextView  seats=(TextView)findViewById(R.id.seatsFilled_addReq);
        from.setText(u_from);
        to.setText(u_to);
        date.setText(u_date);
        seats.setText(u_seats+"");



        passDet=(ListView)findViewById(R.id.PassDet_addreq);
        RDet_passDet_adapter adapter=new RDet_passDet_adapter(this,requests);
        passDet.setAdapter(adapter);
    }

    void Listen()
    {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                requests.clear();
               for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    //Log.e("shoeReq_det",dataSnapshot.toString());
                    requests.add(dsp.getValue(request_check_pojo.class));
               }
               passDet.setAdapter(new RDet_passDet_adapter(getBaseContext(),requests));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
