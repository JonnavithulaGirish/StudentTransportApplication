package com.me.kenburnsview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by J Girish on 26-11-2017.
 */

public class RDet_passDet_adapter extends BaseAdapter {
    Context context;
    ArrayList<request_check_pojo>requests;
    public RDet_passDet_adapter(Context context, ArrayList<request_check_pojo>requests)
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
        View getView= LayoutInflater.from(context).inflate(R.layout.rdet_passdet_view, null);
        TextView req_no=(TextView)getView.findViewById(R.id.Req_no);
        req_no.setText("Request "+(i+1)+":");
        TextView name=(TextView)getView.findViewById(R.id.Rdet_passdet_name);
        TextView phno=(TextView)getView.findViewById(R.id.Rdet_passdet_Phno);
        TextView noSeats=(TextView)getView.findViewById(R.id.Rdet_passdet_NoSeats);
        name.setText(requests.get(i).name);
        phno.setText(requests.get(i).mobile);
        noSeats.setText(requests.get(i).required_seats+"");
        return getView;
    }
}
