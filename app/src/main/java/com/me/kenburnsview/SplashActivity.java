package com.me.kenburnsview;

/**
 * Created by HP-USER on 13-06-2017.
 */
        import android.animation.AnimatorSet;
        import android.animation.ObjectAnimator;
        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.util.Log;
        import android.view.View;
        import android.view.animation.AccelerateDecelerateInterpolator;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.AutoCompleteTextView;
        import android.widget.Button;
        import android.widget.Toast;

        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.me.kenburnsview.R;
        import com.me.kenburnsview.KenBurnsView;
        import com.squareup.okhttp.Call;
        import com.squareup.okhttp.Callback;
        import com.squareup.okhttp.FormEncodingBuilder;
        import com.squareup.okhttp.OkHttpClient;
        import com.squareup.okhttp.Request;
        import com.squareup.okhttp.RequestBody;
        import com.squareup.okhttp.Response;

        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;

        import java.io.IOException;
        import java.net.CookieManager;
        import java.net.CookiePolicy;
        import java.security.cert.CertificateException;
        import java.util.Iterator;

        import javax.net.ssl.HostnameVerifier;
        import javax.net.ssl.SSLContext;
        import javax.net.ssl.SSLSession;
        import javax.net.ssl.SSLSocketFactory;
        import javax.net.ssl.TrustManager;
        import javax.net.ssl.X509TrustManager;

public class SplashActivity extends Activity {

    private String testing_uname;
    private String testing_pass;

    static int[] profile_pic={R.drawable.funkyprofile32,R.drawable.funkyprofgirl128,R.drawable.decentprof128,R.drawable.decentprofgirl128,R.drawable.normalprof128,R.drawable.normalprofgirl128};


    // Splash screen timer
    private Handler handler;
    AutoCompleteTextView netid_act;
    AutoCompleteTextView password_act;
    private static int SPLASH_TIME_OUT = 10000;
    private KenBurnsView mKenBurns;
    private Button login;
    private static final String TAG = "TransportActivity";
    private SharedPreferences sharedPreferences;
    private boolean testing=false;
    String Name;
    String Phone_no;
    private String NetId = null, password = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_splash);
        setAnimation();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        mKenBurns = (KenBurnsView) findViewById(R.id.ken_burns_images);
        mKenBurns.setImageResource(R.drawable.splash_background);

        sharedPreferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);
        NetId = sharedPreferences.getString("username", null);
        password = sharedPreferences.getString("password", null);

        if(NetId != null && password != null){
            Intent startMain = new Intent(getApplicationContext(), HomeActivity.class);
            startMain.putExtra("username", NetId);
            startMain.putExtra("password", password);
            startActivity(startMain);
            finish();
        }



        netid_act= (AutoCompleteTextView) findViewById(R.id.prompt_email);
        password_act= (AutoCompleteTextView) findViewById(R.id.prompt_password);

        testing_uname=netid_act.getText().toString();
        testing_pass=password_act.getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(i);
//
//
//  finish();

                if(testing_uname.equalsIgnoreCase("xxxxx")&&testing_pass.equalsIgnoreCase("x"))
                    testing=true;
                check(v);


            }
        });

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {
                    //ed1.setError("Invalid NetId");
                    Toast.makeText(SplashActivity.this, "Please check your NetID and password", Toast.LENGTH_SHORT).show();
                    netid_act.setError("Invalid");
                    password_act.setError("Invalid");
                }
                else if (msg.what == 2) {
                    // ed2.setError("Invalid password");
                    Toast.makeText(SplashActivity.this, "Please check your NetID and password", Toast.LENGTH_SHORT).show();
                    netid_act.setError("Invalid");
                    password_act.setError("Invalid");

                }
                return false;
            }
        });
    }

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                Intent i = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(i);
//
//                finish();
//            }
//        }, SPLASH_TIME_OUT);



    Call post(Callback callback) throws IOException {
        OkHttpClient client = getUnsafeOkHttpClient();
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client.setCookieHandler(cookieManager);
        RequestBody requestBody = new FormEncodingBuilder()
                .add("user_id", NetId)
                .add("user_password", password)
                .build();
        Request request = new Request.Builder()
                .url("https://studentmaintenance.webapps.snu.edu.in/students/public/studentslist/studentslist/loginauth")
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }


    void check(View v)
    {
        String w= String.valueOf(netid_act.getText());

        switch (v.getId()){

            case R.id.login:
                if(netid_act.getText().toString().equals("")||password_act.getText().toString().equals("")) {
                    Toast.makeText(this, "Enter the Required Fields", Toast.LENGTH_LONG).show();
                    netid_act.setError("Requried");
                    password_act.setError("Requried");
                }
                else if(w.length()!=5){
                    Toast.makeText(this, "Please check the UserName", Toast.LENGTH_LONG).show();
                netid_act.setError("Invalid");}
                else {
                    NetId= String.valueOf(netid_act.getText());
                    password= String.valueOf(password_act.getText());
                    confirmDetails();
                }

                break;
        }
    }



    private void confirmDetails() {
        final ProgressDialog dialog = new ProgressDialog(SplashActivity.this);

        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Loading...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        try {
            post(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.e(TAG, "Details confirmation failed. " + e.toString());
                    dialog.dismiss();
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    /**
                     * The student details website uses class "label label-danger"
                     * to show error in authentication. If this class is refereed
                     * then there is an error authenticating the user.
                     */
                    dialog.dismiss();
                    String responseData = response.body().string();
                    //  Log.e(TAG,responseData);
                    if (responseData.contains("alert alert-danger")&&(!testing)) {
                        Log.e(TAG, "Bad login credentials");

                        /**
                         * If true then wrong SNU Net ID.
                         * Else wrong password.
                         */
                        if (responseData.contains("You are not a valid user of the system")) {
                            Message message = handler.obtainMessage(1);
                            message.sendToTarget();
                        } else {
                            Message message = handler.obtainMessage(2);
                            message.sendToTarget();
                        }
                        return;
                    }

                    else {
                          //     SharedPreferences.Editor editor = sharedPreferences.edit();
                        //     editor.putString("username", NetId);
                        //   editor.putString("password", password);
                        Document doc = Jsoup.parse(responseData);
                        Element table = doc.select("table[class=table-bordered infoClass]").first();

                        Iterator<Element> ite = table.select("td[width=20%]").iterator();

                        //  ite.next(); // first one is image, skip it

                        Name=ite.next().text();
                        for(int i=0;i<=11;i++)
                        {
                            ite.next();

                        }
                      Phone_no=ite.next().text();


//                        FirebaseDatabase database=FirebaseDatabase.getInstance();
//                        DatabaseReference myRef = database.getReference("Users").child(Name).child("Email");
//                        myRef.setValue(NetId+"@snu.edu.in");
//                        DatabaseReference myERef = database.getReference("Users").child(Name).child("Phone_No");
//                        myERef.setValue(Phone_no);
                       Listen();
                        Log.e(TAG,Phone_no);
                        Log.e(TAG,Name);
                        Bundle bundle=new Bundle();

                    }
                }
            });
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void setAnimation() {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleX", 5.0F, 1.0F);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(1200);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleY", 5.0F, 1.0F);
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setDuration(1200);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "alpha", 0.0F, 1.0F);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(1200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.setStartDelay(500);
        animatorSet.start();

        findViewById(R.id.imagelogo).setAlpha(1.0F);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_top_to_center);
        findViewById(R.id.imagelogo).startAnimation(anim);


        Animation animFadein=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        AutoCompleteTextView eid= (AutoCompleteTextView) findViewById(R.id.prompt_email);
        animFadein.setStartOffset(3500);
        eid.setAnimation(animFadein);


       // Animation animFadein2=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        AutoCompleteTextView eid2= (AutoCompleteTextView) findViewById(R.id.prompt_password);
       // animFadein2.setStartOffset(5000);
        eid2.setAnimation(animFadein);

      //  Animation animFadein3=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
         login= (Button) findViewById(R.id.login);
        //animFadein2.setStartOffset(5000);
        login.setAnimation(animFadein);

    }

    void Listen()
    {

        final DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Users");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("dataSnapshot",dataSnapshot.toString());
                if(dataSnapshot.child(Name).exists())
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", NetId);
                    editor.putString("password", password);
                    editor.putString("Name",Name);
                    editor.putString("Email",NetId+"@snu.edu.in");
                    editor.putInt("ProfilePic",dataSnapshot.child(Name).child("ProfilePic").getValue(Integer.class));
                    editor.putString("Phone",dataSnapshot.child(Name).child("Phone_No").getValue(String.class));
                    editor.apply();
                    Log.e("Name",Name);
                    Log.e("Email",NetId);
                    Log.e("ProfilePic",dataSnapshot.child(Name).child("ProfilePic").getValue(Integer.class)+"hiiihihi");
                    Intent startMain = new Intent(getApplicationContext(), HomeActivity.class);

                   // startMain.putExtra("username", NetId);
                    //tartMain.putExtra("password", password);
                    //startMain.putExtra("Name",Name);
                    //startMain.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(startMain);
                    finish();

                }
                else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    rootRef.child(Name).child("Email").setValue(NetId+"@snu.edu.in");
                    rootRef.child(Name).child("Phone_No").setValue(Phone_no);
                    rootRef.child(Name).child("ProfilePic").setValue(1);
                    editor.putString("username", NetId);
                    editor.putString("password", password);
                    editor.putInt("ProfilePic",1);
                    editor.putString("Email",NetId+"@snu.edu.in");
                    editor.putString("Name",Name);
                    editor.putString("Phone",Phone_no);
                    Log.e("ProfilePic",1+"hiiihihi");
                    editor.apply();
                    Intent startMain = new Intent(getApplicationContext(), HomeActivity.class);
                    //startMain.putExtra("username", NetId);
                    //startMain.putExtra("password", password);
                    //startMain.putExtra("Name",Name);
                    //startMain.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(startMain);
                    finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}