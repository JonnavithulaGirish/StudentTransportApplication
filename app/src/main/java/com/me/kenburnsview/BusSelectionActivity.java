package com.me.kenburnsview;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class BusSelectionActivity extends AppCompatActivity {
        Bundle bundle;
    String date;
    LinearLayout mylinearlt;
        DatabaseReference myRef,myChildRef;
    ListView bus_list;
    FirebaseDatabase database;
    ArrayList<Bus_Pojo> buses=new ArrayList<Bus_Pojo>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_selection);


        mylinearlt= (LinearLayout) findViewById(R.id.mylinearlt);
         bundle=getIntent().getBundleExtra("mybundle");
         date=bundle.getString("deptDate");
        Log.d("mytag:",date);
        bus_list=(ListView)findViewById(R.id.busList);

      //  bus_list.setDescendantFocusability(ListView.FOCUS_BLOCK_DESCENDANTS);
        FirebaseApp.initializeApp(this);
        database = FirebaseDatabase.getInstance();

        myChildRef=database.getReference().child(date);
        myChildRef.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                buses.clear();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    buses.add(dsp.getValue(Bus_Pojo.class));
                }

                Custom_Bus_Adapter customAdapterBusList=new Custom_Bus_Adapter(getApplicationContext(),buses);
                bus_list.setAdapter(customAdapterBusList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Log.e("mytag", "onItemClick: " );



        bus_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Log.e("mytag", "onItemClick: " );
                final String busPlate;
                        TextView plate= (TextView) view.findViewById(R.id.plate_no);
                busPlate=plate.getText()+"";
                 final int seatsLeft=buses.get(i).available;
                Button bookbtn= (Button) view.findViewById(R.id.book_btn);
                bookbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(seatsLeft<=0){

                            Snackbar snackbar= Snackbar.make(mylinearlt,"No Seats Available..!", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                        else
                        {
                            Intent intent=new Intent(BusSelectionActivity.this,BusSearchActivity.class);
                                bundle.putString("bus_plate",busPlate);
                                intent.putExtra("mybundle",bundle);
                                startActivity(intent);
                        }
                    }
                });
            }
        });




    }
}
