package com.me.kenburnsview;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class HomeActivity extends AppCompatActivity {
    Button trips,wallet,home,cabpool;
    static Button profile;
    Fragment fragment;
    FrameLayout frameLayout;
    SharedPreferences sharedPreferences;
    static int [] pro_pic={R.drawable.fprof64,R.drawable.funkyprofgirl64,R.drawable.decentprof64,R.drawable.decentprofgirl64,R.drawable.normalprof64,R.drawable.normalprofgirl64};
    @Override
    protected void onCreate(final Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        trips=(Button) (findViewById(R.id.trips));
        wallet=(Button) (findViewById(R.id.wallet));
        frameLayout=(FrameLayout)findViewById(R.id.frameLayout);
        profile= (Button) findViewById(R.id.profile);
        home= (Button) findViewById(R.id.home);
        sharedPreferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        int pic_id=sharedPreferences.getInt("ProfilePic",0);
        Log.e("ProfilePic",pic_id+"");
        cabpool=(Button)findViewById(R.id.chat);
        //Resources resources= getResources();
       // Drawable drawable= resources.getDrawable(pro_pic[pic_id-1]);
        Drawable drawable=getResources().getDrawable(pro_pic[pic_id-1]);
       // profile.setCompoundDrawables(null,drawable,null,null);
        profile.setCompoundDrawablesWithIntrinsicBounds(0,0, 0,pro_pic[pic_id-1]);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new HomeFragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout,fragment);
                transaction.commit();
//                fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//                    @Override
//                    public void onBackStackChanged() {
//                        if(getFragmentManager().getBackStackEntryCount() == 0)
//                        {
//
//                        }
//                    }
//                });

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new ProfileFragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout,fragment);
                transaction.commit();
//                fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//                    @Override
//                    public void onBackStackChanged() {
//                        if(getFragmentManager().getBackStackEntryCount() == 0)
//                        {
//                        // Intent intent=new Intent(getBaseContext(),SplashActivity.class);
//                            startActivity(intent);
//                        }
//                    }
//                });

            }
        });

        trips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new TabFragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout,fragment);
                transaction.commit();
//                fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//                    @Override
//                    public void onBackStackChanged() {
//                        if(getFragmentManager().getBackStackEntryCount() == 0)
//                        {
//
//                        }
//                    }
//                });
            }
        });


        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment=new walletFragmet();
                FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout,fragment);
                transaction.commit();
//                fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//                    @Override
//                    public void onBackStackChanged() {
//                        if(getFragmentManager().getBackStackEntryCount() == 0)
//                        {
//
//                        }
//                    }
//                });
            }
        });
        cabpool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new CabPool_Forum();
                FragmentManager fragmentManager=getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
                transaction.replace(R.id.frameLayout,fragment);
                transaction.commit();
//                fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//                    @Override
//                    public void onBackStackChanged() {
//                        if(getFragmentManager().getBackStackEntryCount() == 0)
//                        {
//
//
//                        }
//                    }
//                });
            }
        });


    }

    @Override
    public void onBackPressed() {
        final Dialog dialog = new Dialog(HomeActivity.this,R.style.Theme_AppCompat_Light_Dialog_Alert);
        dialog.setContentView(R.layout.exit_dialogue);
        Button cancel=(Button)dialog.findViewById(R.id.cancel_dia);
        dialog.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        Button exit=(Button)dialog.findViewById(R.id.exit_dia);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // super.onBackPressed();
                finish();
            }
        });
         dialog.show();

    }

}
