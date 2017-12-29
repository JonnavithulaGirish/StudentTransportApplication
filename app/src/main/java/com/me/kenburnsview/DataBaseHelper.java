package com.me.kenburnsview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mukund on 11/26/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "StudentDatabase";
    private static final String TABLE_NAME = "TripDetails";
    private static final String TRANSACTION_TABLE_NAME = "PaymentDetails";


    private static final String DATE = "date";
    private static final String STUDENT_ID = "id";
    private static final String STUDENT_NAME = "name";
    private static final String DESTINATION = "destination";
    private static final String SOURCE= "source";
    private static final String STATUS= "status";
    private static final String BUSNO = "busno";
    private static final String ISTWOWAY = "istwoway";

    private static final String PAYMENT_DATE = "PaymentDate";
    private static final String PAYMENT_ID = "PaymentId";
    private static final String FROM= "sender";
    private static final String TO= "receiver";
    private static final String AMOUNT= "amount";
    private static final String PAYMENT_STATUS= "PaymentStatus";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        if(context==null)
            Log.d("ttttttttttttt", "DataBaseHelper: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + DATE + " TEXT ,"
                + STUDENT_ID + " TEXT ,"
                + STUDENT_NAME+ " TEXT,"
                + DESTINATION + " TEXT,"
                + SOURCE+ " TEXT,"
                + STATUS + " INTEGER,"
                + BUSNO + " TEXT,"
                + ISTWOWAY+" INTEGER"+
                ")";
        db.execSQL(CREATE_TABLE);
        CREATE_TABLE = "CREATE TABLE " + TRANSACTION_TABLE_NAME+ "("
                + PAYMENT_DATE + " DATETIME,"
                + PAYMENT_ID+ " TEXT,"
                +FROM+" TEXT,"
                + TO+ " TEXT,"
                + AMOUNT+ " REAL,"
                + PAYMENT_STATUS +" INTEGER"+
                ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    void addTrip(TripDetailsPOJO details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, details.getName());
        values.put(STUDENT_ID, details.getId());
        values.put(DESTINATION, details.getDestination());
        values.put(SOURCE, details.getSource());
        values.put(STATUS, details.getStatus());
        values.put(BUSNO,details.getBusno());
        values.put(DATE,details.getDate());
        values.put(ISTWOWAY,details.getIsTwoWay());

//        String query="Insert into "+TABLE_NAME+" ("+STUDENT_NAME+","+STUDENT_ID+","+DESTINATION+","+SOURCE
        db.insert(TABLE_NAME, null, values);
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        Log.d("tttttttttttttttt", "addTrip: "+cursor.getCount()+details.getDestination());
        db.close();

    }


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    void addTransaction(PaymentPOJO details) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PAYMENT_DATE, getDateTime());
        values.put(PAYMENT_ID, details.getPaymentId());
        values.put(FROM, details.from);
        values.put(TO, details.to);
        values.put(AMOUNT, details.amount);
        values.put(PAYMENT_STATUS,details.getPaymentStatus());

        db.insert(TRANSACTION_TABLE_NAME, null, values);
        String countQuery = "SELECT  * FROM " + TRANSACTION_TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        Log.d("tttttttttttttttt", "addTransaction: "+cursor.getCount());
        db.close();

    }

    void completedStatus(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE "+TABLE_NAME+" SET "+ STATUS+"=0 WHERE id= "+id);
    }

    void cancelStatus(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE "+TABLE_NAME+" SET "+ STATUS+"=-1 WHERE id= "+id);
    }


    public List<TripDetailsPOJO> getUpcomingTrips() {
        List<TripDetailsPOJO> tripsList = new ArrayList<TripDetailsPOJO>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME +" where "+DATE+"=today";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TripDetailsPOJO tripDetails=new TripDetailsPOJO();
                tripDetails.setDate(cursor.getString(0));
                tripDetails.setId(cursor.getString(1));
                tripDetails.setName(cursor.getString(2));
                tripDetails.setDestination(cursor.getString(3));
                tripDetails.setSource(cursor.getString(4));
                tripDetails.setBusno(cursor.getString(5));

                tripsList.add(tripDetails);
            } while (cursor.moveToNext());
        }

        return tripsList;
    }

    public List<PaymentPOJO> getTransactions() {
        List<PaymentPOJO> paymentList= new ArrayList<PaymentPOJO>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PaymentPOJO paymentPOJO=new PaymentPOJO();
                paymentPOJO.setPaymentDate(cursor.getString(0));
                paymentPOJO.setPaymentId(cursor.getString(1));
                paymentPOJO.setFrom(cursor.getString(2));
                paymentPOJO.setTo(cursor.getString(3));
                paymentPOJO.setAmount(cursor.getDouble(4));
                paymentPOJO.setPaymentStatus(cursor.getInt(5));

            } while (cursor.moveToNext());
        }

        return paymentList;
    }


    public List<TripDetailsPOJO> getCancelledTrips() {
        List<TripDetailsPOJO> tripsList = new ArrayList<TripDetailsPOJO>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME +" where "+STATUS+"=-1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TripDetailsPOJO tripDetails=new TripDetailsPOJO();
                tripDetails.setDate(cursor.getString(0));
                tripDetails.setId(cursor.getString(1));
                tripDetails.setName(cursor.getString(2));
                tripDetails.setDestination(cursor.getString(3));
                tripDetails.setSource(cursor.getString(4));
                tripDetails.setBusno(cursor.getString(5));

                tripsList.add(tripDetails);
            } while (cursor.moveToNext());
        }

        return tripsList;
    }

    public List<TripDetailsPOJO> getOnGoingTrips() {
        List<TripDetailsPOJO> tripsList = new ArrayList<TripDetailsPOJO>();
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat dateFormat=new SimpleDateFormat("YYMMDD");
        String date=dateFormat.format(cal.getTime());
        int today=Integer.getInteger(date);
        String selectQuery = "SELECT  * FROM " + TABLE_NAME +" where "+DATE+"=today";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TripDetailsPOJO tripDetails=new TripDetailsPOJO();
                tripDetails.setDate(cursor.getString(0));
                tripDetails.setId(cursor.getString(1));
                tripDetails.setName(cursor.getString(2));
                tripDetails.setDestination(cursor.getString(3));
                tripDetails.setSource(cursor.getString(4));
                tripDetails.setBusno(cursor.getString(5));

                tripsList.add(tripDetails);
            } while (cursor.moveToNext());
        }

        return tripsList;
    }

    public int getTripsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }




}
