package com.me.kenburnsview;

/**
 * Created by J Girish on 26-11-2017.
 */

public class Request_pojo {
    public  String name;
    public  String mobile;
    public String from;
    public String to;
    public String time;
    public String date;
    public  int seatsAvailable;

    public Request_pojo()
    {}
    public Request_pojo(String mobile, String name, String from, String to, String time, String date, int seatsAvailable)
    {
        this.mobile=mobile;
        this.name=name;
        this.from=from;
        this.to=to;
        this.time=time;
        this.date=date;
        this.seatsAvailable=seatsAvailable;
    }
    public String getMobile()
    {
        return mobile;
    }
    public  void setMobile(String mobile){
        this.mobile=mobile;
    }
    public  String getName(){
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getFrom()
    {
        return from;
    }
    public String getTo()
    {
        return to;
    }

    public String getTime()
    {
        return time;
    }
    public String getDate()
    {
        return date;
    }
    public int getSeatsAvailable()
    {
        return seatsAvailable;
    }
    public void setFrom(String from)
    {
        this.from=from;
    }
    public void setTo(String to)
    {
        this.to=to;
    }
    public void setTime(String time)
    {
        this.time=time;
    }
    public void setDate(String date)
    {
        this.date=date;
    }
    public  void setSeatsAvailable(int seatsAvailable)
    {
        this.seatsAvailable=seatsAvailable;
    }
}
