package com.me.kenburnsview;

/**
 * Created by Mukund on 11/26/2017.
 */

public class TripDetailsPOJO {

    String id;
    String name;
    String destination;
    String source;
    int status;
    String busno;
    String date;
    int isTwoWay;

    TripDetailsPOJO(){}

    TripDetailsPOJO(String id, String name, String destination, String source, String busno, String date, int isTwoWay)
    {
        this.busno=busno;
        this.destination=destination;
        this.id=id;
        this.name=name;
        this.isTwoWay=isTwoWay;
        this.source=source;
        this.status=1;
        this.date=date;
    }

    public String getDate() {

        String str="";
        str=str+date.substring(4,6);
        str=str+date.substring(2,4);
        str=str+date.substring(0,2);

        return str;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIsTwoWay(int isTwoWay) {
        this.isTwoWay = isTwoWay;
    }

    public int getIsTwoWay() {
        return isTwoWay;
    }

    public int getStatus() {
        return status;
    }

    public String getDestination() {
        return destination;
    }

    public String getId() {
        return id;
    }

    public String getBusno() {
        return busno;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public void setBusno(String busno) {
        this.busno = busno;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
