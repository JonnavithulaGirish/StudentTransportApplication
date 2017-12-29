package com.me.kenburnsview;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment  {

int Pro_number=1;
    SharedPreferences sharedPreferences;
    Dialog dialog;
    EditText User_promobile;
    String P_name,P_mobile;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       sharedPreferences = this.getActivity().getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
       Pro_number=sharedPreferences.getInt("ProfilePic",1);




        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        final CircleImageView setPic=(CircleImageView)view.findViewById(R.id.SetPic);
        TextView name=(TextView)view.findViewById(R.id.User_proname);
        TextView email=(TextView)view.findViewById(R.id.User_proMail);
        final EditText mobile=(EditText) view.findViewById(R.id.User_promobile);
        setPic.setImageResource(SplashActivity.profile_pic[Pro_number]);
        User_promobile= (EditText) view.findViewById(R.id.User_promobile);
        User_promobile.setClickable(false);
        Button logout=(Button)view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext(),R.style.Theme_AppCompat_Light_Dialog_Alert);
                dialog.setContentView(R.layout.exit_dialogue);
                TextView text=(TextView)dialog.findViewById(R.id.text_dia);
                text.setText("Do you really whish to Logout?");
                Button cancel=(Button)dialog.findViewById(R.id.cancel_dia);
                dialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                Button exit=(Button)dialog.findViewById(R.id.exit_dia);
                exit.setText("Logout");
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // super.onBackPressed();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        getActivity().finish();
                        Intent intent=new Intent(getContext(),SplashActivity.class);
                        startActivity(intent);

                    }
                });
                dialog.show();
            }
        });



        final Button editbtn= (Button) view.findViewById(R.id.edit_btn);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_promobile.setClickable(true);
            }
        });

       Button helpbtn= (Button) view.findViewById(R.id.Help);
       helpbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(getContext(),Help_Activity.class);
               startActivity(intent);
           }
       });
        Button button=(Button)view.findViewById(R.id.save);

        setPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });

        dialog = new Dialog(getContext(),R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.propic_dialog);
        final CircleImageView propic1=(CircleImageView)dialog.findViewById(R.id.Pro_Pic1);
        final CircleImageView propic2=(CircleImageView)dialog.findViewById(R.id.Pro_Pic2);
        final CircleImageView propic3=(CircleImageView)dialog.findViewById(R.id.Pro_Pic3);
        final CircleImageView propic4=(CircleImageView)dialog.findViewById(R.id.Pro_Pic4);
        final CircleImageView propic5=(CircleImageView)dialog.findViewById(R.id.Pro_Pic5);
        final CircleImageView propic6=(CircleImageView)dialog.findViewById(R.id.Pro_Pic6);
        propic1.setBackgroundColor(Color.parseColor("#FFFFFF"));
        propic2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        propic3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        propic4.setBackgroundColor(Color.parseColor("#FFFFFF"));
        propic5.setBackgroundColor(Color.parseColor("#FFFFFF"));
        propic6.setBackgroundColor(Color.parseColor("#FFFFFF"));


        Button Submit=(Button)dialog.findViewById(R.id.Set_Propic);
        Button cancel=(Button)dialog.findViewById(R.id.cancel_propic);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPic.setImageResource(SplashActivity.profile_pic[Pro_number-1]);
                 SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("Profilepic",Pro_number);
                editor.apply();
                dialog.dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        propic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propic2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Pro_number=1;
                propic1.setBackgroundColor(Color.parseColor("#FFF1E4E4"));
            }
        });
        propic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propic1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Pro_number=2;
                propic2.setBackgroundColor(Color.parseColor("#FFF1E4E4"));
            }
        });
        propic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propic2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Pro_number=3;
                propic3.setBackgroundColor(Color.parseColor("#FFF1E4E4"));
            }
        });
        propic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propic2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Pro_number=4;
                propic4.setBackgroundColor(Color.parseColor("#FFF1E4E4"));
            }
        });
        propic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propic2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Pro_number=5;
                propic5.setBackgroundColor(Color.parseColor("#FFF1E4E4"));
            }
        });
        propic6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propic2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                propic1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Pro_number=6;
                propic6.setBackgroundColor(Color.parseColor("#FFF1E4E4"));
            }
        });

        final SharedPreferences.Editor editor = sharedPreferences.edit();
        setPic.setImageResource(SplashActivity.profile_pic[Pro_number-1]);
         P_name=sharedPreferences.getString("Name",null);
        Log.e("Name;;;",P_name);
        Log.e("Name;;;",P_name);
        String P_mail=sharedPreferences.getString("Email",null);
      P_mobile=sharedPreferences.getString("Phone",null);
        Log.e("mobile;;;",P_mobile);
        name.setText(P_name);
        email.setText(P_mail);
        mobile.setText(P_mobile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  User_promobile.setClickable(false);
                FirebaseDatabase database= FirebaseDatabase.getInstance();
                DatabaseReference myRef=database.getReference("Users").child(P_name);
                myRef.child("Phone_No").setValue(mobile.getText().toString().trim());
                myRef.child("ProfilePic").setValue(Pro_number);
                editor.putInt("ProfilePic",Pro_number);
                editor.putString("Phone",mobile.getText().toString().trim());
                editor.apply();
                HomeActivity.profile.setCompoundDrawablesWithIntrinsicBounds(0,0, 0,HomeActivity.pro_pic[Pro_number-1]);
                Toast.makeText(getContext(), "Your Details have been succesfully edited ", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}

