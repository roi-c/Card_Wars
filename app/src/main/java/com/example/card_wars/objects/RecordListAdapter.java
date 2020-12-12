package com.example.card_wars.objects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.card_wars.R;

import java.util.ArrayList;


public class RecordListAdapter extends ArrayAdapter<Record> {

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;


    static class ViewHolder {
        TextView name;
        TextView date;
        TextView score;
    }

    public RecordListAdapter(Context context, int resource, ArrayList<Record> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get record information
        String name = getItem(position).getName();
        Long date = getItem(position).getDate();
        int score = getItem(position).getScore();

        Record record = new Record(name, date, score);

        final View result; // create view result for showing the animation

        ViewHolder holder;

        if(convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.textView1);
            holder.date = convertView.findViewById(R.id.textView2);
            holder.score = convertView.findViewById(R.id.textView3);

            result = convertView;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.name.setText(record.getName());
        holder.date.setText("" + record.getDate());
        holder.score.setText("" + record.getScore());

        return convertView;

    }
}
