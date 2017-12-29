package com.me.kenburnsview;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class CabPool_Forum extends Fragment {

    String selected;
    ListView Update;
    ArrayList<Request_pojo> requests=new ArrayList<>();
    public CabPool_Forum() {
        // Required empty public constructor
    }
SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Listen();
        View view=inflater.inflate(R.layout.fragment_cab_pool__forum, container, false);
        // Inflate the layout for this fragment
        sharedPreferences = this.getActivity().getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        final String S_name=sharedPreferences.getString("Name",null);
        Update=(ListView)view.findViewById(R.id.CabPool_msg);
        Update.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Cabpool","LOLpool");
                Intent intent = new Intent(getContext(),showRequestDet_activity.class);
                final TextView U_Name=(TextView)view.findViewById(R.id.Pro_UserName);
                //final TextView time=(TextView)view.findViewById(R.id.Pro_UserName);
                final String username_get=U_Name.getText().toString().trim();

                if(username_get.equals(S_name)) {    // should be Transport.name;
                   Log.e("Cabpool","HELOLOLOL");
                   Log.e("Cbpool",requests.size()+"");
                    intent.putExtra("Noti_Name",username_get);
                    intent.putExtra("from",requests.get(i).from);
                    intent.putExtra("to",requests.get(i).to);
                    intent.putExtra("date",requests.get(i).date);
                    intent.putExtra("seats_ava",requests.get(i).seatsAvailable);
                    startActivity(intent);
                }
                else
                {
                    Log.e("Cabpool","LOL");
                    final Dialog dialog = new Dialog(getContext(),R.style.Theme_AppCompat_Light_Dialog_Alert);
                    dialog.setContentView(R.layout.dialogue_usereq);
                    dialog.setTitle("Enter the data");
                    TextView Req_ini_name=(TextView)dialog.findViewById(R.id.ReqSetby);
                    TextView Req_ini_phno=(TextView)dialog.findViewById(R.id.ReqSetPhno);
                    final TextView Seats_Avail=(TextView)dialog.findViewById(R.id.maxSeats);
                    //final EditText mobile_infor=(EditText)dialog.findViewById(R.id.mobile_info);
                    final EditText seats_req=(EditText)dialog.findViewById(R.id.SeatsReq);
                    final EditText information=(EditText)dialog.findViewById(R.id.mobile_info_ureq);
                    Req_ini_name.setText(requests.get(i).name);
                    Req_ini_phno.setText(requests.get(i).mobile);
                    //Log.e("Cabpool",mobile_info.getText().toString()+"hello");
                    Log.e("Cabpool",requests.get(i).seatsAvailable+"");
                    Log.e("Cabpool",i+"");
                   Seats_Avail.setText(requests.get(i).seatsAvailable+"");

                    Button cancel=(Button)dialog.findViewById(R.id.dialogCancel);
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    Button Accept=(Button)dialog.findViewById(R.id.dialogButton);
                    Accept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                         //   Toast.makeText(getContext(),"Still working",Toast.LENGTH_SHORT).show();
                            int SA=Integer.valueOf(Seats_Avail.getText().toString());
                            int Asked_Seats=Integer.valueOf(seats_req.getText().toString());
                            if(SA==0)
                            {
                                seats_req.setError("no seats available ");
                            }
                            if(SA<Asked_Seats)
                            {
                                seats_req.setError("No of available seats  are less");
                            }

                            else
                            {
                                FirebaseDatabase database=FirebaseDatabase.getInstance();
                                DatabaseReference checkRef=database.getReference("Cabpool").child(username_get).child("seatsAvailable");
                                checkRef.setValue(SA-Asked_Seats);
                                DatabaseReference myRef=database.getReference("Cabpool").child(username_get).child("Requests").child(username_get);
                                myRef.child("name").setValue(S_name);
                //                Log.e("Cabpool",mobile_infor.getText().toString()+"lolol");
                               myRef.child("mobile").setValue(information.getText().toString());
                                myRef.child("required_seats").setValue(Integer.valueOf(seats_req.getText().toString()));
                                dialog.dismiss();
                                Toast.makeText(getContext(),"Successfully sent Request",Toast.LENGTH_SHORT).show();
                            }

                          //  FirebaseDatabase database=FirebaseDatabase.getInstance();
                            //DatabaseReference myRef=database.getReference("Cabpool").child()
                        }
                    });
                    dialog.show();

                }

            }
        });
        CabPool_LvAdapter adapter=new CabPool_LvAdapter(getContext(),requests);
        Update.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action

                final Dialog dialog = new Dialog(getContext(),R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.dialogue_sendreq);


                dialog.setTitle("Enter the data");

                        final EditText from = (EditText) dialog.findViewById(R.id.sendR_from);
                       final EditText to = (EditText) dialog.findViewById(R.id.sendR_to);
                       final EditText time = (EditText) dialog.findViewById(R.id.time_travel);
                       final EditText seats_availabale = (EditText) dialog.findViewById(R.id.seats_avail);
                        final Button setDate = (Button) dialog.findViewById(R.id.calander_use);
                        final Calendar myCalendar = Calendar.getInstance();
                        final EditText mobile_info=(EditText)dialog.findViewById(R.id.mobile_info);

                final DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        String myFormat = "ddMMyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                        setDate.setText(sdf.format(myCalendar.getTime()));
                         selected=sdf.format(myCalendar.getTime());
                        setDate.setText(selected);
                    }
                };
                        setDate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new DatePickerDialog(getContext(), date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                            }
                        });



                        Button cancel = (Button) dialog.findViewById(R.id.dialogCancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                        Button submit = (Button) dialog.findViewById(R.id.dialogButton);
                     submit.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             FirebaseDatabase database=FirebaseDatabase.getInstance();
                            // Log.e("Cabpool",selected+"jhii");
                           //  Log.e("Cabpool",Transport)
                             DatabaseReference myRef=database.getReference("Cabpool").child(S_name);
                             myRef.child("from").setValue(from.getText().toString().trim());
                             myRef.child("to").setValue(to.getText().toString().trim());
                             myRef.child("name").setValue(S_name);
                             myRef.child("mobile").setValue(mobile_info.getText().toString());
                             myRef.child("time").setValue(time.getText().toString().trim());
                             myRef.child("date").setValue(selected);
                             myRef.child("seatsAvailable").setValue(Integer.valueOf(seats_availabale.getText().toString().trim()));
                             dialog.dismiss();
                         }
                     });
                dialog.show();
                    }
                });

        return view;
            }

            void Listen()
            {
                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("Cabpool");

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        requests.clear();
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                           // Log.e("Cabpool",dataSnapshot.toString());
                           requests.add(dsp.getValue(Request_pojo.class));

                       }
                       for(int i=0;i<requests.size();i++)
                       {
                           Log.e("Cabpool",requests.size()+"lo");
                           Log.e("Cabpool",requests.get(i).date+"hiii");
                       }
                        //Log.e(f)
                        Update.setAdapter(new CabPool_LvAdapter(getContext(),requests));
                       // requests.clear();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }





}


