package com.me.kenburnsview;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by J Girish on 25-11-2017.
 */

public class CabPool_LvAdapter extends BaseAdapter {

    Context context;
    ArrayList<Request_pojo>requests;

    public CabPool_LvAdapter(Context context, ArrayList<Request_pojo> requests)
    {
        this.context=context;
        this.requests=requests;
    }
    @Override
    public int getCount() {
        return requests.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myview = LayoutInflater.from(context).inflate(R.layout.cp_lv_layout, null);
        LinearLayout notification=(LinearLayout)myview.findViewById(R.id.Cp_Notifiaction);
        final ImageView pro_pic=(ImageView)myview.findViewById(R.id.Pro_Pic);
        TextView u_name=(TextView)myview.findViewById(R.id.Pro_UserName);
        TextView Notification_content=(TextView)myview.findViewById(R.id.Notification_content);
       // TextView Notification_time=(TextView)myview.findViewById(R.id.Notification_time);
        TextView date=(TextView)myview.findViewById(R.id.date_req);
        TextView time=(TextView)myview.findViewById(R.id.time_req);
        u_name.setText(requests.get(i).name);
        Notification_content.setText("Has Initiated cabpool from "+requests.get(i).from+" to "+requests.get(i).to);
        date.setText(requests.get(i).date);
        time.setText(requests.get(i).time);

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=database.getReference().child("Users").child(requests.get(i).name).child("ProfilePic");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int x=dataSnapshot.getValue(Integer.class);
                pro_pic.setImageResource(SplashActivity.profile_pic[x-1]);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return myview;
    }


    }

