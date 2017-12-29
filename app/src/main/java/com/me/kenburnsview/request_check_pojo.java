package com.me.kenburnsview;

/**
 * Created by J Girish on 26-11-2017.
 */

public class request_check_pojo
{
    public String mobile;
    public String name;
    public  int required_seats;

    public request_check_pojo()
    {}
    public request_check_pojo(String mobile, String name, int required_seats)
    {
        this.mobile=mobile;
        this.name=name;
        this.required_seats=required_seats;
    }
    public void setMobile(String mobile)
    {
        this.mobile=mobile;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public void setRequired_seats(int required_seats)
    {
        this.required_seats=required_seats;
    }
    public  String getMobile(){
        return mobile;
    }
    public  String getName()
    {
        return  name;
    }
    public int getRequired_seats()
    {
        return required_seats;
    }

}