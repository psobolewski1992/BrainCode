package com.tomaszow.hackathon.hackathon.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.tomaszow.hackathon.hackathon.R;
import com.tomaszow.hackathon.hackathon.activities.ClusterMarkerActivity;
import com.tomaszow.hackathon.hackathon.activities.MapTrackerActivity;

/**
 * Created by Katarzyna on 2016-03-19.
 */
public class customAdaper extends BaseAdapter {

    private String[] result;
    private Context context;
    private String[] activityId;
    private static LayoutInflater inflater = null;

    public customAdaper(MapTrackerActivity mainActivity, String[] prgmNameList, String[] prgmActivityList) {
        // TODO Auto-generated constructor stub
        result = prgmNameList;
        context = mainActivity;
        activityId = prgmActivityList;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView userName;
        TextView userActivity;
        //  ImageView img;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.custom_list, null);
        holder.userName = (TextView) rowView.findViewById(R.id.userName);
        holder.userActivity = (TextView) rowView.findViewById(R.id.userActivity);
        // holder.img = (ImageView) rowView.findViewById(R.id.imageView1);
        holder.userName.setText(result[position]);
        holder.userActivity.setText(activityId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Użytkownik " + result[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

}

