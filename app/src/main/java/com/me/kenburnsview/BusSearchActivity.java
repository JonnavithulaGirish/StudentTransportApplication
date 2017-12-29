package com.me.kenburnsview;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BusSearchActivity extends AppCompatActivity {

    String[] places = {"SNU", "Shiv Nadar University", "Noida Sector16", "Sector16"};
    String SOURCE_PLACE, DESTINATON_PLACE;
    Button revBtn;
    AutoCompleteTextView actvS;
    AutoCompleteTextView actvD;
    EditText dept_date;
    EditText return_date;
    boolean isFirstTimeS = true;
    boolean isFirstTimeD = true;
    SimpleDateFormat sdf;
    SimpleDateFormat sdfdatabase;
    DatePickerDialog dpd;
    DatePickerDialog dpd2;
    String deptDateDatabase;
    String returnDateDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_search);


        return_date= (EditText) findViewById(R.id.returnDate_txt);
        dept_date= (EditText) findViewById(R.id.deptDate_etxt);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, places);
        //Getting the instance of AutoCompleteTextView
        actvS = (AutoCompleteTextView) findViewById(R.id.source_etxt);
        actvS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFirstTimeS) actvS.setText("");
            }
        });
        actvS.setThreshold(1);//will start working from first character
        actvS.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

        actvD = (AutoCompleteTextView) findViewById(R.id.destination_etxt);
        actvD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFirstTimeD) actvD.setText("");
            }
        });
        actvD.setThreshold(1);//will start working from first character
        actvD.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView

        actvS.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SOURCE_PLACE = actvS.getText().toString();
                Log.d("mytag", "selected item= " + SOURCE_PLACE);

            }
        });

        actvD.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DESTINATON_PLACE = actvD.getText().toString();
                Log.d("mytag", "selected item= " + DESTINATON_PLACE);

            }
        });



        final Calendar myCalendar= Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd-MMM-yy";
                String databaseformat="ddMMyy";
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                sdfdatabase=new SimpleDateFormat(databaseformat, Locale.US);
                deptDateDatabase=sdfdatabase.format(myCalendar.getTime());
                dept_date.setText(sdf.format(myCalendar.getTime()));
              //  selected=sdf.format(myCalendar.getTime());
              //  customAdapter=new Custom_Adapter(getApplicationContext(),bus);
                //  for(int i=0;i<dates.size();i++){
                //    if(sdf.format(myCalendar.getTime())==dates.get(i).Data);{
                //   customAdapter=new Custom_Adapter(getApplicationContext(),dates.get(i).BusArrayList);
                //busList.setAdapter(customAdapter);
                //}
                //}
              //  setDate.setText("Edit Date");
              //  myChildRef=database.getReference(selected);
               // listen();
            }
        };
        dept_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long today= Calendar.getInstance().getTimeInMillis();
               dpd= new DatePickerDialog(BusSearchActivity.this, date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));

               dpd.getDatePicker().setMinDate(today);
               dpd.show();
            }
        });

        final Calendar myCalendar2= Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date2 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, month);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd-MMM-yy";
                String databaseformat="ddMMyy";
               SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
               SimpleDateFormat sdfdatabase=new SimpleDateFormat(databaseformat, Locale.US);
                returnDateDatabase=sdfdatabase.format(myCalendar2.getTime());
                return_date.setText(sdf.format(myCalendar2.getTime()));

            }
        };
        return_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long today= Calendar.getInstance().getTimeInMillis();
                dpd2= new DatePickerDialog(BusSearchActivity.this, date2,myCalendar2.get(Calendar.YEAR),myCalendar2.get(Calendar.MONTH),myCalendar2.get(Calendar.DAY_OF_MONTH));

                dpd2.getDatePicker().setMinDate(today);
                dpd2.show();
            }
        });


    }





    public void startRotatingImage(View view) {

        revBtn = (Button) findViewById(R.id.revPlaces_btn);

        Animation startRotateAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.android_rotate_animation);

        revBtn.startAnimation(startRotateAnimation);

        SOURCE_PLACE = actvS.getText().toString();
        DESTINATON_PLACE = actvD.getText().toString();


        Animation animFadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        Animation animFadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        animFadeout.setStartOffset(1500);

        actvS.setAnimation(animFadeout);
        actvD.setAnimation(animFadeout);

        actvS.setVisibility(View.INVISIBLE);
        actvS.setText(DESTINATON_PLACE);

        actvD.setVisibility(View.INVISIBLE);
        actvD.setText(SOURCE_PLACE);

        actvS.setVisibility(View.VISIBLE);
        actvS.startAnimation(animFadein);

        actvD.setVisibility(View.VISIBLE);
        actvD.startAnimation(animFadein);


    }

    public void submitBtn(View view) {

        Button submit= (Button) findViewById(R.id.submit_btn);
        SOURCE_PLACE=actvS.getText().toString();
        DESTINATON_PLACE=actvD.getText().toString();

        if(SOURCE_PLACE==null||SOURCE_PLACE.equals("")||SOURCE_PLACE.equals(" ")||DESTINATON_PLACE==null||DESTINATON_PLACE.equals("")||DESTINATON_PLACE.equals(" "))
        {
            Toast.makeText(BusSearchActivity.this,"Please enter valid Source and Destination Values", Toast.LENGTH_SHORT).show();
            actvS.setError("Invalid");
        }
       else if(deptDateDatabase==null||deptDateDatabase.equals("")||deptDateDatabase.equals(" "))
        {
            Toast.makeText(BusSearchActivity.this,"Please select valid dates", Toast.LENGTH_SHORT).show();
            dept_date.setError("Invalid");
            return_date.setError("Invalid");

        }
       else if(SOURCE_PLACE.equalsIgnoreCase(DESTINATON_PLACE)){
            Toast.makeText(BusSearchActivity.this,"Source and Destination can't be same.", Toast.LENGTH_SHORT).show();
            actvD.setError("Invalid");
            actvS.setError("Invalid");
        }
        else
        {
            boolean isTwoWay=true;
            if((returnDateDatabase==null)||returnDateDatabase.equalsIgnoreCase("")||returnDateDatabase.equalsIgnoreCase(" "))
            {
                isTwoWay=false;
            }
            Intent nextintent=new Intent(BusSearchActivity.this,BusSelectionActivity.class);
            Bundle bundle=new Bundle();
            bundle.putString("sourceAddress",SOURCE_PLACE);
            bundle.putString("destinationAddress",DESTINATON_PLACE);
            bundle.putString("deptDate",deptDateDatabase);
            bundle.putString("returnDate",returnDateDatabase);
            bundle.putBoolean("isTwoWay",isTwoWay);
            bundle.putBoolean("isFirstTime",true);
            nextintent.putExtra("mybundle",bundle);
            startActivity(nextintent);
        }

    }
}